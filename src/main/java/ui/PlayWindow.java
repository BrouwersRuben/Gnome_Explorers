package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

import static main.java.Main.*;

public class PlayWindow implements Window {

    private static int gameTimer;
    public static int gameScore;

    private static boolean tutorial = true;
    private static boolean timerStarted = false;

    public void displayOutput(AsciiPanel terminal) {
        if (tutorial) {
            terminal.writeCenter("Use [ARROW KEYS] or [WASD] to move around!", 1);
            terminal.writeCenter("You lose when timer reaches 0. You win with score > 100", 22);
            tutorial = false;
        } else {
            if (!timerStarted) {
                gameTimer = 10;
                gameScore = 0;
                timerStarted = true;
                startGameTimer();
            }
            terminal.write("TIME LEFT: " + gameTimer + " seconds | SCORE: " + gameScore + " points", 1, 1);
        }
        terminal.write("####################", 1, 2);
        terminal.write("#                  #", 1,3);
        terminal.write("#                  #", 1,4);
        terminal.write("#                   ", 1,5);
        terminal.write("#                   ", 1,6);
        terminal.write("#                  #", 1,7);
        terminal.write("#                  #", 1,8);
        terminal.write("####################", 1, 9);
        terminal.write(player.getSymbol(), player.getX(), player.getY(), player.getColor());
    }

    private void startGameTimer() {
        Thread newThread = new Thread(() -> {
            while(gameTimer > 0) {
                try {
                    Thread.sleep(1000);
                    gameTimer = gameTimer - 1;
                    ui.window = new PlayWindow();
                    ui.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            resetVariables();

            if(gameScore > 300) {
                ui.window = new WinWindow();
                //Will this work?
            } if (gameTimer == 0 && gameScore < 50){
                ui.window = new LoseWindow();
            }
        });
        newThread.start();
    }

    private void resetVariables() {
        tutorial = true;
        timerStarted = false;
        player.setX(startingX);
        player.setY(startingY);
    }

    private void increaseScore() {
        gameScore += 5;
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                increaseScore();
                if (player.getY() <= 0) {
                   return this;
                }
                player.move(0, -1);
                return this;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (player.getY() >= 23) {
                    return this;
                }
                increaseScore();
                player.move(0, 1);
                return this;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (player.getX() <= 0) {
                    return this;
                }
                increaseScore();
                player.move(-1, 0);
                return this;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (player.getX() >= 79) {
                    return this;
                }
                increaseScore();
                player.move(1, 0);
                return this;
            default:
                return this;
        }
    }
}
