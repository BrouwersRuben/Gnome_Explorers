package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static main.java.Main.*;
import static main.java.ui.PrologueWindow.playerName;

public class PlayWindow implements Window {

    public static int gameTimer;
    public static int gameScore;
    public static String gameTreasures;

    public static boolean loadGame = false;
    private static boolean tutorial = true;
    private static boolean timerStarted = false;

    public void displayOutput(AsciiPanel terminal) {
        if (loadGame) {
            world.generateWorld(world.level);
            loadGame();
        }
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
                "TIME_ADDED," +
                "PLAYER_NAME, " +
                "GAME_TIMER, " +
                "GAME_SCORE, " +
                "GAME_LEVEL, " +
                "GAME_TREASURES, " +
                "PLAYER_POS_X, " +
                "PLAYER_POS_Y) VALUES (CURRENT_TIMESTAMP, '" + playerName + "' , '" + gameTimer + "' , '" + gameScore + "' , '" + world.level + "' , '" + gameTreasures + "' , '" + player.getX() + "' , '" + player.getY() + "')";
        try {
            statement.execute(insertSaveData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadGame() {
        tutorial = false;
        timerStarted = true;
        startGameTimer();

        char someChar = '|';
        int count = 0;

        for (int i = 0; i < gameTreasures.length(); i++) {
            if (gameTreasures.charAt(i) == someChar) {
                count++;
            }
        }

        ArrayList<Integer[]> tempTreasures = new ArrayList<Integer[]>();

        for (int i = 0; i <= count; i++) {
            if(gameTreasures.length() > 0) {
                String kept = gameTreasures.substring(0, gameTreasures.indexOf("|"));
                String remainder = gameTreasures.substring(gameTreasures.indexOf("|") + 1, gameTreasures.length());

                String X = kept.substring(0, kept.indexOf(","));
                String Y = kept.substring(kept.indexOf(",") + 1);

                int treasureX = parseInt(X);
                int treasureY = parseInt(Y);

                gameTreasures = remainder;

                Integer[] treasurePosition = new Integer[]{treasureX, treasureY};

                tempTreasures.add(treasurePosition);
            }
        }

        world.treasures = tempTreasures;

        loadGame = false;
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
