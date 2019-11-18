package com.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Game implements IGame{
    Deck deck;
    Player currentPlayer;
    List<Player> players = new ArrayList<>();
    Card topCard;
    int turnDirection =  1;
    int turnEngine;
    int colorIsPresent = 0;
    Optional<Colors> chosenColor;
    Player nextPlayer;
    List<Colors> colorsList = new ArrayList<>(){{
        add(Colors.Blue);
        add(Colors.Yellow);
        add(Colors.Red);
        add(Colors.Green);
    }};

    public Game(int totalPlayers) {
        deck = new Deck();
        deck.shuffle(deck.getDeck());
        for(int i = 0; i < totalPlayers; i++) {
         players.add(new Player(createHand(), i));
        }
    }

    public List<Card> createHand() {
        List<Card> playerHand = new ArrayList<>();
        playerHand.add(draw());
        playerHand.add(draw());
        playerHand.add(draw());
        playerHand.add(draw());
        playerHand.add(draw());
        playerHand.add(draw());
        playerHand.add(draw());
        return playerHand;

    }

    public Card draw() {
        return deck.draw();
    }

    public void play() {

        topCard = draw();
        System.out.println("First: " + getTopCard());

        if (isWeirdCard(topCard)) {
            setTopCard(handleWeirdCards());
        }
        turnEngine = 0;

        var num = turnEngine % players.size();
        setCurrentPlayer(players.get(num));
        while (!currentPlayer.myHand.isEmpty()) {
            if(turnEngine <= 0) {
                turnEngine = turnEngine + players.size();
            }
            setCurrentPlayer(players.get(turnEngine % players.size()));

            currentPlayer.takeTurn(this);
            if(currentPlayer.myHand.size() == 1) {
                System.out.println("Player Number #" + currentPlayer.getId() + " ***********************UNO****************************!");
            }

            if (currentPlayer.myHand.isEmpty()) {
                System.out.println("PLAYER " + currentPlayer.getId() + " WON!");
                return;
            }
            turnEngine = turnEngine + turnDirection;
        }
    }

    public int isValidCard(Card card) {
        if(colorIsPresent == 0) {
            if (card.color == Colors.Wild) {
                return 0;
            }

            if ((card.face == getTopCard().face) || (card.color == getTopCard().color)) {
                return 1;
            }
        }
        if(colorIsPresent == 1) {
            if(card.color.equals(getChosenColor().get())) {
                return 1;
            }
            if(card.color == Colors.Wild) {
                return 0;
            }
        }
        return -1;
    }

    public boolean isWeirdCard(Card card) {
        if(card.face == Faces.Draw4)
            return true;
        if(card.face == Faces.Draw2)
            return true;
        if(card.face == Faces.Skip)
            return true;
        if(card.face == Faces.Reverse)
            return true;
            return false;
    }

    @Override
    public boolean isPlayable(Card card) {
        if(colorIsPresent == 0) {
            if ((card.face == getTopCard().face) || (card.color == getTopCard().color)) {
                return true;
            }
            if(card.color == Colors.Wild) {
                return true;
            }
        }
        if(colorIsPresent == 1) {
            if(card.color == Colors.Wild) {
                return true;
            }
            if(card.color.equals((getChosenColor().get()))) {
                return true;
            }
        }
        return false;
    }

    public void setChosenColor(Optional<Colors> chosenColor) {
        this.chosenColor = chosenColor;
    }

    public Optional<Colors> getChosenColor() {
        return chosenColor;
    }

    public Card handleWeirdCards() {
        if(getTopCard().face == Faces.Draw4) {
            if(turnEngine <= 0) {
                turnEngine = turnEngine + players.size();
            }
            nextPlayer = players.get((turnEngine + turnDirection) % players.size());
            System.out.println("ID " + nextPlayer.getId() + " DRAWING FOUR");
            nextPlayer.getMyHand().add(draw());
            nextPlayer.getMyHand().add(draw());
            nextPlayer.getMyHand().add(draw());
            nextPlayer.getMyHand().add(draw());
            turnEngine = turnEngine + turnDirection;
            return getTopCard();
        }
        if(getTopCard().face == Faces.Draw2) {
            nextPlayer = players.get((turnEngine + turnDirection) % players.size());
            System.out.println("ID " + players.get(nextPlayer.getId()).getId() + " DRAWING TWO");
            nextPlayer.getMyHand().add(draw());
            nextPlayer.getMyHand().add(draw());
            turnEngine = turnEngine + turnDirection;
            return getTopCard();
        }
        if(getTopCard().face== Faces.Skip) {
            nextPlayer = players.get((turnEngine + turnDirection) % players.size());
            System.out.println("ID " + nextPlayer.getId() + " SKIPPING");
            turnEngine = turnEngine + turnDirection;
            return getTopCard();
        }
        if(getTopCard().face == Faces.Reverse) {
            setTurnEngine(turnDirection * -1);
            return getTopCard();
        }
        return null;
    }

    @Override
    public void playCard(Card card, Optional<Colors> color)  {
            if ((card.color == Colors.Wild) && (color.isPresent() == true)) {
                if (color.equals(Optional.of(Colors.Wild))) {
                    setChosenColor(Optional.of(Colors.Red));
                }
                else
                    setChosenColor(Optional.of(color).orElse(null));
            }
        deck.addDiscarded(card);
        currentPlayer.myHand.remove(card);
    }


    // GETTERS AND SETTERS

    public int getTurnEngine() {
        return turnEngine;
    }
    public void setTurnEngine(int turnEngine) {
        this.turnEngine = turnEngine;
    }
    public void setTopCard(Card card) {
        this.topCard = card;
    }
    public Card getTopCard() {
        return topCard;
    }
    public Player getNextPlayer() {
        return nextPlayer;
    }
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setColorIsPresent(int colorIsPresent) {
        this.colorIsPresent = colorIsPresent;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}

