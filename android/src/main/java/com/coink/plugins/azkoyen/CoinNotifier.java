package com.coink.plugins.azkoyen;

import com.getcapacitor.JSObject;

interface CoinNotifier {
  void onInsertCoin(JSObject data);

  void onInsertCoinWarning(JSObject data);
}
