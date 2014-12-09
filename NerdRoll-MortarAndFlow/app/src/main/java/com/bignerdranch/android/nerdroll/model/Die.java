package com.bignerdranch.android.nerdroll.model;

public class Die {

    private final int mSides;

    private Dice mDice;

    Die(Dice dice, int sides) {
        mDice = dice;
        mSides = sides;
    }

    public int getSides() {
        return mSides;
    }

    @Override
    public String toString() {
        return "1d" + mSides;
    }

}