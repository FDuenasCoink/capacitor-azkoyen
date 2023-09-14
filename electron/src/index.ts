import type { IValidator } from '@fduenascoink/oink-addons';
import { Azkoyen as AzkoyenAddon } from '@fduenascoink/oink-addons';
import { EventEmitter } from 'events';

import type { AzkoyenPlugin, ChannelData, ChannelInfo, DeviceStatus, ResponseStatus } from '../../src/definitions';

import { CoinChannels } from './channels';
import type { Runnable } from './threat';
import { Thread } from './threat';
import { PluginError, getCapacitorElectronConfig } from './utils';

export class Azkoyen extends EventEmitter implements AzkoyenPlugin, Runnable {
  private static readonly COIN_EVENT = "coinInsert";
  private static readonly COIN_WARNING_EVENT = "coinInsertWarning";

  private azkoyen: IValidator;
  private channels = new CoinChannels();
  private thread = new Thread(this);

  constructor() {
    super();
    const config = getCapacitorElectronConfig('Azkoyen');
    this.azkoyen = new AzkoyenAddon({
      maxCritical: config.maxCritical ?? 3,
      warnToCritical: config.warnToCritical ?? 10,
      maximumPorts: config.maximumPorts ?? 10,
      logLevel: config.logLevel ?? 1,
      logPath: 'logs/pelicano.log',
    });
  }

  async connect(): Promise<ResponseStatus> {
    this.thread.pause();
    const response = this.azkoyen.connect();
    this.thread.resume();
    if (response.statusCode !== 200 && response.statusCode !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  async modifyChannel(channelData: ChannelData): Promise<ChannelInfo> {
    const { channel, active } = channelData;
    this.thread.pause();
    this.channels.setChannel(channel, active);
    this.thread.resume();
    const { mask1, mask2 } = this.channels.getValue();
    this.thread.pause();
    const response = this.azkoyen.modifyChannels(mask1, mask2);
    this.thread.resume();
    if (response.statusCode !== 203) {
      throw new PluginError(response.message, response.statusCode);
    }
    const channels = this.channels.state;
    return { ...response, channels };
  }

  async checkDevice(): Promise<ResponseStatus> {
    this.thread.pause()
    const response = this.azkoyen.checkDevice();
    this.thread.resume();
    const status = response.statusCode;
    if (status !== 200 && status !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  async testStatus(): Promise<DeviceStatus> {
    this.thread.pause();
    const status = this.azkoyen.testStatus();
    this.thread.resume();
    return {
      ...status,
      date: new Date().toDateString(),
    };
  }

  async init(): Promise<void> {
    this.connect();
    this.checkDevice();
    return;
  }

  async startReader(): Promise<ResponseStatus> {
    this.thread.stop();
    const response = this.azkoyen.startReader();
    /* const status = response.statusCode;
    const successStates = [201, 301, 300];
    if (!successStates.includes(status)) {
      throw new PluginError(response.message, response.statusCode);
    } */
    this.channels.reset();
    this.thread.start();
    return response;
  }

  async stopReader(): Promise<ResponseStatus> {
    await this.thread.stop();
    this.channels.reset();
    const response = this.azkoyen.stopReader();
    const status = response.statusCode;
    if (status !== 200 && status !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  async reset(): Promise<ResponseStatus> {
    this.thread.stop();
    const response = this.azkoyen.resetDevice();
    const status = response.statusCode;
    if (status !== 204) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  async run() {
    const coin = this.azkoyen.getCoin();
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
      this.thread.stop();
      return
    }

    const value = coin.coin;
    this.emit(Azkoyen.COIN_EVENT, { value });
  }

  // @ts-ignore
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  addListener(event: string | symbol, listener: (...args: any[]) => void): any {
    return super.addListener(event, listener);
  }

  // @ts-ignore
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  removeAllListeners(event?: string | symbol): any {
    return super.removeAllListeners(event);
  }

  // @ts-ignore
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  removeListener(event: string | symbol, listener: (...args: any[]) => void): any {
    return super.removeListener(event, listener);
  }
}