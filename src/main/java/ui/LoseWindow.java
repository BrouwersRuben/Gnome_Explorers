package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.endGame;

public class LoseWindow implements Window {

    private static boolean addedToDb = false;

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter(" ___    ____  ", 1);
        terminal.writeCenter("|   |  |    | ", 2);
        terminal.writeCenter("|___| |    _| ", 3);
        terminal.writeCenter(" ___  |   |   ", 4);
        terminal.writeCenter("|   | |   |   ", 5);
        terminal.writeCenter("|___| |   |_  ", 6);
        terminal.writeCenter("       |____| ", 7);

        terminal.write("Unfortunately the gnomes lost their way in the dark.. You have lost!", 1, 9);
        terminal.writeCenter("Press [ENTER] to restart the game", 22);
        if (!addedToDb) {
            addedToDb = true;
            endGame();
        }
    }

    private static void resetVariables() {
        addedToDb = false;
    }

    public Window respondToUserInput(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER) {
            resetVariables();
            return new PlayWindow();
        } else {
            return this;
        }
    }
}
