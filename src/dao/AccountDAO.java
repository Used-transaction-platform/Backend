package me.kalriz.checkmate.app.dao;

import me.kalriz.checkmate.app.model.AccountModel;

public interface AccountDAO {
  AccountModel getAccountByEmail(String paramString);
  
  AccountModel getAccountByID(int paramInt);
  
  boolean newAccount(String paramString1, String paramString2, String paramString3, String paramString4);
}
