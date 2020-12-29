package main.java.entities;

import java.awt.*;
import java.util.Arrays;

import static main.java.Main.world;

public class Animal extends Entity {

    public Animal(String name, char symbol, Color color, int xPos, int yPos) {
        super(name, symbol, color, xPos, yPos);
    }

    public void move(int dx, int dy) {
        int[] position = new int[]{x + dx, y + dy};
        System.out.println("[X, Y] : [" + x + " , " + y + "]");
        System.out.println(world.walls.contains(Arrays.toString(position)));
        if(!world.walls.contains(position)) {
            x += dx;
            y += dy;
        }
    }
}
