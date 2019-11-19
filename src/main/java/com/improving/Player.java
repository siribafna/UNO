package com.improving;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Player implements IPlayer{
    List<Card> myHand;
    int id;
    Colors mostCommonColor;

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
    public Card draw(IGame game) {
        var temp = game.draw();
        myHand.add(temp);
        return temp;
    }

    @Override
    public int handSize() {
        return myHand.size();
    }

    @Override
    public void takeTurn(IGame game) {
        if(!this.myHand.isEmpty()) {
            this.showMyCards();
            for(Card e: myHand) {
                if(game.isPlayable(e)) {
                    if (e.color == Colors.Wild) {
                        game.playCard(e, Optional.of(Colors.Wild));
                        return;
                    } else {
                        game.playCard(e, null);
                        return;
                    }
                }
            }
            draw(game);
            System.out.println("ID: " + id + " Drawing!");
        }
        return;
    }

    public void setMostCommonColor(Colors mostCommonColor) {
        this.mostCommonColor = mostCommonColor;
    }

    public Colors getMostCommonColor() {
        return mostCommonColor;
    }

    public Card findBestSuggestedCard() {

        mostCommonColor = myHand.stream()
                .map(Card::getColor).filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
        setMostCommonColor(mostCommonColor);

        Faces mostCommonFace = myHand.stream()
                .map(Card::getFace).filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);

        int highestFaceCount = (int) myHand.stream().parallel()
                .filter(f -> (f.getFace() == mostCommonFace)).count();
        int highestColorCount = (int) myHand.stream().parallel()
                .filter(f -> (f.getColor() == mostCommonColor)).count();

        if(highestFaceCount < highestColorCount)
            return myHand.stream().parallel()
                    .filter(f -> (f.getColor() == mostCommonColor)).findFirst().get();
        else if(highestFaceCount > highestColorCount) {
            return myHand.stream().parallel()
                    .filter(f -> (f.getFace() == mostCommonFace)).findFirst().get();
        }
        else return myHand.stream().findFirst().get();
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