import type { AzkoyenPlugin } from '../../src/definitions';

export class Azkoyen implements AzkoyenPlugin {
  
  async echo(options: { value: string; }): Promise<{ value: string; }> {
    return options;
  }

}