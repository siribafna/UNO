package com.improving.uno;

public interface IPlayer {

    Card draw(Game game);
    int handSize();
    void takeTurn(Game game) throws Exception;
}

