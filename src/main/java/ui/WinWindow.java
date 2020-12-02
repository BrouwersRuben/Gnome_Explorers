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


/*
        terminal.writeCenter("  .----------------.  .----------------.   \n", 1);
        terminal.writeCenter(" | .--------------. || .--------------. |  \n", 2);
        terminal.writeCenter(" | |    ______    | || |    ______    | |  \n", 3);
        terminal.writeCenter(" | |  .' ___  |   | || |  .' ___  |   | |  \n", 4);
        terminal.writeCenter("| | / .'   \\_|  | || | / .'   \\_|  | |  \n", 5);
        terminal.writeCenter(" | | | |    ____  | || | | |    ____  | |  \n", 6);
        terminal.writeCenter("| | \\ `.___]  _ | || | \\ `.___] _| | |  \n", 7);
        terminal.writeCenter(" | |  `._____.'   | || |  `._____.'   | |  \n" ,8);
        terminal.writeCenter(" | |              | || |              | |  \n", 9);
        terminal.writeCenter(" | '--------------' || '--------------' |  \n",10);
        terminal.writeCenter("  '----------------'  '----------------'     ", 11);
        /*
        terminal.writeCenter(" _     _          _                                \n" +
                "| |   | |        ( )                               \n" +
                "| |___| |__  _   |/_   _ ____    _ _ _  ___  ____  \n" +
                " \\_____/ _ \\| | | | | | / _  )  | | | |/ _ \\|  _ \\ \n" +
                "   ___| |_| | |_| |\\ V ( (/ /   | | | | |_| | | | |\n" +
                "  (___)\\___/ \\____| \\_/ \\____)   \\____|\\___/|_| |_|\n" +
                "     ", 2);

         */
