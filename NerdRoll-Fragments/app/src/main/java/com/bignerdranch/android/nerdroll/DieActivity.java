package com.bignerdranch.android.nerdroll;

import android.support.v4.app.Fragment;

public class DieActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        int index = getIntent().getIntExtra(DieFragment.EXTRA_INDEX, 0);
        return DieFragment.newInstance(index);
    }

}