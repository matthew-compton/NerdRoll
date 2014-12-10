package com.bignerdranch.android.nerdroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.screen.MainScreen;
import com.bignerdranch.android.nerdroll.util.CanShowScreen;
import com.bignerdranch.android.nerdroll.util.ScreenConductor;

import javax.inject.Inject;

import flow.Flow;
import mortar.Blueprint;
import mortar.Mortar;

public class MainView extends FrameLayout implements CanShowScreen<Blueprint> {

    @Inject MainScreen.Presenter presenter;
    private final ScreenConductor<Blueprint> screenMaestro;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Mortar.inject(context, this);
        screenMaestro = new ScreenConductor<Blueprint>(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        presenter.setTitle(getContext().getString(R.string.app_name));
        presenter.takeView(this);
    }

    public Flow getFlow() {
        return presenter.getFlow();
    }

    @Override
    public void showScreen(Blueprint screen, Flow.Direction direction) {
        screenMaestro.showScreen(screen, direction);
    }

}