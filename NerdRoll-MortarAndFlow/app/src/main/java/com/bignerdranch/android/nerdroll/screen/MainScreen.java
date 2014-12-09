package com.bignerdranch.android.nerdroll.screen;

import com.bignerdranch.android.nerdroll.MainApplicationModule;
import com.bignerdranch.android.nerdroll.view.MainView;
import com.bignerdranch.android.nerdroll.android.ActionBarModule;
import com.bignerdranch.android.nerdroll.presenter.MainPresenter;
import com.bignerdranch.android.nerdroll.util.MainScope;

import dagger.Provides;
import flow.Flow;
import mortar.Blueprint;

public class MainScreen implements Blueprint {

    @Override
    public String getMortarScopeName() {
        return getClass().getName();
    }

    @Override
    public Object getDaggerModule() {
        return new Module();
    }

    @dagger.Module(
            includes = ActionBarModule.class,
            injects = MainView.class,
            addsTo = MainApplicationModule.class,
            library = true
    )
    public static class Module {

        @Provides
        @MainScope
        Flow provideFlow(MainPresenter presenter) {
            return presenter.getFlow();
        }

    }

}