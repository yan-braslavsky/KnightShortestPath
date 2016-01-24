package com.company;

/**
 * Created by yan.braslavski on 1/24/16.
 *
 * To have some degree of freedom I've encapsulated some problem related
 * conditions in order to scale the problem with ease.
 */
public class ProblemConditions {
    private final int mSizeOfBoardNxN;
    private final int mNearestMove;
    private final int mFareMostMove;

    /**
     * When Knight moves , he moves 2 squares and then 1 square perpendicularly.
     * To generify this , I am saying it moves a "big" distance and then smaller distance perpendicularly.
     * @param sizeOfBoardNxN standard board is 8x8 , but yours can be different
     * @param nearestMove the smaller part of knight step
     * @param fareMostMove the bigger part of knight step
     */
    public ProblemConditions(int sizeOfBoardNxN, int nearestMove, int fareMostMove) {
        mSizeOfBoardNxN = sizeOfBoardNxN;
        mNearestMove = nearestMove;
        mFareMostMove = fareMostMove;
    }

    /**
     * @return true if the position within bounds of chessboard
     */
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
