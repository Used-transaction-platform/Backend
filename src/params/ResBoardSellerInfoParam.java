package me.kalriz.checkmate.app.params;

import me.kalriz.checkmate.app.model.AccountModel;

public class ResBoardSellerInfoParam extends ResponseParam {
  private AccountModel account = null;

  
  public AccountModel getAccount() { return this.account; }


  
  public void setAccount(AccountModel account) { this.account = account; }
}
