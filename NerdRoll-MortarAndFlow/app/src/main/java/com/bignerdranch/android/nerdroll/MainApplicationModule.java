package com.bignerdranch.android.nerdroll;

import com.bignerdranch.android.nerdroll.util.GsonParcer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flow.Parcer;

@Module(library = true)
public class MainApplicationModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Parcer<Object> provideParcer(Gson gson) {
        return new GsonParcer<>(gson);
    }

}