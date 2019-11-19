package com.improving;

import java.util.List;
import java.util.Optional;

public interface IGame {
    void playCard(Card card, Optional<Colors> color);
    boolean isPlayable(Card card);
  //  public List<Integer> getPlayerHandSizes();
    public Card draw();



}
