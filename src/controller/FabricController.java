package me.kalriz.checkmate.app.controller;

import me.kalriz.checkmate.app.service.impl.FabricUser;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = {"/api/fabric/"})
public class FabricController
{
  private static final Logger Log = LoggerFactory.getLogger(me.kalriz.checkmate.app.CheckMateApplication.class);
  
  @ResponseBody
  @GetMapping(path = {"test"})
  public String boardSubmit() {
    Log.info("API-Fabric-Test: ");
    
    HFClient hfClient = null;
    
    try {
      CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
      
      HFCAClient hfCAClient = HFCAClient.createNewInstance("http://163.239.27.32:7054", null);
      
      hfCAClient.setCryptoSuite(cryptoSuite);
      
      FabricUser admin = new FabricUser("admin", "adminpw", cryptoSuite);
      admin.setMspId("Org0MSP");
      admin.setEnrollment(hfCAClient.enroll("admin", "adminpw"));
      
      hfClient = HFClient.createNewInstance();
      hfClient.setCryptoSuite(cryptoSuite);
      hfClient.setUserContext(admin);
      
      return hfClient.getChannel("bc1").getName();










    
    }
    catch (Exception e) {
      return e.getMessage();
    } 
  }
}
