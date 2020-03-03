package com.Ghevi;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Board extends JFrame {

    public static int boardWidth = 1000;
    public static int boardHeight = 1000;

    public static void main(String[] args) {

        new Board();
    }

    public Board(){

        this.setSize(boardWidth, boardHeight);
        this.setTitle("Java Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameDrawingPanel gamePanel = new GameDrawingPanel();

        this.add(gamePanel, BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.setVisible(true);
    }
}
