package com.Ghevi;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class PhasedLaser extends Polygon {

    int gameBoardWidth = GameBoard.boardWidth;
    int gameBoardHeight = GameBoard.boardHeight;

    private double centerX = 0, centerY = 0;

    public static int[] polyXArray = {-3, 3, 3, -3, -3};
    public static int[] polyYArray = {-3, -3, 3, 3, -3};

    private int laserWidth = 6, laserHeight = 6;

    public boolean onScreen = false;

    private double movingAngle = 0;

    private double xVelocity = 5, yVelocity = 5;

    public PhasedLaser(double centerX, double centerY, double movingAngle){
        super(polyXArray, polyYArray, 5);

        this.centerX = centerX;
        this.centerY = centerY;
        this.movingAngle = movingAngle;

        this.onScreen = true;

        // Center of the laser projectile
        this.setXVelocity(this.laserXMoveAngle(movingAngle) * 10);
        this.setYVelocity(this.laserYMoveAngle(movingAngle) * 10);

    }

    public double getXCenter(){ return centerX; }
    public double getYCenter(){ return centerY; }
    public void setXCenter(double xCent){ this.centerX = xCent; }
    public void setYCenter(double yCent){ this.centerY = yCent; }

    public void changeXPos(double incAmt){ this.centerX += incAmt; }
    public void changeYPos(double incAmt){ this.centerY += incAmt; }

    public double getXVelocity(){ return xVelocity; }
    public double getYVelocity(){ return yVelocity; }
    public void setXVelocity(double xVel) { this.xVelocity = xVel; }
    public void setYVelocity(double yVel) { this.yVelocity = yVel; }

    public int getWidth(){ return laserWidth; }
    public int getHeight(){ return laserHeight; }

    public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }
    public double getMovingAngle(){ return this.movingAngle; }

    public Rectangle getBounds(){
        return new Rectangle(getWidth() - 6, getHeight() - 6, getWidth(), getHeight());
    }

    public double laserXMoveAngle(double xMoveAngle){
        return (double) (Math.cos(xMoveAngle * Math.PI / 180));
    }

    public double laserYMoveAngle(double yMoveAngle){
        return (double) (Math.sin(yMoveAngle * Math.PI / 180));
    }

    public void move(){

        if(this.onScreen){
            this.changeXPos(this.getXVelocity());

            if(this.getXCenter() < 0){
                this.onScreen = false;

            } else
                if(this.getXCenter() > gameBoardWidth){
                    this.onScreen = false;
                }

            this.changeYPos(this.getYVelocity());

            if(this.getYCenter() < 0){
                this.onScreen = false;

            } else
            if(this.getYCenter() > gameBoardHeight){
                this.onScreen = false;
            }

        }
    }

}











