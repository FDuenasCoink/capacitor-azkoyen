package com.coink.plugins.azkoyen;

import com.getcapacitor.JSObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hardware.azkoyen.Response_t;
import hardware.azkoyen.TestStatus_t;

public class AzkoyenResponse extends JSObject {
  private final String message;
  private final int statusCode;

  public AzkoyenResponse(Response_t response) {
    super();
    String message = response.getMessage();
    int statusCode = response.getStatusCode();
    this.message = message;
    this.statusCode = statusCode;
    put("statusCode", statusCode);
    put("message", message);
  }

  public AzkoyenResponse(Response_t response, CoinsChannels channels) {
    super();
    String message = response.getMessage();
    int statusCode = response.getStatusCode();
    this.message = message;
    this.statusCode = statusCode;
    put("statusCode", statusCode);
    put("message", message);
    put("channels", channels);
  }

  public AzkoyenResponse(TestStatus_t status) {
    super();
    Date now = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    String date = df.format(now);

    this.message = status.getMessage();
    this.statusCode = status.getErrorCode();

    put("version", status.getVersion());
    put("device", status.getDevice());
    put("errorType", status.getErrorType());
    put("errorCode", status.getErrorCode());
    put("message", status.getMessage());
    put("aditionalInfo", status.getAditionalInfo());
    put("priority", status.getPriority());
    put("date", date);
  }

  public String getMessage() {
    return message;
  }

  public int getStatusCode() {
    return statusCode;
  }

}
