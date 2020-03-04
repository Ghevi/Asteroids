package com.Ghevi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GameDrawingPanel extends JComponent {

    public ArrayList<Rock> rocks = new ArrayList<Rock>();

    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;

    static SpaceShip theShip = new SpaceShip();

    int width = GameBoard.boardWidth;
    int height = GameBoard.boardHeight;

    public GameDrawingPanel(){

        for(int i = 0; i < 10; i++){
            int randomStartXPos = (int) (Math.random() * (GameBoard.boardWidth - 40) + 1);
            int randomStartYPos = (int) (Math.random() * (GameBoard.boardHeight - 40) + 1);

            rocks.add(new Rock(Rock.getPolyXArray(randomStartXPos), Rock.getPolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
            Rock.rocks = rocks;
        }
    }

    public void paint(Graphics g) {

        Graphics2D graphicSettings = (Graphics2D) g;

        AffineTransform identity = new AffineTransform();

        graphicSettings.setColor(Color.BLACK);
        graphicSettings.fillRect(0, 0, getWidth(), getHeight());

        graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphicSettings.setPaint(Color.WHITE);

        for (Rock rock : rocks) {
            rock.move();
            graphicSettings.draw(rock);
        }

        // Handles spinning the ship in the clockwise direction when the D key is pressed and held

        if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 68) {
            theShip.increaseRotationAngle();
            System.out.println("Ship Angle: " + theShip.getRotationAngle());

        } else

            // Continues to rotate the ship counter clockwise if the A key is held

            if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 65) {
                theShip.decreaseRotationAngle();
                System.out.println("Ship Angle: " + theShip.getRotationAngle());
        } else
            if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 87){

                // Set movement angle to the current rotation angle
                // This is done so that the ship rotation can be set by the A & D keys
                // but when the throttle is hit the ship knows what direction to go

                theShip.setMovingAngle(theShip.getRotationAngle());

                // Changes the values of x & y based on the angle of the ship. This way it knows if it
                // should increase or decrease x & y. By putting .01 in here we can slowly increase the velocity

                theShip.increaseXVelocity(theShip.shipXMoveAngle(theShip.getMovingAngle()) * 0.1);
                theShip.increaseYVelocity(theShip.shipYMoveAngle(theShip.getMovingAngle()) * 0.1);

            }


        theShip.move();
        graphicSettings.setTransform(identity);
        graphicSettings.translate(theShip.getXCenter(), theShip.getYCenter());
        graphicSettings.rotate(Math.toRadians(theShip.getRotationAngle()));
        graphicSettings.draw(theShip);

        for(PhasedLaser laser : GameBoard.laserProjectiles){
            laser.move();
            if(laser.onScreen){
                graphicSettings.setTransform(identity);
                graphicSettings.translate(laser.getXCenter(), laser.getYCenter());
                graphicSettings.draw(laser);
            }
        }


    }
}
