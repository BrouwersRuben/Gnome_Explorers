package main.java.entities;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static main.java.Main.world;
import static main.java.ui.PlayWindow.gameScore;

public class Animal extends Entity {

    public Animal(String name, char symbol, Color color, int xPos, int yPos) {
        super(name, symbol, color, xPos, yPos);
    }

    public void move(int dx, int dy) {
        Integer[] position = new Integer[]{x + dx, y + dy};

        // System.out.println("[X, Y] : [" + x + " , " + y + "]");

        // System.out.println(deepContains(world.walls, position));

        if(!deepContains(world.walls, position, false)) {

            if (deepContains(world.treasures, position, true)) {
                gameScore += 50;
            }

            if (deepContains(world.stairs, position, false)) {
                world.generateWorld(++world.level);
            }

            x += dx;
            y += dy;
        }
    }

    public static boolean deepContains(List<Integer[]> list, Integer[] probe, boolean removeElement) {
        int index = 0;
        for (Integer[] element : list) {
            if (Arrays.deepEquals(element, probe)) {
                if (removeElement) {
                    list.remove(index);
                }
                return true;
            }
            index++;
        }
        return false;
    }
}
