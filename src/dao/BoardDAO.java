package me.kalriz.checkmate.app.dao;

import java.util.List;
import me.kalriz.checkmate.app.model.BoardModel;
import me.kalriz.checkmate.app.params.ResBoardContractIDInfo;

public interface BoardDAO {
  BoardModel getRecentBoard();
  
  int getRecentBoardID();
  
  BoardModel getBoardByID(int paramInt);
  
  List<BoardModel> getBoardByTitle(String paramString);
  
  List<BoardModel> getBoardByAccountID(int paramInt);
  
  List<BoardModel> getBoardByBuyerID(int paramInt);
  
  List<String> getBoardContractIDList();
  
  List<ResBoardContractIDInfo> getBoardContractIDInfoList();
  
  List<BoardModel> getReportBoard();
  
  List<BoardModel> getReportBoardByID(String paramString);
  
  int getReportTotalCount();
  
  int getAccountIdByBoardID(int paramInt);
  
  boolean newBoard(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3);
  
  boolean newAccountBoard(int paramInt1, int paramInt2);
  
  boolean updateBoardState(int paramInt1, int paramInt2);
  
  boolean updateBoardBuyer(int paramInt1, int paramInt2);
}
