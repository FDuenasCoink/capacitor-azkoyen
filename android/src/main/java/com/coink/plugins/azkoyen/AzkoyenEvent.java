package com.coink.plugins.azkoyen;

import com.getcapacitor.JSObject;

import hardware.azkoyen.CoinError_t;

public class AzkoyenEvent extends JSObject {

  public AzkoyenEvent(CoinError_t coin) {
    super();
    int value = coin.getCoin();
    put("value", value);
  }

  private AzkoyenEvent(int value) {
    super();
    put("value", value);
  }

  private AzkoyenEvent(CoinError_t coin, String type) {
    super();
    String message = coin.getMessage();
    int code = coin.getStatusCode();
    if (type.equals("error")) {
      JSObject error = new JSObject();
      error.put("message", message);
      error.put("code", code);
      put("error", error);
    } else if (type.equals("warning")) {
      put("code", code);
      put("message", message);
    }
  }

  public static AzkoyenEvent error(CoinError_t coin) {
    return new AzkoyenEvent(coin, "error");
  }

  public static AzkoyenEvent warning(CoinError_t coin) {
    return new AzkoyenEvent(coin, "warning");
  }

  public static AzkoyenEvent fromValue(int value) {
    return new AzkoyenEvent(value);
  }
}
