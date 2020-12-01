package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

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

        terminal.writeCenter("Press 1 to start game", 10);
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_1 ? new PlayWindow() : this;
    }
}
