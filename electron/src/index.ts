import type { PluginListenerHandle } from '@capacitor/core';
import type { IValidator } from '@fduenascoink/oink-addons';
import { Azkoyen as AzkoyenAddon } from '@fduenascoink/oink-addons';

import type { AzkoyenPlugin, ChannelData, ChannelInfo, CoinEvent, CoinEventWarning, DeviceStatus, ResponseStatus } from '../../src/definitions';

import { getCapacitorElectronConfig } from './utils';

class PluginError extends Error {
  constructor(message: string, code: number) {
    super(`${message} (CÓDIGO ${code})`);
  }
}

export class Azkoyen implements AzkoyenPlugin {
  private azkoyen: IValidator;

  constructor() {
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
    const response = this.azkoyen.connect();
    if (response.statusCode !== 200 && response.statusCode !== 301) {
      throw new PluginError(response.message, response.statusCode);
    }
    return response;
  }

  modifyChannel(channelData: ChannelData): Promise<ChannelInfo> {
    console.log(channelData);
    throw new Error('Method not implemented.');
  }

  async checkDevice(): Promise<ResponseStatus> {
    return this.azkoyen.checkDevice();
  }

  testStatus(): Promise<DeviceStatus> {
    throw new Error('Method not implemented.');
  }

  init(): Promise<void> {
    throw new Error('Method not implemented.');
  }

  startReader(): Promise<ResponseStatus> {
    throw new Error('Method not implemented.');
  }

  stopReader(): Promise<ResponseStatus> {
    throw new Error('Method not implemented.');
  }

  reset(): Promise<ResponseStatus> {
    throw new Error('Method not implemented.');
  }

  addListener(eventName: 'coinInsert', listenerFunc: (event: CoinEvent) => void): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(eventName: 'coinInsertWarning', listenerFunc: (event: CoinEventWarning) => void): Promise<PluginListenerHandle> & PluginListenerHandle;
  addListener(eventName: unknown, listenerFunc: unknown): Promise<PluginListenerHandle> & PluginListenerHandle {
    console.log(eventName, listenerFunc);
    throw new Error('Method not implemented.');
  }

  removeAllListeners(): Promise<void> {
    throw new Error('Method not implemented.');
  }

}