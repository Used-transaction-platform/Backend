package me.kalriz.checkmate.app.params;

public class ResBoardContractIDInfo extends ResponseParam {
  private int account_id = 0;
  private String contract_id = null;

  
  public int getAccount_id() { return this.account_id; }

  
  public void setAccount_id(int account_id) { this.account_id = account_id; }

  
  public String getContract_id() { return this.contract_id; }

  
  public void setContract_id(String contract_id) { this.contract_id = contract_id; }
}
