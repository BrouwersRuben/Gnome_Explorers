package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.ui.PrologueWindow.playerName;

public class LoadWindow implements Window {

    StringBuilder sb = new StringBuilder();

    public void displayOutput(AsciiPanel terminal) {
        playerName = sb.toString();

        // Display list of 5-10 save game states

    }

    public Window respondToUserInput(KeyEvent key) {
        int keyCode =  key.getKeyCode();
        if(keyCode == KeyEvent.VK_ENTER) {
            return new PlayWindow();
        }
        return this;
    }
}
