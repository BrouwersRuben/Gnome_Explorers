package main.java;

import java.util.Scanner;

public class Main {
    // User input
    private static final Scanner keyboard = new Scanner(System.in);

    // Game over boolean to be switched when game ended
    private static boolean gameOver;

    // Starting position
    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args) {
        showMainMenu();
        if(!gameOver) {
            playGame();
        }
        endGame();
    }

    private static void showMainMenu() {
        /*
        String GameName = "  ____  ____    ___   ___ ___    ___        ___  __ __  ____  _       ___   ____     ___  ____    _____\n" +
                " /    T|    \\  /   \\ |   T   T  /  _]      /  _]|  T  T|    \\| T     /   \\ |    \\   /  _]|    \\  / ___/\n" +
                "Y   __j|  _  YY     Y| _   _ | /  [_      /  [_ |  |  ||  o  ) |    Y     Y|  D  ) /  [_ |  D  )(   \\_ \n" +
                "|  T  ||  |  ||  O  ||  \\_/  |Y    _]    Y    _]l_   _j|   _/| l___ |  O  ||    / Y    _]|    /  \\__  T\n" +
                "|  l_ ||  |  ||     ||   |   ||   [_     |   [_ |     ||  |  |     T|     ||    \\ |   [_ |    \\  /  \\ |\n" +
                "|     ||  |  |l     !|   |   ||     T    |     T|  |  ||  |  |     |l     !|  .  Y|     T|  .  Y \\    |\n" +
                "l___,_jl__j__j \\___/ l___j___jl_____j    l_____j|__j__|l__j  l_____j \\___/ l__j\\_jl_____jl__j\\_j  \\___j";
        String CompanyName = "- Wild Llama Entertainment -\n";
        System.out.println(GameName);
        System.out.printf(" %68s\n", CompanyName);
        System.out.print("Please enter your name adventurer: ");
        String playerName = keyboard.nextLine();
        System.out.printf("Wild Llama Entertainment welcomes you to our game \"%s\" \n", playerName);
        System.out.println(" ");
        //Maybe we should add a start button
        */

        String playerName;

        int choice;

        System.out.println("Wild Llama Entertainment proudly presents..");
        System.out.println("  ____  ____    ___   ___ ___    ___        ___  __ __  ____  _       ___   ____     ___  ____    _____\n" +
                " /    T|    \\  /   \\ |   T   T  /  _]      /  _]|  T  T|    \\| T     /   \\ |    \\   /  _]|    \\  / ___/\n" +
                "Y   __j|  _  YY     Y| _   _ | /  [_      /  [_ |  |  ||  o  ) |    Y     Y|  D  ) /  [_ |  D  )(   \\_ \n" +
                "|  T  ||  |  ||  O  ||  \\_/  |Y    _]    Y    _]l_   _j|   _/| l___ |  O  ||    / Y    _]|    /  \\__  T\n" +
                "|  l_ ||  |  ||     ||   |   ||   [_     |   [_ |     ||  |  |     T|     ||    \\ |   [_ |    \\  /  \\ |\n" +
                "|     ||  |  |l     !|   |   ||     T    |     T|  |  ||  |  |     |l     !|  .  Y|     T|  .  Y \\    |\n" +
                "l___,_jl__j__j \\___/ l___j___jl_____j    l_____j|__j__|l__j  l_____j \\___/ l__j\\_jl_____jl__j\\_j  \\___j");

        System.out.println("\n\tNew Game\n Press 1 to continue\n Press 2 to exit");

        choice = keyboard.nextInt();

        if (choice == 2) {
            System.out.println("Untraveled paths dismay the frail. Return with more valiance.");
            gameOver = true;
            return;
        } else if (choice == 1) {
            System.out.println("\tEnter your name");
        } else {
            System.out.println("\tTry another input next time");
            return;
        }

        playerName = keyboard.next();

        System.out.println("Prepare, " + playerName + ", for adventure awaits you...");

        // "Enter your name' isn't aligned with "new game" and I can't figure out how to use souf or anything else to fix this :/
    }


    private static void playGame() {
        //String cutPlayerName = playerName.substring(0,15);
        //I wanted to show the players name in the top left of the game window, next to the timer.
        String timer = "00:00"; //This timer will work, and start when the game starts
        String startGameWindow = "#-------------------#\n" +
                "| k                 |\n" +
                "|                   |\n" +
                "|                   #\n" +
                "|         P\n" +
                "|                   #\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#-------------------#";
        //Player position = x11, y5
        System.out.printf("%21s \n", timer);
        System.out.printf("%s\n",startGameWindow);
        /*
        //If the player reaches the door (X21, Y5), then the new window will appear. (and so on, until all windows are explored)
        String gameWindow2 = "#-------------------#\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#                   #\n" +
                "\n" +
                "#                   #\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#-------#  #--------#";
         */

        System.out.println("Use WASD to move!");

        // Repeat movement until game over
        while(!gameOver) {
            movePlayer();

            // Sample game over condition
            if(x == 5 || y == 5 || x == -5 || y == -5) {
                gameOver = true;
            }
        }
    }

    private static void movePlayer() {

        // User input. TODO: Replace with KeyListener
        char key = keyboard.next().charAt(0);

        // Change player position based on key pressed
        if(key == 'W' || key == 'w') y++;

        if(key == 'S' || key == 's') y--;

        if(key == 'A' || key == 'a') x--;

        if(key == 'D' || key == 'd') x++;

        System.out.printf("Player position: | x: %2d | y: %2d | %n", x, y);

    }

    private static void endGame() {
        System.out.println("Show end game");
    }
}

