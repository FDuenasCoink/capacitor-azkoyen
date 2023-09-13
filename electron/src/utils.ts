import { app } from 'electron';
import { existsSync, readFileSync } from 'fs';
import { join } from 'path';

export function deepClone<T>(object: Record<string, T>): Record<string, T> {
  return JSON.parse(JSON.stringify(object));
}

export function getCapacitorElectronConfig(pluginName: string) {
  let capFileConfig: any = {};
  if (existsSync(join(app.getAppPath(), 'build', 'capacitor.config.js'))) {
    // eslint-disable-next-line @typescript-eslint/no-var-requires
    capFileConfig = require(join(app.getAppPath(), 'build', 'capacitor.config.js')).default;
  } else {
    capFileConfig = JSON.parse(readFileSync(join(app.getAppPath(), 'capacitor.config.json')).toString());
  }
  const pluginConfig = capFileConfig.plugins[pluginName] ?? {};
  return deepClone(pluginConfig as Record<string, any>);
}