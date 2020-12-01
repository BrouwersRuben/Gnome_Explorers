package main.java.ui;

import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

import static main.java.Main.closeDb;

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
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        final Interface gui = this;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(gui,
                        "Close Gnome Explorers?", "Closing dialog",
                        JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    closeDb();
                    System.exit(0);
                }
            }
        });

        repaint();
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