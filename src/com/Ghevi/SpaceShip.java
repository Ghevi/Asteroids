package com.Ghevi;

import java.awt.*;

public class SpaceShip extends Polygon {

    int uLeftXPos = 500, uLeftYPos = 400;

    int xDirection = 0;
    int yDirection = 0;

    int width = GameBoard.boardWidth;
    int height = GameBoard.boardHeight;

    public static int[] polyXArray = {-13, 14, -13, -5, -13};
    public static int[] polyYArray = {-15, 0, 15, 0, -15};

    static int rotationAngle = 0;

    public SpaceShip(){
        super(polyXArray, polyYArray, 5);
    }

    public void move(){
        /*
        int uLeftXPos = super.xpoints[0];
        int uLeftYPos = super.ypoints[0];

        // If the SpaceShip hits a wall it will go in the opposite direction
        if (uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection;
        if (uLeftYPos < 0 || (uLeftYPos + 50) > height) yDirection = -yDirection;

        // Change the values of the points for the Polygon
        for (int i = 0; i < super.xpoints.length; i++) {

            super.xpoints[i] += xDirection;
            super.ypoints[i] += yDirection;

        }
        */

        super.xpoints = SpaceShip.polyXArray;
        super.ypoints = SpaceShip.polyYArray;

    }
}
