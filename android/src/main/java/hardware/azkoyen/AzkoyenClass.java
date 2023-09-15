/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package hardware.azkoyen;

public class AzkoyenClass {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected AzkoyenClass(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(AzkoyenClass obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(AzkoyenClass obj) {
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
        AzkoyenControlJNI.delete_AzkoyenClass(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setSerialPort(int value) {
    AzkoyenControlJNI.AzkoyenClass_SerialPort_set(swigCPtr, this, value);
  }

  public int getSerialPort() {
    return AzkoyenControlJNI.AzkoyenClass_SerialPort_get(swigCPtr, this);
  }

  public void setSuccessConnect(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_SuccessConnect_set(swigCPtr, this, value);
  }

  public boolean getSuccessConnect() {
    return AzkoyenControlJNI.AzkoyenClass_SuccessConnect_get(swigCPtr, this);
  }

  public void setPortO(int value) {
    AzkoyenControlJNI.AzkoyenClass_PortO_set(swigCPtr, this, value);
  }

  public int getPortO() {
    return AzkoyenControlJNI.AzkoyenClass_PortO_get(swigCPtr, this);
  }

  public void setCoinEvent(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinEvent_set(swigCPtr, this, value);
  }

  public int getCoinEvent() {
    return AzkoyenControlJNI.AzkoyenClass_CoinEvent_get(swigCPtr, this);
  }

  public void setCoinEventPrev(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinEventPrev_set(swigCPtr, this, value);
  }

  public int getCoinEventPrev() {
    return AzkoyenControlJNI.AzkoyenClass_CoinEventPrev_get(swigCPtr, this);
  }

  public void setCoinCinc(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinCinc_set(swigCPtr, this, value);
  }

  public int getCoinCinc() {
    return AzkoyenControlJNI.AzkoyenClass_CoinCinc_get(swigCPtr, this);
  }

  public void setCoinCien(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinCien_set(swigCPtr, this, value);
  }

  public int getCoinCien() {
    return AzkoyenControlJNI.AzkoyenClass_CoinCien_get(swigCPtr, this);
  }

  public void setCoinDosc(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinDosc_set(swigCPtr, this, value);
  }

  public int getCoinDosc() {
    return AzkoyenControlJNI.AzkoyenClass_CoinDosc_get(swigCPtr, this);
  }

  public void setCoinQuin(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinQuin_set(swigCPtr, this, value);
  }

  public int getCoinQuin() {
    return AzkoyenControlJNI.AzkoyenClass_CoinQuin_get(swigCPtr, this);
  }

  public void setCoinMil(int value) {
    AzkoyenControlJNI.AzkoyenClass_CoinMil_set(swigCPtr, this, value);
  }

  public int getCoinMil() {
    return AzkoyenControlJNI.AzkoyenClass_CoinMil_get(swigCPtr, this);
  }

  public void setErrorHappened(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_ErrorHappened_set(swigCPtr, this, value);
  }

  public boolean getErrorHappened() {
    return AzkoyenControlJNI.AzkoyenClass_ErrorHappened_get(swigCPtr, this);
  }

  public void setCriticalError(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_CriticalError_set(swigCPtr, this, value);
  }

  public boolean getCriticalError() {
    return AzkoyenControlJNI.AzkoyenClass_CriticalError_get(swigCPtr, this);
  }

  public void setErrorOCode(int value) {
    AzkoyenControlJNI.AzkoyenClass_ErrorOCode_set(swigCPtr, this, value);
  }

  public int getErrorOCode() {
    return AzkoyenControlJNI.AzkoyenClass_ErrorOCode_get(swigCPtr, this);
  }

  public void setErrorOMsg(String value) {
    AzkoyenControlJNI.AzkoyenClass_ErrorOMsg_set(swigCPtr, this, value);
  }

  public String getErrorOMsg() {
    return AzkoyenControlJNI.AzkoyenClass_ErrorOMsg_get(swigCPtr, this);
  }

  public void setErrorOStatic(int value) {
    AzkoyenControlJNI.AzkoyenClass_ErrorOStatic_set(swigCPtr, this, value);
  }

  public int getErrorOStatic() {
    return AzkoyenControlJNI.AzkoyenClass_ErrorOStatic_get(swigCPtr, this);
  }

  public void setErrorOCritical(int value) {
    AzkoyenControlJNI.AzkoyenClass_ErrorOCritical_set(swigCPtr, this, value);
  }

  public int getErrorOCritical() {
    return AzkoyenControlJNI.AzkoyenClass_ErrorOCritical_get(swigCPtr, this);
  }

  public void setFaultOCode(int value) {
    AzkoyenControlJNI.AzkoyenClass_FaultOCode_set(swigCPtr, this, value);
  }

  public int getFaultOCode() {
    return AzkoyenControlJNI.AzkoyenClass_FaultOCode_get(swigCPtr, this);
  }

  public void setFaultOMsg(String value) {
    AzkoyenControlJNI.AzkoyenClass_FaultOMsg_set(swigCPtr, this, value);
  }

  public String getFaultOMsg() {
    return AzkoyenControlJNI.AzkoyenClass_FaultOMsg_get(swigCPtr, this);
  }

  public void setNoUsedBit(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_NoUsedBit_set(swigCPtr, this, value);
  }

  public boolean getNoUsedBit() {
    return AzkoyenControlJNI.AzkoyenClass_NoUsedBit_get(swigCPtr, this);
  }

  public void setMeasurePhotoBlocked(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_MeasurePhotoBlocked_set(swigCPtr, this, value);
  }

  public boolean getMeasurePhotoBlocked() {
    return AzkoyenControlJNI.AzkoyenClass_MeasurePhotoBlocked_get(swigCPtr, this);
  }

  public void setOutPhotoBlocked(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_OutPhotoBlocked_set(swigCPtr, this, value);
  }

  public boolean getOutPhotoBlocked() {
    return AzkoyenControlJNI.AzkoyenClass_OutPhotoBlocked_get(swigCPtr, this);
  }

  public void setCOSAlert(boolean value) {
    AzkoyenControlJNI.AzkoyenClass_COSAlert_set(swigCPtr, this, value);
  }

  public boolean getCOSAlert() {
    return AzkoyenControlJNI.AzkoyenClass_COSAlert_get(swigCPtr, this);
  }

  public void setActOCoin(int value) {
    AzkoyenControlJNI.AzkoyenClass_ActOCoin_set(swigCPtr, this, value);
  }

  public int getActOCoin() {
    return AzkoyenControlJNI.AzkoyenClass_ActOCoin_get(swigCPtr, this);
  }

  public void setActOChannel(int value) {
    AzkoyenControlJNI.AzkoyenClass_ActOChannel_set(swigCPtr, this, value);
  }

  public int getActOChannel() {
    return AzkoyenControlJNI.AzkoyenClass_ActOChannel_get(swigCPtr, this);
  }

  public void setLoggerLevel(int value) {
    AzkoyenControlJNI.AzkoyenClass_LoggerLevel_set(swigCPtr, this, value);
  }

  public int getLoggerLevel() {
    return AzkoyenControlJNI.AzkoyenClass_LoggerLevel_get(swigCPtr, this);
  }

  public void setLogFilePath(String value) {
    AzkoyenControlJNI.AzkoyenClass_LogFilePath_set(swigCPtr, this, value);
  }

  public String getLogFilePath() {
    return AzkoyenControlJNI.AzkoyenClass_LogFilePath_get(swigCPtr, this);
  }

  public void setMaxPorts(int value) {
    AzkoyenControlJNI.AzkoyenClass_MaxPorts_set(swigCPtr, this, value);
  }

  public int getMaxPorts() {
    return AzkoyenControlJNI.AzkoyenClass_MaxPorts_get(swigCPtr, this);
  }

  public AzkoyenClass() {
    this(AzkoyenControlJNI.new_AzkoyenClass(), true);
  }

  public SpdlogLevels_t SearchSpdlogLevel(int Code) {
    return new SpdlogLevels_t(AzkoyenControlJNI.AzkoyenClass_SearchSpdlogLevel(swigCPtr, this, Code), true);
  }

  public void SetSpdlogLevel() {
    AzkoyenControlJNI.AzkoyenClass_SetSpdlogLevel(swigCPtr, this);
  }

  public ErrorCodeExComm_t SearchErrorCodeExComm(int Code) {
    return new ErrorCodeExComm_t(AzkoyenControlJNI.AzkoyenClass_SearchErrorCodeExComm(swigCPtr, this, Code), true);
  }

  public CoinPolling_t SearchCoin(int Channel) {
    return new CoinPolling_t(AzkoyenControlJNI.AzkoyenClass_SearchCoin(swigCPtr, this, Channel), true);
  }

  public ErrorCodePolling_t SearchErrorCodePolling(int Code) {
    return new ErrorCodePolling_t(AzkoyenControlJNI.AzkoyenClass_SearchErrorCodePolling(swigCPtr, this, Code), true);
  }

  public FaultCode_t SearchFaultCode(int Code) {
    return new FaultCode_t(AzkoyenControlJNI.AzkoyenClass_SearchFaultCode(swigCPtr, this, Code), true);
  }

  public int StIdle() {
    return AzkoyenControlJNI.AzkoyenClass_StIdle(swigCPtr, this);
  }

  public int StConnect() {
    return AzkoyenControlJNI.AzkoyenClass_StConnect(swigCPtr, this);
  }

  public int StCheck() {
    return AzkoyenControlJNI.AzkoyenClass_StCheck(swigCPtr, this);
  }

  public int StWaitPoll() {
    return AzkoyenControlJNI.AzkoyenClass_StWaitPoll(swigCPtr, this);
  }

  public int StPolling() {
    return AzkoyenControlJNI.AzkoyenClass_StPolling(swigCPtr, this);
  }

  public int StReset() {
    return AzkoyenControlJNI.AzkoyenClass_StReset(swigCPtr, this);
  }

  public int StError() {
    return AzkoyenControlJNI.AzkoyenClass_StError(swigCPtr, this);
  }

  public void InitLogger(String Path) {
    AzkoyenControlJNI.AzkoyenClass_InitLogger(swigCPtr, this, Path);
  }

  public int ConnectSerial(int Port) {
    return AzkoyenControlJNI.AzkoyenClass_ConnectSerial(swigCPtr, this, Port);
  }

  public int ScanPorts() {
    return AzkoyenControlJNI.AzkoyenClass_ScanPorts(swigCPtr, this);
  }

  public int SendingCommand(SWIGTYPE_p_std__vectorT_unsigned_char_t Comm) {
    return AzkoyenControlJNI.AzkoyenClass_SendingCommand(swigCPtr, this, SWIGTYPE_p_std__vectorT_unsigned_char_t.getCPtr(Comm));
  }

  public int ExecuteCommand(SWIGTYPE_p_std__vectorT_unsigned_char_t Comm) {
    return AzkoyenControlJNI.AzkoyenClass_ExecuteCommand(swigCPtr, this, SWIGTYPE_p_std__vectorT_unsigned_char_t.getCPtr(Comm));
  }

  public int HandleResponse(SWIGTYPE_p_std__vectorT_unsigned_char_t Response, int Rdlen, int Xlen) {
    return AzkoyenControlJNI.AzkoyenClass_HandleResponse(swigCPtr, this, SWIGTYPE_p_std__vectorT_unsigned_char_t.getCPtr(Response), Rdlen, Xlen);
  }

  public int HandleResponsePolling(SWIGTYPE_p_std__vectorT_unsigned_char_t Response, int Rdlen) {
    return AzkoyenControlJNI.AzkoyenClass_HandleResponsePolling(swigCPtr, this, SWIGTYPE_p_std__vectorT_unsigned_char_t.getCPtr(Response), Rdlen);
  }

  public int HandleResponseInfo(SWIGTYPE_p_std__vectorT_unsigned_char_t Response, int Rdlen) {
    return AzkoyenControlJNI.AzkoyenClass_HandleResponseInfo(swigCPtr, this, SWIGTYPE_p_std__vectorT_unsigned_char_t.getCPtr(Response), Rdlen);
  }

  public int CheckOptoStates() {
    return AzkoyenControlJNI.AzkoyenClass_CheckOptoStates(swigCPtr, this);
  }

  public int SimplePoll() {
    return AzkoyenControlJNI.AzkoyenClass_SimplePoll(swigCPtr, this);
  }

  public int SelfCheck() {
    return AzkoyenControlJNI.AzkoyenClass_SelfCheck(swigCPtr, this);
  }

  public int EnableChannels() {
    return AzkoyenControlJNI.AzkoyenClass_EnableChannels(swigCPtr, this);
  }

  public int CheckEventReset() {
    return AzkoyenControlJNI.AzkoyenClass_CheckEventReset(swigCPtr, this);
  }

  public int ResetDevice() {
    return AzkoyenControlJNI.AzkoyenClass_ResetDevice(swigCPtr, this);
  }

  public SWIGTYPE_p_std__vectorT_unsigned_char_t BuildCmdModifyInhibit(int InhibitMask1, int InhibitMask2) {
    return new SWIGTYPE_p_std__vectorT_unsigned_char_t(AzkoyenControlJNI.AzkoyenClass_BuildCmdModifyInhibit(swigCPtr, this, InhibitMask1, InhibitMask2), true);
  }

  public int ChangeInhibitChannels(int InhibitMask1, int InhibitMask2) {
    return AzkoyenControlJNI.AzkoyenClass_ChangeInhibitChannels(swigCPtr, this, InhibitMask1, InhibitMask2);
  }

}