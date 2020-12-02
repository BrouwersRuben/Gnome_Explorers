package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.endGame;

public class WinWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.write("The gnomes found the way out! You have won!", 1, 1);
        terminal.writeCenter("Press [ENTER] to play again or [ESC] to go to main menu", 22);
        endGame();
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new StartWindow();
            case KeyEvent.VK_ENTER:
                return new PlayWindow();
            default:
                return this;
        }
    }
}
