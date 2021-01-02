package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.ui.PrologueWindow.playerName;

public class LoadPrologueWindow implements Window {

    StringBuilder sb = new StringBuilder();

    public void displayOutput(AsciiPanel terminal) {
        playerName = sb.toString();
        terminal.writeCenter("Enter your name and press [ENTER]..", 9);
        terminal.writeCenter(playerName, 11);
    }

    public Window respondToUserInput(KeyEvent key) {
        int keyCode =  key.getKeyCode();
        if(keyCode == KeyEvent.VK_ENTER) {
            return new LoadWindow();
        } else {
            // Alphanumeric character codes
            if((((keyCode>=65)&&(keyCode<=90))||((keyCode>=97)&&(keyCode<=122))||((keyCode>=48)&&(keyCode<=57)))) {
                sb.append(key.getKeyChar());
            }
            if((keyCode == 46 || keyCode == 8) && (sb.length() > 0)) {
                sb.setLength(sb.length() - 1);
            }
            return this;
        }
    }
}
