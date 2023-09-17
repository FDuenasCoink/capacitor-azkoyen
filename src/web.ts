import { WebPlugin } from '@capacitor/core';

import type { AzkoyenPluginBase, ChannelData, ChannelInfo, DeviceStatus, ResponseStatus } from './definitions';
import { Validator } from './lib/Validator';
import { Logger } from './lib/logger';

export class AzkoyenWeb extends WebPlugin implements AzkoyenPluginBase {
  private static readonly COINK_EVENT = 'coinInsert';
  private logger = new Logger('AZKOYEN');
  private validator = new Validator({
    title: 'Azkoyen',
    coins: [
      { value: 50 },
      { value: 100 },
      { value: 200 },
      { value: 500 },
      { value: 1000 },
    ],
  });

  async reset(): Promise<ResponseStatus> {
    this.logger.log('reset');
    return {
      statusCode: 200,
      message: 'web simulator',
    }
  }

  async modifyChannel(channelData: ChannelData): Promise<ChannelInfo> {
    this.logger.log('modifyChannel');
    const channels =  this.validator.modifyChannel(channelData);
    return {
      statusCode: 200,
      message: 'web simulator',
      channels,
    };
  }

  async checkDevice(): Promise<ResponseStatus> {
    this.logger.log('checkDevice');
    return {
      statusCode: 200,
      message: 'web simulator',
    }
  }

  async connect(): Promise<ResponseStatus> {
    this.logger.log('connected');
    return {
      statusCode: 200,
      message: 'web simulator',
    }
  }

  async testStatus(): Promise<DeviceStatus> {
    this.logger.log('tes status simulated');
    return {
      version: '1',
      device: 1,
      errorType: 0,
      errorCode: 0,
      message: "web simulated response",
      aditionalInfo: "",
      priority: 0,
      date: new Date().toISOString(),
    }
  }

  async init(): Promise<void> {
    this.logger.log('setup web');
    return;
  }

  async startReader(): Promise<ResponseStatus> {
    this.logger.log('start reader');
    this.validator.mount();
    this.validator.resetChannels();
    this.validator.onCoinInsert((event) => {
      this.notifyListeners(AzkoyenWeb.COINK_EVENT, event);
    });
    return {
      statusCode: 200,
      message: "web simulator"
    };
  }

  async stopReader(): Promise<ResponseStatus> {
    this.logger.log('stop reader');
    this.validator.unmount();
    return {
      statusCode: 200,
      message: "web simulator"
    };
  }
}
