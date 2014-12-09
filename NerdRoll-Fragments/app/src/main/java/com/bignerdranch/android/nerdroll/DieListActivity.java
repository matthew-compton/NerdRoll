package com.bignerdranch.android.nerdroll;

import android.support.v4.app.Fragment;

public class DieListActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new DieListFragment();
    }

}