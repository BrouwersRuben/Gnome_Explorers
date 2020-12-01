package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class LoseWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.write("Unfortunately the gnomes lost their way in the dark.. You have lost!", 1, 1);
        terminal.writeCenter("Press [ENTER] to restart the game", 22);
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayWindow() : this;
    }
}
