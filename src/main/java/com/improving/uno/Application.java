package com.improving.uno;

import java.util.Scanner;

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
