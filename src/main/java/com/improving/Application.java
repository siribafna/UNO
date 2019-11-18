package com.improving;

import java.util.Scanner;

/*
NOTES FOR TIM:

PLAYER IS KINDA SMART BUT NOT REALLY, CURRENTLY:
-RECOGNIZES MOST FREQUENT CARD IN THE PILE
 */


public class Application {
    public static void main(String[] args)  {
        System.out.println("Welcome to UNO!");
        System.out.print("How many players? ");
        Scanner scan = new Scanner(System.in);

        int playersNum = scan.nextInt();

        Game game = new Game(playersNum);
        game.play();
    }
}
