package com.improving.uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<Card>(112);
    private List<Card> discarded = new ArrayList<Card>();

    public Deck() {
        for(int i = 0; i < 2; i++) {
            for(var color: Colors.values()) {
                for(var face: Faces.values()) {
                    if(color != Colors.Wild && face != Faces.Wild && face != Faces.Draw4)
                        deck.add( new Card(color, face));
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            deck.add( new Card(Colors.Wild, Faces.Draw4));
        }

        for(int i = 0; i < 4; i++) {
            deck.add( new Card(Colors.Wild, Faces.Wild));
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void show() {
        for(Card c: deck) {
            System.out.println(c);
        }
    }

    public List<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }

    public Card draw() { // as long as you're calling draw AFTER shuffle, no problem

        if((deck.isEmpty())) {
            System.out.println("Deck is empty! Starting discarded stack!");
            shuffle(this.discarded);
            setDeck(this.discarded);
        }

        var card = deck.get(0);
        getDiscarded().add(card);
        deck.remove(card);
        return card;

    }

    public void addDiscarded(Card card) {
        this.discarded.add(card);
    }

    public List<Card> getDiscarded() {
        return discarded;
    }

    public void setDiscarded(List<Card> discarded) {
        this.discarded = discarded;
    }
}
