/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package hardware.azkoyen;

public class CoinError_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected CoinError_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CoinError_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(CoinError_t obj) {
    long ptr = 0;
    if (obj != null) {
      if (!obj.swigCMemOwn)
        throw new RuntimeException("Cannot release ownership as memory is not owned");
      ptr = obj.swigCPtr;
      obj.swigCMemOwn = false;
      obj.delete();
    }
    return ptr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        AzkoyenControlJNI.delete_CoinError_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setStatusCode(int value) {
    AzkoyenControlJNI.CoinError_t_StatusCode_set(swigCPtr, this, value);
  }

  public int getStatusCode() {
    return AzkoyenControlJNI.CoinError_t_StatusCode_get(swigCPtr, this);
  }

  public void setEvent(int value) {
    AzkoyenControlJNI.CoinError_t_Event_set(swigCPtr, this, value);
  }

  public int getEvent() {
    return AzkoyenControlJNI.CoinError_t_Event_get(swigCPtr, this);
  }

  public void setCoin(int value) {
    AzkoyenControlJNI.CoinError_t_Coin_set(swigCPtr, this, value);
  }

  public int getCoin() {
    return AzkoyenControlJNI.CoinError_t_Coin_get(swigCPtr, this);
  }

  public void setMessage(String value) {
    AzkoyenControlJNI.CoinError_t_Message_set(swigCPtr, this, value);
  }

  public String getMessage() {
    return AzkoyenControlJNI.CoinError_t_Message_get(swigCPtr, this);
  }

  public void setRemaining(int value) {
    AzkoyenControlJNI.CoinError_t_Remaining_set(swigCPtr, this, value);
  }

  public int getRemaining() {
    return AzkoyenControlJNI.CoinError_t_Remaining_get(swigCPtr, this);
  }

  public CoinError_t() {
    this(AzkoyenControlJNI.new_CoinError_t(), true);
  }

}
