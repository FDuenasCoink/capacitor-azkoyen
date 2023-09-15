/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package hardware.azkoyen;

public class CoinLost_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected CoinLost_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CoinLost_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(CoinLost_t obj) {
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
        AzkoyenControlJNI.delete_CoinLost_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setCoinCinc(int value) {
    AzkoyenControlJNI.CoinLost_t_CoinCinc_set(swigCPtr, this, value);
  }

  public int getCoinCinc() {
    return AzkoyenControlJNI.CoinLost_t_CoinCinc_get(swigCPtr, this);
  }

  public void setCoinCien(int value) {
    AzkoyenControlJNI.CoinLost_t_CoinCien_set(swigCPtr, this, value);
  }

  public int getCoinCien() {
    return AzkoyenControlJNI.CoinLost_t_CoinCien_get(swigCPtr, this);
  }

  public void setCoinDosc(int value) {
    AzkoyenControlJNI.CoinLost_t_CoinDosc_set(swigCPtr, this, value);
  }

  public int getCoinDosc() {
    return AzkoyenControlJNI.CoinLost_t_CoinDosc_get(swigCPtr, this);
  }

  public void setCoinQuin(int value) {
    AzkoyenControlJNI.CoinLost_t_CoinQuin_set(swigCPtr, this, value);
  }

  public int getCoinQuin() {
    return AzkoyenControlJNI.CoinLost_t_CoinQuin_get(swigCPtr, this);
  }

  public void setCoinMil(int value) {
    AzkoyenControlJNI.CoinLost_t_CoinMil_set(swigCPtr, this, value);
  }

  public int getCoinMil() {
    return AzkoyenControlJNI.CoinLost_t_CoinMil_get(swigCPtr, this);
  }

  public CoinLost_t() {
    this(AzkoyenControlJNI.new_CoinLost_t(), true);
  }

}