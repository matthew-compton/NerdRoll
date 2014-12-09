package com.bignerdranch.android.nerdroll.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DieList {

    private final List<Die> mDieList;

    @Inject
    DieList() {
        mDieList = Collections.unmodifiableList(Arrays.asList(
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

    public List<Die> asList() {
        return mDieList;
    }

    public Die get(int position) {
        return mDieList.get(position);
    }

}