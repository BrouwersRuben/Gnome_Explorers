package main.java.ui;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static main.java.Main.statement;

public class LeaderboardWindow implements Window {

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("-------------------------------", 1);
        terminal.writeCenter("| Gnome Explorers Leaderboard |", 2);
        terminal.writeCenter("-------------------------------",3);

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM (SELECT player_name, end_time, score FROM INT_leaderboard ORDER BY score DESC) WHERE ROWNUM <= 5");

            int i = 1;

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                Timestamp time = resultSet.getTimestamp(2);
                int score = resultSet.getInt(3);

                terminal.writeCenter(i + ". " + name + "     " + time + "     " + score, 5 + i);

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        terminal.writeCenter("  .__.", 13);
        terminal.writeCenter("  (|  |)",14);
        terminal.writeCenter("  (  )", 15);
        terminal.writeCenter("  _)(_", 16);

        terminal.writeCenter("Press [ESC] to return to main menu", 22);
    }

    public Window respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ESCAPE ? new StartWindow() : this;
    }
}
