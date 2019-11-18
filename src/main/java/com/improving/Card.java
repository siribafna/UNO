package com.improving;

public class Card {
    Colors color;
    Faces face;

    public Card(Colors color, Faces face) {
        this.color = color;
        this.face = face;
    }

    public Colors getColor() {
        return color;
    }

    public Faces getFace() {
        return face;
    }

    @Override
    public String toString() {

        return "Card: " + color + " " + face;
    }
}
