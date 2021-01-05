package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import static main.java.Main.*;
import static main.java.ui.PlayWindow.*;
import static main.java.ui.PrologueWindow.playerName;

public class LoadWindow implements Window {

    StringBuilder sb = new StringBuilder();

    private static ArrayList<String[]> data;

    public void displayOutput(AsciiPanel terminal) {

        data = new ArrayList<String[]>();

        terminal.writeCenter("--------------------------------", 1);
        terminal.writeCenter("|     Available Load Games     |", 2);
        terminal.writeCenter("--------------------------------",3);
        terminal.writeCenter("Press [ESC] to go back to main menu", 22);

        try {
            ResultSet resultSet = statement.executeQuery("" +
                    "SELECT * FROM (" +
                    "SELECT GAME_TIMER, GAME_SCORE, GAME_LEVEL, GAME_TREASURES, PLAYER_POS_X, PLAYER_POS_Y FROM INT_SAVEGAMES WHERE PLAYER_NAME = '" + playerName + "' ORDER BY TIME_ADDED DESC" +
                    ") WHERE ROWNUM <= 5"
            );

            int i = 1;

            while (resultSet.next()) {
                String gameTimer = resultSet.getString(1);
                String gameScore = resultSet.getString(2);
                String gameLevel = resultSet.getString(3);
                String gameTreasures = resultSet.getString(4);
                String playerPosX = resultSet.getString(5);
                String playerPosY = resultSet.getString(6);

                terminal.writeCenter(i + ". Level: " + gameLevel + " | Time left: " + gameTimer + " | Score: " + gameScore, 5 + i);

                data.add(new String[]{gameTimer, gameScore, gameLevel, gameTreasures, playerPosX, playerPosY});

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadGame(int Index) {
        for (int i = 0; i < data.size(); i++) {
            if(i == Index) {
                String[] elem = data.get(i);
                gameTimer = parseInt(elem[0]);
                gameScore = parseInt(elem[1]);
                world.level = parseInt(elem[2]);
                gameTreasures = elem[3];
                player.setX(parseInt(elem[4]));
                player.setY(parseInt(elem[5]));
            }
        }
        loadGame = true;
    }

    public Window respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_1:
                loadGame(0);
                return new PlayWindow();
            case KeyEvent.VK_2:
                loadGame(1);
                return new PlayWindow();
            case KeyEvent.VK_3:
                loadGame(2);
                return new PlayWindow();
            case KeyEvent.VK_4:
                loadGame(3);
                return new PlayWindow();
            case KeyEvent.VK_5:
                loadGame(4);
                return new PlayWindow();
            case KeyEvent.VK_ESCAPE:
                return new StartWindow();
            default:
                return this;
        }
    }
}
