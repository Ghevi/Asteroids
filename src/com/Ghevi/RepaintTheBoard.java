package com.Ghevi;

public class RepaintTheBoard implements Runnable {

    Board theBoard;

    public RepaintTheBoard(Board theBoard){
        this.theBoard = theBoard;
    }


    @Override
    public void run() {
        theBoard.repaint();
    }
}
