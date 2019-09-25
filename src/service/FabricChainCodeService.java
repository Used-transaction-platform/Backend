package me.kalriz.checkmate.app.service;

import me.kalriz.checkmate.app.model.BoardModel;

public interface FabricChainCodeService {
  BoardModel getBoard(int paramInt);
  
  boolean newBoard(int paramInt1, int paramInt2);
}
