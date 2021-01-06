package main.java.world;

import asciiPanel.AsciiPanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    public ArrayList<Integer[]> walls;
    public ArrayList<Integer[]> stairs;
    public ArrayList<Integer[]> treasures;
    public ArrayList<Integer[]> trap;
    public ArrayList<Integer[]> stairsInv;
    public ArrayList<Integer[]> stairsBack;
    public ArrayList<Integer[]> endGame;

    public int level = 1;
    int offSet = 3;

    public void generateWorld(int level) {

        System.out.println("Generating new world..");

        walls = new ArrayList<Integer[]>();
        stairs = new ArrayList<Integer[]>();
        treasures = new ArrayList<Integer[]>();
        trap = new ArrayList<Integer[]>();
        stairsInv = new ArrayList<Integer[]>();
        stairsBack = new ArrayList<Integer[]>();
        endGame = new ArrayList<Integer[]>();

        try (BufferedReader br = new BufferedReader(new FileReader("resources/floors/floor" + level + ".txt"))) {
            String s;
            String line = br.readLine();

            int lineIndex = 0;

            while (line != null) {

                lineIndex++;

                // System.out.println("line: " + line + "\n");

                s = line;

                // convert String to char[] array
                char[] chars = s.toCharArray();

                // iterate over char[] array using enhanced for loop
                int charIndex = 0;
                for (char ch : chars) {
                    int wallX = charIndex + offSet;
                    int wallY = lineIndex + offSet;

                    Integer[] position = new Integer[] {wallX, wallY};

                    // System.out.print("char: " + ch + " position: " + Arrays.toString(position) + "\n");
                    if (ch == '1') {
                        walls.add(position);
                    }

                    if (ch == '2') {
                        treasures.add(position);
                    }

                    if (ch == '3') {
                        stairs.add(position);
                    }

                    if (ch == '4') {
                        trap.add(position);
                    }

                    if (ch == '5') {
                        stairsInv.add(position);
                    }

                    if (ch == '6') {
                        stairsBack.add(position);
                    }

                    if (ch == '7') {
                        endGame.add(position);
                    }

                    charIndex++;
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintWorld(AsciiPanel terminal) {

//        System.out.println("Painting world..");

        for (int i = 0 ; i < walls.size(); i++) {
            terminal.write('#', walls.get(i)[0], walls.get(i)[1]);
        }

        for (int i = 0 ; i < treasures.size(); i++) {
            terminal.write('$', treasures.get(i)[0], treasures.get(i)[1]);
        }

        for (int i = 0 ; i < stairs.size(); i++) {
            terminal.write('&', stairs.get(i)[0], stairs.get(i)[1]);
        }

        for (int i = 0 ; i < trap.size(); i++) {
            terminal.write('!', trap.get(i)[0], trap.get(i)[1], Color.red);
        }

        //Is it a good idea to put a space here, to make it more secret...?
        for (int i = 0 ; i < stairsInv.size(); i++) {
            terminal.write('.', stairsInv.get(i)[0], stairsInv.get(i)[1], Color.darkGray);
        }

        for (int i = 0 ; i < stairsBack.size(); i++) {
            terminal.write('&', stairsBack.get(i)[0], stairsBack.get(i)[1]);
        }

        for (int i = 0 ; i < endGame.size(); i++) {
            terminal.write('E', endGame.get(i)[0], endGame.get(i)[1], Color.green);
        }

    }
}
