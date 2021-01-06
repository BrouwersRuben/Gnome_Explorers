//TODO: Shouldn't we change the name of this class? As we do not have any animals in the game itself
package main.java.entities;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.java.Main.world;
import static main.java.ui.PlayWindow.gameScore;
import static main.java.ui.PlayWindow.gameTimer;

public class Animal extends Entity {

    public Animal(String name, char symbol, Color color, int xPos, int yPos) {
        super(name, symbol, color, xPos, yPos);
    }

    public void move(int dx, int dy) {
        Random rd = new Random();
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

            if (deepContains(world.trap, position, true)) {
                gameScore -= rd.nextInt(25)+1;
            }

            if (deepContains(world.stairsInv, position, false)) {
                world.generateWorld(++world.level);
            }

            if (deepContains(world.stairsBack, position, false)) {
                world.generateWorld(--world.level);
            }

            //TODO: Maybe delete this?
            if (deepContains(world.exit, position, true)) {
                gameTimer -= gameTimer-1;
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
