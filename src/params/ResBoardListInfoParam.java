package me.kalriz.checkmate.app.params;

import java.util.List;
import me.kalriz.checkmate.app.model.BoardModel;

public class ResBoardListInfoParam
  extends ResponseParam {
  private List<BoardModel> boardList = null;
  private int state = 0;

  
  public List<BoardModel> getBoardList() { return this.boardList; }

  
  public void setBoardList(List<BoardModel> boardList) { this.boardList = boardList; }

  
  public int getState() { return this.state; }

  
  public void setState(int state) { this.state = state; }
}
