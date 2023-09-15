/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package hardware.azkoyen;

public class ErrorCodePolling_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ErrorCodePolling_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ErrorCodePolling_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ErrorCodePolling_t obj) {
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
        AzkoyenControlJNI.delete_ErrorCodePolling_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setCode(int value) {
    AzkoyenControlJNI.ErrorCodePolling_t_Code_set(swigCPtr, this, value);
  }

  public int getCode() {
    return AzkoyenControlJNI.ErrorCodePolling_t_Code_get(swigCPtr, this);
  }

  public void setMessage(String value) {
    AzkoyenControlJNI.ErrorCodePolling_t_Message_set(swigCPtr, this, value);
  }

  public String getMessage() {
    return AzkoyenControlJNI.ErrorCodePolling_t_Message_get(swigCPtr, this);
  }

  public void setStatic(int value) {
    AzkoyenControlJNI.ErrorCodePolling_t_Static_set(swigCPtr, this, value);
  }

  public int getStatic() {
    return AzkoyenControlJNI.ErrorCodePolling_t_Static_get(swigCPtr, this);
  }

  public void setCritical(int value) {
    AzkoyenControlJNI.ErrorCodePolling_t_Critical_set(swigCPtr, this, value);
  }

  public int getCritical() {
    return AzkoyenControlJNI.ErrorCodePolling_t_Critical_get(swigCPtr, this);
  }

  public ErrorCodePolling_t() {
    this(AzkoyenControlJNI.new_ErrorCodePolling_t(), true);
  }

}