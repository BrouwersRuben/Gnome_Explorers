package main.java.ui;

import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class Interface extends JFrame implements KeyListener {
    private AsciiPanel terminal;
    private Queue<InputEvent> inputQueue;

    public Interface(int screenWidth, int screenHeight) {
        super("Gnome Explorers");

        inputQueue = new LinkedList<>();
        terminal = new AsciiPanel(screenWidth, screenHeight);
        super.add(terminal);

        pack();

        terminal.writeCenter("GNOME EXPLORERS", 1);

        super.addKeyListener(this);
        super.setSize(screenWidth*9, screenHeight*16);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.repaint();
    }

    public void clear() {
        terminal.clear();
    }

    public void drawChar(char symbol, int xPos, int yPos, Color color) {
        terminal.write(symbol, xPos, yPos, color);
    }

    public InputEvent getNextInput() {
        return inputQueue.poll();
    }

    public void refresh() {
        terminal.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}