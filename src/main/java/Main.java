package main.java;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    // User input
    private static final Scanner keyboard = new Scanner(System.in);

    // Game over boolean to be switched when game ended
    private static boolean gameOver;

    // Player related variables
    // TODO: Update from hardcoded
    private static String playerName;
    private static String playerTime = "2020-11-29 12:00:00";
    private static int playerScore = 0;

    // Starting position
    private static int x = 0;
    private static int y = 0;

    // Database specific variables
    private static Connection conn = null;
    private static Statement statement = null;

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
                // statement.execute("CREATE TABLE INT_leaderboard (player_name varchar2(25) primary key, end_time timestamp not null, score number not null)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                }
                if(statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        showMainMenu();
        if(!gameOver) {
            playGame();
        }
        endGame();
    }

    private static void showMainMenu() {

        int choice;

        String companyName = "Wild Llama Entertainment";

        System.out.printf("%s proudly presents.. \n", companyName);
        System.out.println("  ____  ____    ___   ___ ___    ___        ___  __ __  ____  _       ___   ____     ___  ____    _____\n" +
                " /    T|    \\  /   \\ |   T   T  /  _]      /  _]|  T  T|    \\| T     /   \\ |    \\   /  _]|    \\  / ___/\n" +
                "Y   __j|  _  YY     Y| _   _ | /  [_      /  [_ |  |  ||  o  ) |    Y     Y|  D  ) /  [_ |  D  )(   \\_ \n" +
                "|  T  ||  |  ||  O  ||  \\_/  |Y    _]    Y    _]l_   _j|   _/| l___ |  O  ||    / Y    _]|    /  \\__  T\n" +
                "|  l_ ||  |  ||     ||   |   ||   [_     |   [_ |     ||  |  |     T|     ||    \\ |   [_ |    \\  /  \\ |\n" +
                "|     ||  |  |l     !|   |   ||     T    |     T|  |  ||  |  |     |l     !|  .  Y|     T|  .  Y \\    |\n" +
                "l___,_jl__j__j \\___/ l___j___jl_____j    l_____j|__j__|l__j  l_____j \\___/ l__j\\_jl_____jl__j\\_j  \\___j");

        System.out.println("\n\tNew Game\n Press 1 to continue\n Press 2 to exit");

        choice = keyboard.nextInt();

        if (choice == 2) {
            System.out.println("Untraveled paths dismay the frail. Return with more valiance.");
            gameOver = true;
            return;
        } else if (choice == 1) {
            System.out.println("\tEnter your name");
        } else {
            System.out.println("\tTry another input next time");
            return;
        }

        playerName = keyboard.next();

        System.out.println("Prepare, " + playerName + ", for adventure awaits you...");

        // "Enter your name' isn't aligned with "new game" and I can't figure out how to use souf or anything else to fix this :/
    }


    private static void playGame() {
        //String cutPlayerName = playerName.substring(0,15);
        //I wanted to show the players name in the top left of the game window, next to the timer.
        String timer = "00:00"; //This timer will work, and start when the game starts
        // TODO: Update this to work with playerTimer variable

        String startGameWindow = "#-------------------#\n" +
                "| k                 |\n" +
                "|                   |\n" +
                "|                   #\n" +
                "|         P\n" +
                "|                   #\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#-------------------#";
        //Player position = x11, y5
        System.out.printf("%21s \n", timer);
        System.out.printf("%s\n",startGameWindow);
        /*
        //If the player reaches the door (X21, Y5), then the new window will appear. (and so on, until all windows are explored)
        String gameWindow2 = "#-------------------#\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#                   #\n" +
                "\n" +
                "#                   #\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#-------#  #--------#";
         */

        System.out.println("Use WASD to move!");

        // Repeat movement until game over
        while(!gameOver) {
            movePlayer();

            // Sample game over condition
            if(x == 5 || y == 5 || x == -5 || y == -5) {
                gameOver = true;
            }
        }
    }

    private static void movePlayer() {

        // User input. TODO: Replace with KeyListener
        char key = keyboard.next().charAt(0);

        // Change player position based on key pressed
        if(key == 'W' || key == 'w') y++;

        if(key == 'S' || key == 's') y--;

        if(key == 'A' || key == 'a') x--;

        if(key == 'D' || key == 'd') x++;

        // Increment player score as placeholder
        playerScore += 10;

        System.out.printf("Player position: | x: %2d | y: %2d | %n", x, y);

    }

    private static void endGame() {
        System.out.println(" ");
        String EndGame = "  ______           _    _____                      \n" +
                " |  ____|         | |  / ____|                     \n" +
                " | |__   _ __   __| | | |  __  __ _ _ __ ___   ___ \n" +
                " |  __| | '_ \\ / _` | | | |_ |/ _` | '_ ` _ \\ / _ \\\n" +
                " | |____| | | | (_| | | |__| | (_| | | | | | |  __/\n" +
                " |______|_| |_|\\__,_|  \\_____|\\__,_|_| |_| |_|\\___|\n" +
                "                                                   \n" +
                "                                                   ";
        System.out.print(EndGame + "\n");
        System.out.printf("%s's final score: %d points\n", playerName, playerScore);
        System.out.printf("Time: %s", playerTime);
    }
}

