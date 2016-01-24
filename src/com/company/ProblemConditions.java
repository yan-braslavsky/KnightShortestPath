package com.company;

/**
 * Created by yan.braslavski on 1/24/16.
 */
public class ProblemConditions {
    private final int mSizeOfBoardNxN;
    private final int mNearestMove;
    private final int mFareMostMove;

    public ProblemConditions(int sizeOfBoardNxN, int nearestMove, int fareMostMove) {
        mSizeOfBoardNxN = sizeOfBoardNxN;
        mNearestMove = nearestMove;
        mFareMostMove = fareMostMove;
    }

    public boolean isPositionOnChessBoard(int x,int y) {
        return (x <= mSizeOfBoardNxN && y <= mSizeOfBoardNxN
                && x >= 0 && y >= 0);
    }

    public int getFareMostMove() {
        return mFareMostMove;
    }

    public int getNearestMove() {
        return mNearestMove;
    }

    public int getChessBoardSize() {
        return mSizeOfBoardNxN;
    }
}
