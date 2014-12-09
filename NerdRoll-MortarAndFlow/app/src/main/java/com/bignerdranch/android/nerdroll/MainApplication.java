package com.bignerdranch.android.nerdroll;

import android.app.Application;

import dagger.ObjectGraph;
import mortar.Mortar;
import mortar.MortarScope;

public class MainApplication extends Application {

    private MortarScope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();
        rootScope = Mortar.createRootScope(BuildConfig.DEBUG, ObjectGraph.create(new MainApplicationModule()));
    }

    @Override
    public Object getSystemService(String name) {
        if (Mortar.isScopeSystemService(name)) {
            return rootScope;
        }
        return super.getSystemService(name);
    }

}