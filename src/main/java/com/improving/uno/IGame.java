package com.improving.uno;

import java.util.Optional;

public interface IGame {
    void playCard(Card card, Optional<Colors> color);
    boolean isPlayable(Card card);

}
