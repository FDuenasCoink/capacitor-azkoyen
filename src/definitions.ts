export interface AzkoyenPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
