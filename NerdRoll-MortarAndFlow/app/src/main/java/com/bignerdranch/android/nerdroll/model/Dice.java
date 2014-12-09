package com.bignerdranch.android.nerdroll.model;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Arrays.asList;

@Singleton
public class Dice {

    private final List<Die> mDieList;

    @Inject
    Dice() {
        mDieList = Collections.unmodifiableList(asList(
                new Die(this, 3),
                new Die(this, 4),
                new Die(this, 6),
                new Die(this, 8),
                new Die(this, 10),
                new Die(this, 12),
                new Die(this, 20),
                new Die(this, 30),
                new Die(this, 100)
        ));
    }

    public List<Die> getAll() {
        return mDieList;
    }

}