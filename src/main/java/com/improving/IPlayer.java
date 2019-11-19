package com.improving;

public interface IPlayer {

    Card draw(IGame game);
    int handSize();
    void takeTurn(IGame game) throws Exception;
}

