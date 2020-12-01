package main.java.ui;

import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

public class Interface extends JFrame implements KeyListener {
    private AsciiPanel terminal;
    private Queue<InputEvent> inputQueue;

    private Window window;

    public Interface(int screenWidth, int screenHeight) {
        super("Gnome Explorers");

        inputQueue = new LinkedList<>();
        terminal = new AsciiPanel(screenWidth, screenHeight);
        super.add(terminal);

        pack();

        window = new StartWindow();

        super.addKeyListener(this);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }

    public void clear(char symbol) {
        terminal.clear(symbol);
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

    public void repaint(){
        terminal.clear();
        window.displayOutput(terminal);
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        window = window.respondToUserInput(e);
        inputQueue.add(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}