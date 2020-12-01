package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class PlayWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.write("The gnomes are going through dungeons..", 1, 1);
        terminal.writeCenter("Press [ESC] to lose or [ENTER] to win", 22);
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseWindow();
            case KeyEvent.VK_ENTER:
                return new WinWindow();
        }

        return this;
    }
}
