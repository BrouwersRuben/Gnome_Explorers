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
        System.out.println("Wild Llama Entertainment was here.");

        showMainMenu();
        playGame();
        endGame();
    }

    private static void showMainMenu() {
        System.out.println("Show main menu");
    }

    private static void playGame() {
        System.out.println("Show game window");

        // TODO: Add code to display game window

        System.out.println("Use WASD to move!");

        // Repeat movement until game over
        while(!gameOver) {
            movePlayer();

            // Sample game over condition
            if(x == 5 || y == 5 || x == -5 || y == -5) {
                System.out.println("You've strayed too far from the light..");
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
