package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class LeaderboardWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("Gnome Explorers Leaderboard", 1);
        terminal.writeCenter("Press [ENTER] to return to main menu", 22);
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new StartWindow() : this;
    }
}
