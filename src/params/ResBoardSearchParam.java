package me.kalriz.checkmate.app.params;

import java.util.List;
import me.kalriz.checkmate.app.model.BoardModel;

public class ResBoardSearchParam
  extends ResponseParam {
  private List<BoardModel> boardList = null;

  
  public List<BoardModel> getBoardList() { return this.boardList; }

  
  public void setBoardList(List<BoardModel> boardList) { this.boardList = boardList; }
}
