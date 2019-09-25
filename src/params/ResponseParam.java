package me.kalriz.checkmate.app.params;public class ResponseParam { private long timestamp;
  
  public ResponseParam() {
    this.timestamp = 0L;
    this.success = false;
    this.errorMsg = "";

    
    this.timestamp = System.currentTimeMillis();
  }
  private boolean success; private String errorMsg;
  
  public long getTimestamp() { return this.timestamp; }


  
  public void setTimestamp(long timestamp) { this.timestamp = timestamp; }


  
  public boolean isSuccess() { return this.success; }


  
  public void setSuccess(boolean success) { this.success = success; }


  
  public String getErrorMsg() { return this.errorMsg; }


  
  public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; } }
