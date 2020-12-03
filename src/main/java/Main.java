package main.java;

import main.java.entities.Animal;
import main.java.ui.Interface;

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
    public static int startingX = 5;
    public static int startingY = 5;
    public static Interface ui;

    public static Connection conn = null;
    public static Statement statement = null;

    // Enable the connection to the database with the tnsnames.ora
    public static void setTnsAdmin() {
        String tnsAdmin = System.getenv("TNS_ADMIN");
        if (tnsAdmin == null) {
            String oracleHome = System.getenv("ORACLE_HOME");
            if (oracleHome == null) {
                return; //failed to find any useful env variables
            }
            tnsAdmin = oracleHome + File.separatorChar + "network" + File.separatorChar + "admin";
        }
        System.setProperty("oracle.net.tns_admin", tnsAdmin);
    }

    public static void main(String[] args) {
        try {
            setTnsAdmin();
            String db_url = "jdbc:oracle:thin:@wildllamaent_medium";
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");

            conn = DriverManager.getConnection(db_url, username, password);
            if(conn != null) {
                System.out.println("Connected to the database.");
                statement = conn.createStatement();

                // Creating the table for the leaderboard
                // statement.execute("CREATE TABLE INT_leaderboard (player_name varchar2(25), end_time timestamp not null, score number not null)");

                showGameWindow();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeDb() {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Closed database connection.");
            }
            if(statement != null && !statement.isClosed()) {
                statement.close();
                System.out.println("Closed database statements.");
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
