package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

public interface Window {
    void displayOutput(AsciiPanel terminal);

    Window respondToUserInput(KeyEvent key);
}

