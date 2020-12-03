package main.java.entities;

import java.awt.*;

public class Animal extends Entity {
    public Animal(String name, char symbol, Color color, int xPos, int yPos) {
        super(name, symbol, color, xPos, yPos);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
