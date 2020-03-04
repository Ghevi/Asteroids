package com.Ghevi;

public class RepaintTheBoard implements Runnable {

    GameBoard theGameBoard;

    public RepaintTheBoard(GameBoard theGameBoard){
        this.theGameBoard = theGameBoard;
    }


    @Override
    public void run() {
        theGameBoard.repaint();
    }
}
