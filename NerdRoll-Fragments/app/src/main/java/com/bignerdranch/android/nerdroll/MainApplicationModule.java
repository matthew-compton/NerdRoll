package com.bignerdranch.android.nerdroll;

import com.bignerdranch.android.nerdroll.controller.DieFragment;
import com.bignerdranch.android.nerdroll.controller.DieListFragment;
import com.bignerdranch.android.nerdroll.model.DieList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                MainActivity.class,
                DieListFragment.class,
                DieFragment.class
        },
        complete = true)
public class MainApplicationModule {

    private final MainApplication mApplication;

    public MainApplicationModule(MainApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public DieList provideDieList() {
        return new DieList();
    }

}
