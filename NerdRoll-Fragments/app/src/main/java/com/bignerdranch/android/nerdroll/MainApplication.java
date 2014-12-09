package com.bignerdranch.android.nerdroll;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public class MainApplication extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(new MainApplicationModule(this));
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    public final void inject(Object object) {
        mObjectGraph.inject(object);
    }

}
