package me.kalriz.checkmate.app.params;

public class ReqBoardSubmitParam {
  private String title = null;
  private String contents = null;
  private int price = 0;
  private String contract_id = null;
  
  public String getContract_id() { return this.contract_id; }

  
  public void setContract_id(String contract_id) { this.contract_id = contract_id; }

  
  public int getPrice() { return this.price; }

  
  public void setPrice(int price) { this.price = price; }
  
  private int seller = 0;

  
  public String getTitle() { return this.title; }

  
  public void setTitle(String title) { this.title = title; }

  
  public String getContents() { return this.contents; }

  
  public void setContents(String contents) { this.contents = contents; }

  
  public int getSeller() { return this.seller; }

  
  public void setSeller(int seller) { this.seller = seller; }
}
