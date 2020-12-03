package main.java.ui;

import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.event.*;

import static main.java.Main.closeDb;

public class Interface extends JFrame implements KeyListener {
    private AsciiPanel terminal;

    public Window window;

    public Interface(int screenWidth, int screenHeight) {
        super("Gnome Explorers");

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

    public void repaint() {
        terminal.clear();
        window.displayOutput(terminal);
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        window = window.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}