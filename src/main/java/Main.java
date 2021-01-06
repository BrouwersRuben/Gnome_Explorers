package main.java;

import main.java.entities.Animal;
import main.java.ui.Interface;
import main.java.world.World;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static main.java.ui.PlayWindow.gameScore;
import static main.java.ui.PrologueWindow.playerName;

public class Main {

    public static Animal player;
    public static int startingX = 17;
    public static int startingY = 11;
    public static Interface ui;
    public static World world = new World();

    public static Connection conn = null;
    public static Statement statement = null;

    public static void main(String[] args) {
        try {
            String db_url = "jdbc:oracle:thin:@wildllamaent_medium?TNS_ADMIN=./db";
            String username = "INTEGRATION PROJECT";
            String password = "WildLlamaEntertainment1";

            conn = DriverManager.getConnection(db_url, username, password);
            if(conn != null) {
                System.out.println("Connected to the database.");
                statement = conn.createStatement();

                // Creating the table for the leaderboard
//                 statement.execute("CREATE TABLE INT_leaderboard (player_name varchar2(25), end_time timestamp not null, score number not null)");

                // Creating the table for save games
//                statement.execute("CREATE TABLE INT_Savegames (" +
//                        "TIME_ADDED timestamp not null," +
//                        "PLAYER_NAME varchar2(255), " +
//                        "GAME_TIMER number not null, " +
//                        "GAME_SCORE number not null," +
//                        "GAME_LEVEL number not null," +
//                        "GAME_TREASURES varchar2(255)," +
//                        "PLAYER_POS_X number not null," +
//                        "PLAYER_POS_Y number not null" +
//                        ")");

                showGameWindow();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeDb() {
        try {
            if(statement != null && !statement.isClosed()) {
                statement.close();
                System.out.println("Closed database statements.");
            }
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Closed database connection.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void showGameWindow() {
        player = new Animal("player", 'G', Color.white, startingX, startingY);
        ui = new Interface(144, 48);
    }

    public static void endGame() {
        try {
            String insertPlayerData = "INSERT INTO INT_leaderboard (player_name, end_time, score) VALUES ('" + playerName + "', CURRENT_TIMESTAMP, " + gameScore + ")";
            statement.execute(insertPlayerData);
            System.out.println("Successfully inserted the player named " + playerName + " to the database.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
