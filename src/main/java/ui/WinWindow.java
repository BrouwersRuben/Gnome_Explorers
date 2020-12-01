package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class WinWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.write("The gnomes found the way out! You have won!", 1, 1);
        terminal.writeCenter("Press [ENTER] to play again", 5);
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayWindow() : this;
    }
}
