package main.java.world;

import asciiPanel.AsciiPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    public ArrayList<Integer[]> walls = new ArrayList<Integer[]>();
    public ArrayList<Integer[]> stairs = new ArrayList<Integer[]>();
    public ArrayList<Integer[]> treasures = new ArrayList<Integer[]>();

    int offSet = 3;

    public void generateWorld(int level) {

//        System.out.println("Generating new world..");

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
            terminal.write('T', treasures.get(i)[0], treasures.get(i)[1]);
        }

        for (int i = 0 ; i < stairs.size(); i++) {
            terminal.write('S', stairs.get(i)[0], stairs.get(i)[1]);
        }
    }
}
