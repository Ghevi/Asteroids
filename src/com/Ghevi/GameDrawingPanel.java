package com.Ghevi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameDrawingPanel extends JComponent {

    public ArrayList<Rock> rocks = new ArrayList<Rock>();

    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;

    int width = Board.boardWidth;
    int height = Board.boardHeight;

    public GameDrawingPanel(){

        for(int i = 0; i < 50; i++){
            int randomStartXPos = (int) (Math.random() * (Board.boardWidth - 40) + 1);
            int randomStartYPos = (int) (Math.random() * (Board.boardHeight - 40) + 1);

            rocks.add(new Rock(Rock.getPolyXArray(randomStartXPos), Rock.getPolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
        }
    }

    public void paint(Graphics g){

        Graphics2D graphicSettings = (Graphics2D)g;

        graphicSettings.setColor(Color.BLACK);
        graphicSettings.fillRect(0,0, getWidth(), getHeight());

        graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphicSettings.setPaint(Color.WHITE);

        for(Rock rock : rocks){
            rock.move();
            graphicSettings.draw(rock);
        }
    }
}
