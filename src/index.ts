import { registerPlugin } from '@capacitor/core';

import type { AzkoyenPlugin } from './definitions';

const Azkoyen = registerPlugin<AzkoyenPlugin>('Azkoyen', {
  web: () => import('./web').then(m => new m.AzkoyenWeb()),
});

export * from './definitions';
export { Azkoyen };
