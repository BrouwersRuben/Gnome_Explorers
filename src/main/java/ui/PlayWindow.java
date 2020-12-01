package main.java.ui;

import asciiPanel.AsciiPanel;
import main.java.entities.Animal;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class PlayWindow implements Window {

    public static boolean playGame = true;

    private static String playerName;
    private static java.sql.Timestamp playerTime = null;
    private static int playerScore = 0;
    private static Animal player;

    private static Interface ui;

    // Limit game to 60 updates per second so it runs the same on all machines
    private static int fps = 60;
    private static int times = 1000000000 / fps;

    public void displayOutput(AsciiPanel terminal) {
        while(playGame) {
            long startTime = System.nanoTime();

            movePlayer();

            ui.clear();
            terminal.write("The gnomes are going through dungeons..", 1, 1);
            terminal.writeCenter("Press [ESC] to lose or [ENTER] to win", 22);
            ui.drawChar(player.getSymbol(), player.getX(), player.getY(), player.getColor());
            ui.refresh();

            long endTime = System.nanoTime();
            long sleepTime = times - (endTime-startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime/1000000);
                } catch (InterruptedException e) {
                    playGame = false;
                }
            }
        }
    }

    private static void movePlayer() {
        InputEvent event = ui.getNextInput();
        if (event instanceof KeyEvent) {
            KeyEvent keypress = (KeyEvent)event;
            switch (keypress.getKeyCode()){
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    player.move(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    player.move(0, 1);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    player.move(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    player.move(1, 0);
                    break;
            }
        }
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseWindow();
            case KeyEvent.VK_ENTER:
                return new WinWindow();
        }

        return this;
    }
}
