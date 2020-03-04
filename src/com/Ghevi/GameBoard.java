package com.Ghevi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameBoard extends JFrame {

    public static int boardWidth = 1000;
    public static int boardHeight = 800;

    public static boolean keyHeld = false;

    public static int keyHeldCode;

    public static void main(String[] args) {

        new GameBoard();
    }

    public GameBoard(){

        this.setSize(boardWidth, boardHeight);
        this.setTitle("Java Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // KEYCODE ==> W: 87, A: 65, S: 83, D: 68
                if(e.getKeyCode() == 87){
                    System.out.println("Forward");
                } else if(e.getKeyCode() == 83) {
                    System.out.println("Backward");
                } else if(e.getKeyCode() == 68){
                    System.out.println("Rotate Right");
                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                } else if(e.getKeyCode() == 65){
                    System.out.println("Rotate Left");
                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                keyHeld = false;
            }
        });

        GameDrawingPanel gamePanel = new GameDrawingPanel();

        this.add(gamePanel, BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.setVisible(true);
    }


}
