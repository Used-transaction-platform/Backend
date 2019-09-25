package me.kalriz.checkmate.app.controller;

import java.util.List;
import java.util.Map;
import me.kalriz.checkmate.app.dao.AccountDAO;
import me.kalriz.checkmate.app.dao.BoardDAO;
import me.kalriz.checkmate.app.model.BoardModel;
import me.kalriz.checkmate.app.params.ReqBoardSubmitParam;
import me.kalriz.checkmate.app.params.ResBoardContractIDInfo;
import me.kalriz.checkmate.app.params.ResBoardListInfoParam;
import me.kalriz.checkmate.app.params.ResBoardSellerInfoParam;
import me.kalriz.checkmate.app.params.ResBoardSubmitParam;
import me.kalriz.checkmate.app.params.ResponseParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = {"/api/board/"})
public class BoardController
{
  private static final Logger Log = LoggerFactory.getLogger(me.kalriz.checkmate.app.CheckMateApplication.class);
  
  @Autowired
  private BoardDAO boardDao;
  
  @Autowired
  private AccountDAO accountDao;
  
  @ResponseBody
  @PostMapping(path = {"submit"})
  public ResponseParam boardSubmit(@RequestBody ReqBoardSubmitParam submitParam) {
    Log.info("API-Board-Submit: Request, [" + this.accountDao.getAccountByID(submitParam.getSeller()).getEmail() + "]");
    
    ResBoardSubmitParam result = new ResBoardSubmitParam();
    
    if (this.boardDao.newBoard(submitParam.getTitle(), submitParam.getContents(), submitParam.getSeller(), 
        submitParam.getPrice(), submitParam.getContract_id())) {
      BoardModel board = this.boardDao.getRecentBoard();
      
      if (this.boardDao.newAccountBoard(board.getBoard_id(), submitParam.getSeller())) {
        result.setBoard(board);
        result.setSuccess(true);
      } 
    } else {
      result.setErrorMsg("board doesn't submit.");
    }  return result;
  }
  
  @ResponseBody
  @GetMapping(path = {"search"})
  public ResponseParam boardSearch(@RequestParam Map<String, String> params) {
    Log.info("API-Board-Search-ID: Request, [" + params.keySet().toString() + "]");
    
    for (String key : params.keySet()) {
      if (key.equals("id")) {
        ResBoardSubmitParam param = new ResBoardSubmitParam();
        
        String id = (String)params.get(key);
        
        param.setBoard(this.boardDao.getBoardByID(Integer.parseInt(id)));
        param.setSuccess(true);
        
        return param;
      }  if (key.equals("title")) {
        String title = (String)params.get(key);
        
        ResBoardListInfoParam param = new ResBoardListInfoParam();
        
        param.setBoardList(this.boardDao.getBoardByTitle(title));
        param.setSuccess(true);
        
        return param;
      }  if (key.equals("accountid")) {
        String accountid = (String)params.get(key);
        
        ResBoardListInfoParam param = new ResBoardListInfoParam();
        
        param.setBoardList(this.boardDao.getBoardByAccountID(Integer.parseInt(accountid)));
        param.setSuccess(true);
        
        return param;
      }  if (key.equals("buyerid")) {
        String buyerid = (String)params.get(key);
        
        ResBoardListInfoParam param = new ResBoardListInfoParam();
        
        param.setBoardList(this.boardDao.getBoardByBuyerID(Integer.parseInt(buyerid)));
        param.setSuccess(true);
        
        return param;
      } 
    } 
    return null;
  }

  
  @ResponseBody
  @GetMapping(path = {"search/contract"})
  public List<String> boardSearchContracts(@RequestParam Map<String, String> params) { return this.boardDao.getBoardContractIDList(); }


  
  @ResponseBody
  @GetMapping(path = {"search/contract/detail"})
  public List<ResBoardContractIDInfo> boardSearchContractDetails(@RequestParam Map<String, String> params) { return this.boardDao.getBoardContractIDInfoList(); }

  
  @ResponseBody
  @GetMapping(path = {"seller"})
  public ResponseParam boardSeller(@RequestParam Map<String, String> params) {
    Log.info("API-Board-Search-ID: Request, [" + params.keySet().toString() + "]");
    
    ResBoardSellerInfoParam result = new ResBoardSellerInfoParam();
    
    try {
      String boardIDParam = (String)params.get("boardid");
      
      int boardID = Integer.parseInt(boardIDParam);
      
      result.setAccount(this.accountDao.getAccountByID(this.boardDao.getAccountIdByBoardID(boardID)));
      result.setSuccess(true);
    }
    catch (Exception e) {
      result.setErrorMsg("bad board_id request.");
    } 
    
    return result;
  }
  
  @ResponseBody
  @GetMapping(path = {"update"})
  public ResponseParam boardUpdate(@RequestParam Map<String, String> params) {
    Log.info("API-Board-Search-ID: Request, [" + params.keySet().toString() + "]");
    
    ResBoardSubmitParam result = new ResBoardSubmitParam();
    
    if (params.keySet().contains("state")) {
      
      try {
        String boardIDParam = (String)params.get("boardid");
        String stateParam = (String)params.get("state");
        
        int boardID = Integer.parseInt(boardIDParam);
        int state = Integer.parseInt(stateParam);
        
        Log.info("API-Board-Search-ID: Request, [state: " + state + "]");
        
        if (this.boardDao.updateBoardState(boardID, state)) {
          
          result.setBoard(this.boardDao.getBoardByID(boardID));
          result.setSuccess(true);
        } else {
          
          result.setErrorMsg("bad board_id request.");
        } 
      } catch (Exception e) {
        Log.error("API-Board-Search-ID: " + e.getMessage());
        result.setErrorMsg("bad board_id or state request.");
      }
    
    } else if (params.keySet().contains("buyer")) {
      
      try {
        String boardIDParam = (String)params.get("boardid");
        String buyerParam = (String)params.get("buyer");
        
        int boardID = Integer.parseInt(boardIDParam);
        int buyer = Integer.parseInt(buyerParam);
        
        if (this.boardDao.updateBoardBuyer(boardID, buyer)) {
          
          result.setBoard(this.boardDao.getBoardByID(boardID));
          result.setSuccess(true);
        } else {
          
          result.setErrorMsg("bad board_id request.");
        } 
      } catch (Exception e) {
        result.setErrorMsg("bad board's index or buyer's index request.");
      } 
    } 
    
    return result;
  }
}
