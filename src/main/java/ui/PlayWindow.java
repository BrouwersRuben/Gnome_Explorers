package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static main.java.Main.*;
import static main.java.ui.PrologueWindow.playerName;

public class PlayWindow implements Window {

    private static int gameTimer;
    public static int gameScore;

    private static boolean tutorial = true;
    private static boolean timerStarted = false;

    public void displayOutput(AsciiPanel terminal) {
        if (tutorial) {
            terminal.writeCenter("Use [ARROW KEYS] or [WASD] to move around! Press [ESC] to save the game.", 1);
            terminal.writeCenter("You lose when timer reaches 0. You win with score > 300", 22);
            world.generateWorld(world.level);
            tutorial = false;
        } else {
            if (!timerStarted) {
                gameTimer = 100;
                gameScore = 0;
                timerStarted = true;
                startGameTimer();
            }
            terminal.write("TIME LEFT: " + gameTimer + " seconds | SCORE: " + gameScore + " points | LEVEL: " + world.level, 1, 1);
            terminal.write("-------------------------------------------------------------------------------",0,2);
        }
        world.paintWorld(terminal);
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
            } else {
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

    private void saveGame() {
        StringBuilder gameTreasures = new StringBuilder();
            for (Integer[] elem : world.treasures) {
                gameTreasures.append(elem[0] + "," + elem[1] + "|");
            }
        System.out.println("============== SAVE GAME DATA ==============");
        System.out.println("Name: " + playerName);
        System.out.println("Time: " + gameTimer);
        System.out.println("Score: " + gameScore);
        System.out.println("Level: " + world.level);
        System.out.println("Treasures: " + gameTreasures);
        System.out.println("PosX: " + player.getX());
        System.out.println("PosY: " + player.getY());
        System.out.println("============================================");
        String insertSaveData = "INSERT INTO INT_SAVEGAMES (" +
                "PLAYER_NAME, " +
                "GAME_TIMER, " +
                "GAME_SCORE, " +
                "GAME_LEVEL, " +
                "GAME_TREASURES, " +
                "PLAYER_POS_X, " +
                "PLAYER_POS_Y) VALUES ('" + playerName + "' , '" + gameTimer + "' , '" + gameScore + "' , '" + world.level + "' , '" + gameTreasures + "' , '" + player.getX() + "' , '" + player.getY() + "')";
        try {
            statement.execute(insertSaveData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
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
                player.move(0, 1);
                return this;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (player.getX() <= 0) {
                    return this;
                }
                player.move(-1, 0);
                return this;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (player.getX() >= 79) {
                    return this;
                }
                player.move(1, 0);
                return this;
            case KeyEvent.VK_ESCAPE:
                saveGame();
                return this;
            default:
                return this;
        }
    }
}
