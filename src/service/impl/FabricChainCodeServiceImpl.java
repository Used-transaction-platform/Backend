package me.kalriz.checkmate.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import me.kalriz.checkmate.app.model.BoardModel;
import me.kalriz.checkmate.app.service.FabricChainCodeService;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;


@Service
public class FabricChainCodeServiceImpl
  implements FabricChainCodeService
{
  private HFClient hfClient = null;
  private Channel channel = null;
  
  @Value("${fabric.ca-server.url}")
  private String CA_SERVER_URL;
  
  @Value("${fabric.ca-server.admin.name}")
  private String CA_SERVER_ADMIN_NAME;
  @Value("${fabric.ca-server.pem.file}")
  private String CA_SERVER_PEM_FILE;
  @Value("${fabric.org.name}")
  private String ORG_NAME;
  @Value("${fabric.org.msp.name}")
  private String ORG_MSP_NAME;
  @Value("${fabric.org.admin.name}")
  private String ORG_ADMIN_NAME;
  @Value("${fabric.peer.name}")
  private String PEER_NAME;
  @Value("${fabric.peer.url}")
  private String PEER_URL;
  @Value("${fabric.peer.pem.file}")
  private String PEER_PEM_FILE;
  @Value("${fabric.orderer.name}")
  private String ORDERER_NAME;
  @Value("${fabric.orderer.url}")
  private String ORDERER_URL;
  @Value("${fabric.orderer.pem.file}")
  private String ORDERER_PEM_FILE;
  @Value("${fabric.org.user.name}")
  private String USER_NAME;
  @Value("${fabric.org.user.secret}")
  private String USER_SECRET;
  @Value("${fabric.channel.name}")
  private String CHANNEL_NAME;
  
  public FabricChainCodeServiceImpl() {
    try {
      Properties ca_properties = new Properties();
      ca_properties.put("pemBytes", readString(this.CA_SERVER_PEM_FILE).getBytes());
      HFCAClient hfcaClient = HFCAClient.createNewInstance(this.CA_SERVER_ADMIN_NAME, this.CA_SERVER_URL, ca_properties);
      CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
      hfcaClient.setCryptoSuite(cryptoSuite);
      
      FabricUser admin = new FabricUser(this.ORG_ADMIN_NAME, this.ORG_NAME, cryptoSuite);
      admin.setMspId(this.ORG_MSP_NAME);
      admin.setEnrollment(hfcaClient.enroll(this.USER_NAME, this.USER_SECRET));
      
      this.hfClient = HFClient.createNewInstance();
      this.hfClient.setCryptoSuite(cryptoSuite);
      this.hfClient.setUserContext(admin);
      
      Properties peer_properties = getPropertiesWith(this.PEER_PEM_FILE);
      Peer peer = this.hfClient.newPeer(this.PEER_NAME, this.PEER_URL, peer_properties);
      
      Properties orderer_properties = getPropertiesWith(this.ORDERER_PEM_FILE);
      Orderer orderer = this.hfClient.newOrderer(this.ORDERER_NAME, this.ORDERER_URL, orderer_properties);
      
      Channel channel = this.hfClient.newChannel(this.CHANNEL_NAME);
      
      channel.addPeer(peer);
      channel.addOrderer(orderer);
      channel.initialize();
      
      this.channel = channel;
    }
    catch (Exception exception) {}
  }


  
  private Properties getPropertiesWith(String filename) {
    Properties properties = new Properties();
    properties.put("pemBytes", readString(filename).getBytes());
    properties.setProperty("sslProvider", "openSSL");
    properties.setProperty("negotiationType", "TLS");
    return properties;
  }
  
  private String readString(String filePath) {
    try {
      File file = getFile(filePath);
      if (file.exists()) {
        byte[] encoded = new byte[0];
        try {
          encoded = FileCopyUtils.copyToByteArray(file);
        }
        catch (IOException e) {
          e.printStackTrace();
        } 
        return new String(encoded, StandardCharsets.UTF_8);
      } 
      return null;
    }
    catch (IOException e) {
      return e.getMessage();
    } 
  }
  
  private static File getFile(String filePath) throws IOException {
    String[] tokens = filePath.split("\\.");
    
    ClassPathResource classPathResource = new ClassPathResource(filePath);
    String protocol = classPathResource.getURL().getProtocol();
    
    if (protocol.equals("file")) {
      return classPathResource.getFile();
    }
    if (protocol.equals("jar")) {
      InputStream inputStream = (new ClassPathResource(filePath)).getInputStream();
      File tempFile = File.createTempFile(String.valueOf(tokens[0]) + "-temp", "." + tokens[1]);
      try {
        FileUtils.copyInputStreamToFile(inputStream, tempFile);
      } finally {
        
        IOUtils.closeQuietly(inputStream);
      } 
      return tempFile;
    } 
    
    return null;
  }



  
  public BoardModel getBoard(int boardId) { return null; }




  
  public boolean newBoard(int boardId, int sellerId) { return false; }
}
