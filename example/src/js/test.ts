import { Azkoyen } from '@fduenascoink/capacitor-azkoyen';

async function test() {
  const subscription = await Azkoyen.addListener('coinInsert', (event) => {
    console.log(event);
  });
  if (typeof subscription === 'string') {
    Azkoyen.removeListener?.(subscription);
    return;
  }
  subscription.remove();
}

test();