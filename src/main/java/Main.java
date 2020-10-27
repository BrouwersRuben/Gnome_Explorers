package main.java;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wild Llama Entertainment was here.");

        showMainMenu();
        playGame();
        movePlayer();
        endGame();
    }

    private static void showMainMenu() {
        System.out.println("Show main menu");
    }

    private static void playGame() {
        System.out.println("Show game window");
    }

    private static void movePlayer() {
        System.out.println("Moving player");
    }

    private static void endGame() {
        System.out.println("Show end game");
    }
}
