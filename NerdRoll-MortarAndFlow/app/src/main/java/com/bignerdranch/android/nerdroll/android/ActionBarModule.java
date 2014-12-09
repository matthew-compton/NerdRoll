package com.bignerdranch.android.nerdroll.android;

import com.bignerdranch.android.nerdroll.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = MainActivity.class)
public class ActionBarModule {

    @Provides
    @Singleton
    ActionBarOwner provideActionBarOwner() {
        return new ActionBarOwner();
    }

}
