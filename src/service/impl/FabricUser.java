package me.kalriz.checkmate.app.service.impl;

import java.io.Serializable;
import java.util.Set;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;



public class FabricUser
  implements User, Serializable
{
  private static final long serialVersionUID = 8077132186383604355L;
  private String name;
  private Set<String> roles;
  private String account;
  private String affiliation;
  
  public FabricUser(String name, String org, CryptoSuite cryptoSuite) {
    this.enrollment = null;



    
    this.name = name;
    this.cryptoSuite = cryptoSuite;
    this.organization = org;
  }
  private String organization; private String enrollmentSecret; private String mspId;
  Enrollment enrollment;
  private CryptoSuite cryptoSuite;
  
  public String getName() { return this.name; }




  
  public Set<String> getRoles() { return this.roles; }




  
  public String getAccount() { return this.account; }




  
  public String getAffiliation() { return this.affiliation; }




  
  public Enrollment getEnrollment() { return this.enrollment; }


  
  public void setEnrollment(Enrollment enrollment) { this.enrollment = enrollment; }




  
  public String getMspId() { return this.mspId; }


  
  public void setMspId(String mspId) { this.mspId = mspId; }
}
