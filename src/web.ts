import { WebPlugin } from '@capacitor/core';

import type { AzkoyenPlugin } from './definitions';

export class AzkoyenWeb extends WebPlugin implements AzkoyenPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
