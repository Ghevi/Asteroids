package com.Ghevi;

import java.awt.*;

public class SpaceShip extends Polygon {

    private double xVelocity = 0, yVelocity = 0;

    int gameBoardWidth = GameBoard.boardWidth;
    int gameBoardHeight = GameBoard.boardHeight;

    private double centerX = gameBoardWidth / 2, centerY = gameBoardHeight / 2;

    private static int[] polyXArray = {-13, 14, -13, -5, -13};
    private static int[] polyYArray = {-15, 0, 15, 0, -15};

    private int shipWidth = 27, shipHeight = 30;

    private double uLeftXPos = getXCenter() + this.polyXArray[0];
    private double uLeftYPos = getYCenter() + this.polyYArray[0];

    private double rotationAngle = 0, movingAngle = 0;

    public SpaceShip(){
        super(polyXArray, polyYArray, 5);
    }

    public double getXCenter(){ return centerX; }
    public double getYCenter(){ return centerY; }

    public void setXCenter(double xCent){ this.centerX = xCent; }
    public void setYCenter(double yCent){ this.centerY = yCent; }

    public void increaseXPos(double incAmt) { this.centerX += incAmt; }
    public void increaseYPos(double incAmt) { this.centerY += incAmt; }

    public double getULeftXPos(){ return uLeftXPos; }
    public double getULeftYPos(){ return uLeftYPos; }

    public void setULeftXPos(double xULPos){ this.uLeftXPos = xULPos; }
    public void setULeftYPos(double yULPos){ this.uLeftYPos = yULPos; }

    public int getShipWidth(){ return shipWidth; }
    public int getShipHeight(){ return shipHeight; }

    public double getXVelocity(){ return xVelocity; }
    public double getYVelocity(){ return yVelocity; }

    public void setXVelocity(double xVel){ this.xVelocity = xVel; }
    public void seYyVelocity(double yVel){ this.yVelocity = yVel; }

    public void increaseXVelocity(double xVelInc){ this.xVelocity += xVelInc; }
    public void increaseYVelocity(double yVelInc){ this.yVelocity += yVelInc; }

    public void decreaseXVelocity(double xVelDec){ this.xVelocity -= xVelDec; }
    public void decreaseYVelocity(double yVelDec){ this.yVelocity -= yVelDec; }

    public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }
    public double getMovingAngle(){ return movingAngle; }

    public void increaseMovingAngle(double moveAngle){ this.movingAngle += moveAngle; }

    public double shipXMoveAngle(double xMoveAngle){
        return (double) (Math.cos(xMoveAngle * Math.PI / 180));
    }

    public double shipYMoveAngle(double yMoveAngle){
        return (double) (Math.sin(yMoveAngle * Math.PI / 180));
    }

    public double getRotationAngle(){ return rotationAngle; }

    public void increaseRotationAngle(){
        if(getRotationAngle() >= 355){ rotationAngle = 0; }

        else { rotationAngle += 5; }
    }

    public void decreaseRotationAngle(){
        if(getRotationAngle() <= 0){ rotationAngle = 355; }

        else { rotationAngle -= 5; }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getShipWidth() - 14, getShipHeight() - 15, getShipWidth(), getShipHeight());
    }

    public void move(){

        this.increaseXPos(this.getXVelocity());

        if(this.getXCenter() < 0){
            this.setXCenter(gameBoardWidth);
        } else
            if(this.getXCenter() > gameBoardWidth){
                this.setXCenter(0);
            }

        this.increaseYPos(this.getYVelocity());

        if(this.getYCenter() < 0){
            this.setYCenter(gameBoardHeight);
        } else
            if(this.getYCenter() > gameBoardHeight){
                this.setYCenter(0);
        }
    }
}















  /* This is the example of bad design


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

        // If the SpaceShip Class Diagram hits a wall it will go in the opposite direction
        if (uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection;
        if (uLeftYPos < 0 || (uLeftYPos + 50) > height) yDirection = -yDirection;

        // Change the values of the points for the Polygon
        for (int i = 0; i < super.xpoints.length; i++) {

            super.xpoints[i] += xDirection;
            super.ypoints[i] += yDirection;

        }


        super.xpoints = SpaceShip.polyXArray;
        super.ypoints = SpaceShip.polyYArray;
        }
  */