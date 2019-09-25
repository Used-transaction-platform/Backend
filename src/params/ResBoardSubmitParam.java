package me.kalriz.checkmate.app.params;

import me.kalriz.checkmate.app.model.BoardModel;

public class ResBoardSubmitParam
  extends ResponseParam {
  private BoardModel board = null;

  
  public BoardModel getBoard() { return this.board; }

  
  public void setBoard(BoardModel board) { this.board = board; }
}
