package com.coink.plugins.azkoyen;

import hardware.azkoyen.Response_t;

public class AzkoyenException extends Exception{
  private String code;

  public AzkoyenException(String message, int code) {
    super(message);
    this.code = Integer.toString(code);
  }

  public AzkoyenException(Response_t response) {
    super(response.getMessage());
    this.code = Integer.toString(response.getStatusCode());
  }

  public String getCode() {
    return code;
  }
}
