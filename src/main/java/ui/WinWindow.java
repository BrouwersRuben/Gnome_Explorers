package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.endGame;

public class WinWindow implements Window {



    public void displayOutput(AsciiPanel terminal) {

        terminal.writeCenter(" _______  _______ ", 1);
        terminal.writeCenter("|       ||       |", 2);
        terminal.writeCenter("|    ___||    ___|", 3);
        terminal.writeCenter("|   | __ |   | __ ", 4);
        terminal.writeCenter("|   ||  ||   ||  |", 5);
        terminal.writeCenter("|   |_| ||   |_| |", 6);
        terminal.writeCenter("|_______||_______|", 7);


        terminal.write("The gnomes found the way out, you've won!", 1, 9);
        terminal.writeCenter("Press [ENTER] to play again", 22);
        endGame();
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayWindow() : this;
    }
}
