package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.endGame;

public class WinWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter(" .----------------.  .----------------.   \n" +
                "| .--------------. || .--------------. |  \n" +
                "| |    ______    | || |    ______    | |  \n" +
                "| |  .' ___  |   | || |  .' ___  |   | |  \n" +
                "| | / .'   \\_|   | || | / .'   \\_|   | |  \n" +
                "| | | |    ____  | || | | |    ____  | |  \n" +
                "| | \\ `.___]  _| | || | \\ `.___]  _| | |  \n" +
                "| |  `._____.'   | || |  `._____.'   | |  \n" +
                "| |              | || |              | |  \n" +
                "| '--------------' || '--------------' |  \n" +
                " '----------------'  '----------------'   ", 1);
        terminal.writeCenter(" _     _          _                                \n" +
                "| |   | |        ( )                               \n" +
                "| |___| |__  _   |/_   _ ____    _ _ _  ___  ____  \n" +
                " \\_____/ _ \\| | | | | | / _  )  | | | |/ _ \\|  _ \\ \n" +
                "   ___| |_| | |_| |\\ V ( (/ /   | | | | |_| | | | |\n" +
                "  (___)\\___/ \\____| \\_/ \\____)   \\____|\\___/|_| |_|\n" +
                "     ", 2);
        terminal.write("The gnomes found the way out!", 1, 3);
        terminal.writeCenter("Press [ENTER] to play again", 22);
        endGame();
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayWindow() : this;
    }
}
