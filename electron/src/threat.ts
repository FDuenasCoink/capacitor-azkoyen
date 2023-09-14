export interface Runnable {
  run(): void | Promise<void>;
}

export class Thread {
  private readonly runnable: Runnable;
  private threadRef?: any;

  private active = false;

  constructor(runnable: Runnable) {
    this.runnable = runnable;
  }

  start() {
    if (this.threadRef) return;
    this.active = true;
    this.threadRef = setInterval(async () => {
      await this.runnable.run();
    }, 10);
  }

  stop() {
    this.active = false;
    clearInterval(this.threadRef);
    this.threadRef = undefined;
  }

  pause() {
    if (!this.active) return;
    clearInterval(this.threadRef);
    this.threadRef = undefined;
  }

  resume() {
    if (!this.active) return;
    this.start();
  }

}