package com.bignerdranch.android.nerdroll.presenter;

import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.screen.DiceScreen;
import com.bignerdranch.android.nerdroll.util.FlowOwner;
import com.bignerdranch.android.nerdroll.view.MainView;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import flow.HasParent;
import flow.Parcer;
import mortar.Blueprint;

@Singleton
public class MainPresenter extends FlowOwner<Blueprint, MainView> {

    private final ActionBarOwner actionBarOwner;

    @Inject
    public MainPresenter(Parcer<Object> flowParcer, ActionBarOwner actionBarOwner) {
        super(flowParcer);
        this.actionBarOwner = actionBarOwner;
    }

    @Override
    public void showScreen(Blueprint newScreen, Flow.Direction direction) {
        boolean hasUp = newScreen instanceof HasParent;
        String title = "NerdRoll";
        ActionBarOwner.MenuAction menu = null;
        actionBarOwner.setConfig(new ActionBarOwner.Config(false, hasUp, title, menu));
        super.showScreen(newScreen, direction);
    }

    @Override
    protected Blueprint getFirstScreen() {
        return new DiceScreen();
    }

}
