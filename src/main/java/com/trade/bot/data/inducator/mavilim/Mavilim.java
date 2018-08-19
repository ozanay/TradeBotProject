package com.trade.bot.data.inducator.mavilim;

/**
 * @author Ozan Ay
 */
public class Mavilim {
    private int firstMovingAverageLength;
    private int secondMovingAverageLength;

    public Mavilim(int firstMovingAverageLength, int secondMovingAverageLength) {
        this.firstMovingAverageLength = firstMovingAverageLength;
        this.secondMovingAverageLength = secondMovingAverageLength;
    }

    public int getFirstMovingAverageLength() {
        return firstMovingAverageLength;
    }

    public int getSecondMovingAverageLength() {
        return secondMovingAverageLength;
    }

    public int getTMAL() {
        return firstMovingAverageLength + secondMovingAverageLength;
    }

    public int getFMAL() {
        return secondMovingAverageLength + getTMAL();
    }

    public int getFTMAL() {
        return getTMAL() + getFMAL();
    }

    public int getSMAL() {
        return getFMAL() + getFTMAL();
    }
}
