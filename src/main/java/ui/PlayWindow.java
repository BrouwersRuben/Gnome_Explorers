package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.player;

public class PlayWindow implements Window {

    private static boolean tutorial = true;

    public void displayOutput(AsciiPanel terminal) {
        if(tutorial) {
            terminal.writeCenter("Use [ARROW KEYS] or [WASD] to move around!", 1);
            terminal.writeCenter("Press [ESC] to lose or [ENTER] to win", 22);
            tutorial = false;
        }
        terminal.write(player.getSymbol(), player.getX(), player.getY(), player.getColor());
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseWindow();
            case KeyEvent.VK_ENTER:
                return new WinWindow();
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.move(0, -1);
                return this;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.move(0, 1);
                return this;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.move(-1, 0);
                return this;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.move(1, 0);
                return this;
            default:
                return this;
        }
    }
}
