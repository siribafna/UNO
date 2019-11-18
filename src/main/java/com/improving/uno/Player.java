package com.improving.uno;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Player implements IPlayer{
    List<Card> myHand;
    int id;

    public Player(List<Card> myHand, int id) {
        this.myHand = myHand;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Card> getMyHand() {
        return myHand;
    }

    public void setMyHand(List<Card> myHand) {
        this.myHand = myHand;
    }

    @Override
    public Card draw(Game game) {
        var card = game.getDeck().draw();
        myHand.add(card);
        return card;
    }

    @Override
    public int handSize() {
        return myHand.size();
    }

    @Override
    public void takeTurn(Game game) {
        var firstCard = game.getTopCard();
        if(!this.myHand.isEmpty()) {
            for (Card e : myHand) {
                if(game.isPlayable(e) == true) {
                    if (game.isValidCard(e) == 1) {
                        game.setColorIsPresent(0);
                        var temp = e;
                        game.setTopCard(temp);
                        System.out.println("ID: " + this.id + " Playing..." + e);
                        game.playCard(e, null);
                        if (game.isWeirdCard(temp)) {
                            game.setTopCard(game.handleWeirdCards());
                        }
                        return;
                    }
                    if (game.isValidCard(e) == 0) {
                        game.setColorIsPresent(1);
                        var temp = e;
                        game.setTopCard(e);
                        System.out.println("ID: " + this.id + " Playing..." + e);
                        game.playCard(e, Optional.of(Colors.Wild));
                        if (game.isWeirdCard(temp)) {
                            game.setTopCard(game.handleWeirdCards());
                        }
                        return;
                    }
                }
            }
            draw(game);
            System.out.println("ID: " + id + " Drawing!");
        }
        return;
    }

    public int isValidCard(Game game, Card card) {
        if(game.colorIsPresent == 0) {
            if (card.color == Colors.Wild) {
                return 0;
            }

            if ((card.face == game.getTopCard().face) || (card.color == game.getTopCard().color)) {
                return 1;
            }
        }
        if(game.colorIsPresent == 1) {
            if(card.color.equals(Optional.of(game.getChosenColor()))) {
                return 1;
            }
            if(card.color == Colors.Wild) {
                return 0;
            }
        }
        return -1;
    }

    public void showMyCards() {
        System.out.println(myHand);
    }

    public Colors pickAColor() {
        Random rand = new Random();
        int randNum = rand.nextInt();
        if(randNum == 0) {
            return Colors.Blue;
        }
        if(randNum == 1) {
            return Colors.Red;
        }
        if(randNum == 2) {
            return Colors.Green;
        }if(randNum == 3) {
            return Colors.Yellow;
        }
        else return null;
    }
}