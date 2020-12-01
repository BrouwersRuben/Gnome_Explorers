package main.java;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    // User input
    private static final Scanner keyboard = new Scanner(System.in);

    // Game over boolean to be switched when game ended
    private static boolean gameOver;

    // Player related variables
    private static String playerName;
    private static java.sql.Timestamp playerTime = null;
    private static int playerScore = 0;
    private static Animal player;

    // Starting position
    private static int x = 0;
    private static int y = 0;

    // UI related variables
    private static UserInterface ui;

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
                // statement.execute("CREATE TABLE INT_leaderboard (player_name varchar2(25), end_time timestamp not null, score number not null)");

                showMainMenu();
                if(!gameOver) {
                    playGame();
                }
                endGame();

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

        System.out.println("\n\tNew Game\n Press 1 to continue\n Press 2 to show leaderboard\n Press 3 to exit");

        choice = keyboard.nextInt();

        if (choice == 3) {
            System.out.println("Untraveled paths dismay the frail. Return with more valiance.");
            System.exit(0);
            return;
        } else if (choice == 2) {
            showLeaderboard();
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

        player = new Animal("player", 'P', Color.white, 10, 10);

        ui = new UserInterface(playerName, 80, 24);

        String timer = "00:00"; //This timer will work, and start when the game starts
        String startGameWindow = "#-------------------#\n" +
                "| k                 |\n" +
                "|                   |\n" +
                "|                   #\n" +
                "|         P\n" +
                "|                   #\n" +
                "|                   |\n" +
                "|                   |\n" +
                "#-------------------#";

        System.out.printf("%21s \n", timer);
        System.out.printf("%s\n",startGameWindow);
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

        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();

        // 2) get a java.util.Date from the calendar instance.
        //    this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();

        // 3) a java current time (now) instance
        playerTime = new java.sql.Timestamp(now.getTime());

        System.out.printf("Time: %s", playerTime);

        try {
            String insertPlayerData = "INSERT INTO INT_leaderboard (player_name, end_time, score) VALUES ('" + playerName + "', CURRENT_TIMESTAMP, " + playerScore + ")";
            statement.execute(insertPlayerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void showLeaderboard() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM (SELECT player_name, end_time, score FROM INT_leaderboard ORDER BY score DESC) WHERE ROWNUM <= 5");

            System.out.printf("%-10s %-10s %-10s%n", "Name", "Time", "Score");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                Timestamp time = resultSet.getTimestamp(2);
                int score = resultSet.getInt(3);

                System.out.printf("%-10s %tT %5d%n", name, time, score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}

