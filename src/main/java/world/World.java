package main.java.world;

import asciiPanel.AsciiPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    public ArrayList<Integer[]> walls = new ArrayList<Integer[]>();
    int offSet = 3;

    public void generateWorld(AsciiPanel terminal) {
        try (BufferedReader br = new BufferedReader(new FileReader("resources/floors/floor1.txt"))) {
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
                        terminal.write('#', wallX, wallY);
                    }
                    charIndex++;
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
