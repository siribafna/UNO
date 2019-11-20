package com.improving;

import java.util.List;
import java.util.Optional;

public interface IGame {
    public void playCard(Card card, Optional<Colors> color);

    boolean isPlayable(Card card);

    public Card draw();

    public void play(int numPlayers);

    public List<IPlayerInfo> getPlayerInfo();

    public IPlayerInfo getNextPlayer();

    public IPlayerInfo getPreviousPlayer();

    public IPlayerInfo getNextNextPlayer();

    public IDeck getDeckInfo();
}
