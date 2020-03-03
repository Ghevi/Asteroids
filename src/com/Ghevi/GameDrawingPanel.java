package com.Ghevi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameDrawingPanel extends JComponent {

    public ArrayList<Rock> rocks = new ArrayList<Rock>();

    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;

    SpaceShip theShip = new SpaceShip();

    int width = GameBoard.boardWidth;
    int height = GameBoard.boardHeight;

    public GameDrawingPanel(){

        for(int i = 0; i < 50; i++){
            int randomStartXPos = (int) (Math.random() * (GameBoard.boardWidth - 40) + 1);
            int randomStartYPos = (int) (Math.random() * (GameBoard.boardHeight - 40) + 1);

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

        theShip.move();
        graphicSettings.draw(theShip);


    }
}
