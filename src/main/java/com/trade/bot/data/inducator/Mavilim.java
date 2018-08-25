package com.trade.bot.data.inducator;

/**
 * @author Ozan Ay
 */
class Mavilim {
    private int firstMovingAverageLength;
    private int secondMovingAverageLength;

    Mavilim(int firstMovingAverageLength, int secondMovingAverageLength) {
        this.firstMovingAverageLength = firstMovingAverageLength;
        this.secondMovingAverageLength = secondMovingAverageLength;
    }

    int getFirstMovingAverageLength() {
        return firstMovingAverageLength;
    }

    int getSecondMovingAverageLength() {
        return secondMovingAverageLength;
    }

    int getTMAL() {
        return firstMovingAverageLength + secondMovingAverageLength;
    }

    int getFMAL() {
        return secondMovingAverageLength + getTMAL();
    }

    int getFTMAL() {
        return getTMAL() + getFMAL();
    }

    int getSMAL() {
        return getFMAL() + getFTMAL();
    }
}
