package com.Ghevi;

import java.awt.*;
import java.util.ArrayList;

// Extending the Polygon class because I'm drawing Polygons

class Rock extends Polygon {

    // Upper left hand corner of the Polygon
    int uLeftXPos, uLeftYPos;


    // Used to change the direction of the asteroid when
    // it hits something and determines how fast it moves
    int xDirection = 1;
    int yDirection = 1;

    int rockWidth = 26;
    int rockHeight = 31;

    static ArrayList<Rock> rocks = new ArrayList<Rock>();

    // Get the board width and height
    int width = GameBoard.boardWidth;
    int height = GameBoard.boardHeight;

    // Will hold the x & y coordinates for the Polygons
    int[] polyXArray, polyYArray;

    // x & y positions available for other methods
    // There will be more Polygon points available later
    public static int[] sPolyXArray = {10, 17, 26, 34, 27, 36, 26, 14, 8, 1, 5, 1, 10};

    public static int[] sPolyYArray = {0, 5, 1, 8, 13, 20, 31, 28, 31, 22, 16, 7, 0};

    public boolean onScreen = true;

    String explodeFile = "file:./res/explode.wav";

    // Creates a new asteroid
    public Rock(int[] polyXArray, int[] polyYArray, int pointsInPoly, int randomStartXPos, int randomStartYPos) {

        // Creates a Polygon by calling the super or parent class of Rock Polygon
        super(polyXArray, polyYArray, pointsInPoly);

        // Randomly generate a speed for the Polygon
        this.xDirection = (int) (Math.random() * 4 + 1);
        this.yDirection = (int) (Math.random() * 4 + 1);

        // Holds the starting x & y position for the Rock
        this.uLeftXPos = randomStartXPos;
        this.uLeftYPos = randomStartYPos;

    }

    public Rectangle getBounds(){
        return new Rectangle(super.xpoints[0], super.ypoints[0], rockWidth, rockHeight);
    }

    public void move(SpaceShip theShip, ArrayList<PhasedLaser> laserProjectiles) {

        Rectangle rockToCheck = this.getBounds();

        for(Rock rock : rocks) {

            if (rock.onScreen) {

                Rectangle otherRock = rock.getBounds();

                if (rock != this && otherRock.intersects(rockToCheck)) {
                    int tempXDirection = this.xDirection;
                    int tempYDirection = this.yDirection;

                    this.xDirection = rock.xDirection;
                    this.yDirection = rock.yDirection;

                    rock.xDirection = tempXDirection;
                    rock.yDirection = tempYDirection;
                }

                Rectangle shipBox = theShip.getBounds();

                if (otherRock.intersects(shipBox)) {
                    GameBoard.playSoundEffect(explodeFile);

                    theShip.setXCenter(theShip.gameBoardWidth / 2);
                    theShip.setYCenter(theShip.gameBoardHeight / 2);

                    theShip.setXVelocity(0);
                    theShip.setYVelocity(0);

                }

                for (PhasedLaser laser : laserProjectiles) {
                    if (laser.onScreen) {
                        if (otherRock.contains(laser.getXCenter(), laser.getYCenter())) {
                            rock.onScreen = false;
                            laser.onScreen = false;

                            GameBoard.playSoundEffect(explodeFile);
                        }
                    }
                }
            }
        }

        // Get the upper left and top most point for the Polygon
        // This will be dynamic later on
        int uLeftXPos = super.xpoints[0];
        int uLeftYPos = super.ypoints[0];

        // If the Rock hits a wall it will go in the opposite direction
        if (uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection;
        if (uLeftYPos < 0 || (uLeftYPos + 50) > height) yDirection = -yDirection;

        // Change the values of the points for the Polygon
        for (int i = 0; i < super.xpoints.length; i++) {

            super.xpoints[i] += xDirection;
            super.ypoints[i] += yDirection;

        }
    }

    // public method available for creating Polygon x point arrays
    public static int[] getPolyXArray(int randomStartXPos) {

        // Clones the array so that the original shape isn't changed for the asteroid
        int[] tempPolyXArray = (int[]) sPolyXArray.clone();

        for (int i = 0; i < tempPolyXArray.length; i++) {
            tempPolyXArray[i] += randomStartXPos;

        }
        return tempPolyXArray;
    }

    // public method available for creating Polygon y point arrays
    public static int[] getPolyYArray(int randomStartYPos) {

        // Clones the array so that the original shape isn't changed for the asteroid
        int[] tempPolyYArray = (int[]) sPolyYArray.clone();

        for (int i = 0; i < tempPolyYArray.length; i++) {
            tempPolyYArray[i] += randomStartYPos;

        }
        return tempPolyYArray;

    }
}