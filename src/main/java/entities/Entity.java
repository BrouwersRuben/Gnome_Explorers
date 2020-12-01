package main.java.entities;

import java.awt.*;

public class Entity {
    protected int x;
    protected int y;

    protected String name;
    protected char symbol;
    protected Color color;

    // Standard getters for the properties of an entity
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public char getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return name;
    }
    public Color getColor() {
        return this.color;
    }

    public Entity(String name, char symbol, Color color, int xPos, int yPos) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.x = xPos;
        this.y = yPos;
    }
}
