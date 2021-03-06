package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.closeDb;

public class StartWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter(" _____                                              _                         ", 1);
        terminal.writeCenter("|  __ \\                                            | |                        ", 2);
        terminal.writeCenter("| |  \\/_ __   ___  _ __ ___   ___    _____  ___ __ | | ___  _ __ ___ _ __ ___ ", 3);
        terminal.writeCenter("| | __| '_ \\ / _ \\| '_ ` _ \\ / _ \\  / _ \\ \\/ / '_ \\| |/ _ \\| '__/ _ \\ '__/ __|", 4);
        terminal.writeCenter("| |_\\ \\ | | | (_) | | | | | |  __/ |  __/>  <| |_) | | (_) | | |  __/ |  \\__ \\", 5);
        terminal.writeCenter(" \\____/_| |_|\\___/|_| |_| |_|\\___|  \\___/_/\\_\\ .__/|_|\\___/|_|  \\___|_|  |___/", 6);
        terminal.writeCenter("                                             | |                              ", 7);
        terminal.writeCenter("                                             |_|                              ", 8);
        terminal.writeCenter("                        *- Wild Llama Entertainment -*                        ", 10);

        terminal.writeCenter("1 - Start Game   ", 12);
        terminal.writeCenter("2 - Load Game    ", 13);
        terminal.writeCenter("3 - Leaderboards ", 14);
        terminal.writeCenter("4 - Exit         ", 15);
        terminal.write("Version 1.0",67, 22);
    }

    public Window respondToUserInput(KeyEvent key) {
        switch(key.getKeyCode()) {
            case KeyEvent.VK_1:
                return new PrologueWindow();
            case KeyEvent.VK_2:
                return new LoadPrologueWindow();
            case KeyEvent.VK_3:
                return new LeaderboardWindow();
            case KeyEvent.VK_4:
                closeDb();
                System.exit(0);
            default:
                return this;
        }
    }
}
