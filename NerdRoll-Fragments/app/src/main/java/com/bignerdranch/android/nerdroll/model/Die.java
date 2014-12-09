package com.bignerdranch.android.nerdroll.model;

public class Die {

    private final int mSides;

    private DieList mDieList;

    Die(DieList dieList, int sides) {
        mDieList = dieList;
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