import type { CoinResult, IAzkoyen, UnsubscribeFunc } from '@fduenascoink/azkoyen-addon';
import { Azkoyen as AzkoyenAddon } from '@fduenascoink/azkoyen-addon';
import { app } from 'electron';
import { EventEmitter } from 'events';
import { join } from 'path'

import type { AzkoyenPlugin, ChannelData, ChannelInfo, DeviceStatus, ResponseStatus } from '../../src/definitions';

import { CoinChannels } from './channels';
import { PluginError, getCapacitorElectronConfig } from './utils';

export class Azkoyen extends EventEmitter implements AzkoyenPlugin {
  private static readonly COIN_EVENT = "coinInsert";
  private static readonly COIN_WARNING_EVENT = "coinInsertWarning";

  private azkoyen: IAzkoyen;
  private channels = new CoinChannels();
  private unsubscribeFn?: UnsubscribeFunc;

  constructor() {
    super();
    const config = getCapacitorElectronConfig('Azkoyen');
    const logsPath = app.getPath('documents');
    this.azkoyen = new AzkoyenAddon({
      maxCritical: config.maxCritical ?? 3,
      warnToCritical: config.warnToCritical ?? 10,
      maximumPorts: config.maximumPorts ?? 10,
      logLevel: config.logLevel ?? 1,
      logPath: join(logsPath, 'oink-logs', 'azkoyen.log'),
    });
  }

  async connect(): Promise<ResponseStatus> {
    const response = this.azkoyen.connect();
    if (response.statusCode !== 200 && response.statusCode !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  async modifyChannel(channelData: ChannelData): Promise<ChannelInfo> {
    const { channel, active } = channelData;
    this.channels.setChannel(channel, active);
    const { mask1, mask2 } = this.channels.getValue();
    const response = this.azkoyen.modifyChannels(mask1, mask2);
    if (response.statusCode !== 203) {
      throw new PluginError(response.message, response.statusCode);
    }
    const channels = this.channels.state;
    return { ...response, channels };
  }

  async checkDevice(): Promise<ResponseStatus> {
    const response = this.azkoyen.checkDevice();
    const status = response.statusCode;
    if (status !== 200 && status !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  async testStatus(): Promise<DeviceStatus> {
    const status = this.azkoyen.testStatus();
    return {
      ...status,
      date: new Date().toISOString(),
    };
  }

  async init(): Promise<void> {
    await this.connect();
    await this.checkDevice();
  }

  async startReader(): Promise<ResponseStatus> {
    await this.unsubscribe();
    const response = this.azkoyen.startReader();
    const status = response.statusCode;
    const successStates = [201, 301, 300];
    if (!successStates.includes(status)) {
      throw new PluginError(response.message, response.statusCode);
    }
    this.channels.reset();
    this.unsubscribeFn = this.azkoyen.onCoin((coin) => this.notifyCoin(coin));
    return response;
  }

  async stopReader(): Promise<ResponseStatus> {
    await this.unsubscribe();
    const response = this.azkoyen.stopReader();
    const status = response.statusCode;
    if (status !== 200 && status !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    this.channels.reset();
    return response;
  }

  async reset(): Promise<ResponseStatus> {
    await this.unsubscribe();
    const response = this.azkoyen.resetDevice();
    const status = response.statusCode;
    if (status !== 204) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  private async notifyCoin(coin: CoinResult) {
    const status = coin.statusCode;
    if (status === 303) return;
    
    if (coin.remaining > 1) {
      const remaining = this.azkoyen.getLostCoins();
      Object.entries(remaining).forEach(([coinValue, quantity]) => {
        for (let i = 0; i < quantity; i++) {
          this.emit(Azkoyen.COIN_EVENT, { value: Number(coinValue) });
        }
      });
    }

    if (status === 302 || status === 404) return;

    if (status === 401) {
      const warning = { code: coin.statusCode, message: coin.message };
      this.emit(Azkoyen.COIN_WARNING_EVENT, warning);
      return;
    }

    if (status !== 202) {
      const error = { code: coin.statusCode, message: coin.message };
      const value = 0;
      const event = { value, error };
      this.emit(Azkoyen.COIN_EVENT, event);
      await this.unsubscribe();
      return
    }

    const value = coin.coin;
    this.emit(Azkoyen.COIN_EVENT, { value });
  }

  // @ts-ignore
  addListener(event: string | symbol, listener: (...args: any[]) => void): any {
    return super.addListener(event, listener);
  }

  // @ts-ignore
  removeAllListeners(event?: string | symbol): any {
    return super.removeAllListeners(event);
  }

  // @ts-ignore
  removeListener(event: string | symbol, listener: (...args: any[]) => void): any {
    return super.removeListener(event, listener);
  }

  private sleep() {
    return new Promise(resolve => setTimeout(resolve, 800));
  }

  private async unsubscribe() {
    if (!this.unsubscribeFn) return;
    this.unsubscribeFn?.();
    await this.sleep();
    this.unsubscribeFn = undefined;
  }

}