package com.bignerdranch.android.nerdroll.screen;

import com.bignerdranch.android.nerdroll.MainApplicationModule;
import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.util.FlowOwner;
import com.bignerdranch.android.nerdroll.view.MainView;
import com.bignerdranch.android.nerdroll.android.ActionBarModule;
import com.bignerdranch.android.nerdroll.util.MainScope;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.Flow;
import flow.HasParent;
import flow.Parcer;
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
        Flow provideFlow(Presenter presenter) {
            return presenter.getFlow();
        }

    }

    @Singleton
    public static class Presenter extends FlowOwner<Blueprint, MainView> {

        private final ActionBarOwner mActionBarOwner;
        private String mTitle;

        @Inject
        public Presenter(Parcer<Object> flowParcer, ActionBarOwner actionBarOwner) {
            super(flowParcer);
            this.mActionBarOwner = actionBarOwner;
        }

        @Override
        public void showScreen(Blueprint newScreen, Flow.Direction direction) {
            boolean hasUp = newScreen instanceof HasParent;
            ActionBarOwner.MenuAction menu = null;
            mActionBarOwner.setConfig(new ActionBarOwner.Config(false, hasUp, mTitle, menu));
            super.showScreen(newScreen, direction);
        }

        @Override
        protected Blueprint getFirstScreen() {
            return new DieListScreen();
        }

        public void setTitle(String title) {
            mTitle = title;
        }

    }

}