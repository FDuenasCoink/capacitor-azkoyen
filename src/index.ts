import { registerPlugin } from '@capacitor/core';

import type { AzkoyenPluginElectron } from './definitions';

const Azkoyen = registerPlugin<AzkoyenPluginElectron>('Azkoyen', {
  web: () => import('./web').then(m => new m.AzkoyenWeb()),
  electron: () => (window as any).CapacitorCustomPlatform.plugins.Azkoyen,
});

export * from './definitions';
export { Azkoyen };
