package com.improving;

public interface IPlayer extends IPlayerInfo {

    Card draw(IGame game);
    int handSize();
    void takeTurn(IGame game) throws Exception;
}

