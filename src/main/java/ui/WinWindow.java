package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.endGame;

public class WinWindow implements Window {

    private static boolean addedToDb = false;

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("The gnomes found the way out! You have won!", 5);
        terminal.writeCenter(" _______  _______ ", 7);
        terminal.writeCenter("|       ||       |", 8);
        terminal.writeCenter("|    ___||    ___|", 9);
        terminal.writeCenter("|   | __ |   | __ ", 10);
        terminal.writeCenter("|   ||  ||   ||  |", 11);
        terminal.writeCenter("|   |_| ||   |_| |", 12);
        terminal.writeCenter("|_______||_______|", 13);
        terminal.writeCenter("[ENTER] - play again | [ESC] - go to main menu | [L] - go to leaderboard", 22);
        if (!addedToDb) {
            addedToDb = true;
            endGame();
        }
    }

    private static void resetVariables() {
        addedToDb = false;
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                resetVariables();
                return new StartWindow();
            case KeyEvent.VK_ENTER:
                resetVariables();
                return new PlayWindow();
            case KeyEvent.VK_L:
                resetVariables();
                return new LeaderboardWindow();
            default:
                return this;
        }
    }
}
