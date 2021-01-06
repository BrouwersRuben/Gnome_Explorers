package main.java.ui;

import asciiPanel.AsciiPanel;
import java.awt.*;
import java.awt.event.KeyEvent;

import static main.java.Main.endGame;

public class WinWindow implements Window {

    private static boolean addedToDb = false;

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("You collected enough treasures! You have won!", 5);
        terminal.writeCenter(" _______  _______ ", 7, Color.YELLOW);
        terminal.writeCenter("|       ||       |", 8, Color.YELLOW);
        terminal.writeCenter("|    ___||    ___|", 9, Color.YELLOW);
        terminal.writeCenter("|   | __ |   | __ ", 10, Color.YELLOW);
        terminal.writeCenter("|   ||  ||   ||  |", 11, Color.YELLOW);
        terminal.writeCenter("|   |_| ||   |_| |", 12, Color.YELLOW);
        terminal.writeCenter("|_______||_______|", 13, Color.YELLOW);

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
