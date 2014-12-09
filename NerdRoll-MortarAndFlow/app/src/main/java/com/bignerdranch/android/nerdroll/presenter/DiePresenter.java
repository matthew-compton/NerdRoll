package com.bignerdranch.android.nerdroll.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.util.MainScope;
import com.bignerdranch.android.nerdroll.view.DieView;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import mortar.ViewPresenter;
import rx.functions.Action0;

@Singleton
public class DiePresenter extends ViewPresenter<DieView> {

    private final ActionBarOwner mActionBarOwner;
    private final Flow mFlow;

    private final Die mDie;

    @Inject
    public DiePresenter(Die mDie, @MainScope Flow flow, ActionBarOwner actionBarOwner) {
        this.mDie = mDie;
        this.mFlow = flow;
        this.mActionBarOwner = actionBarOwner;
    }

    @Override
    public void onLoad(Bundle savedInstanceState) {
        DieView v = getView();
        if (v == null) {
            return;
        }

        ActionBarOwner.Config actionBarConfig = mActionBarOwner.getConfig();
        actionBarConfig = actionBarConfig.withAction(new ActionBarOwner.MenuAction(R.string.roll, android.R.drawable.ic_menu_compass, MenuItem.SHOW_AS_ACTION_NEVER, new Action0() {
            @Override
            public void call() {
                v.roll(mDie.getSides());
            }
        }));
        mActionBarOwner.setConfig(actionBarConfig);

        v.setOnClickListener(v1 -> v.roll(mDie.getSides()));
        v.updateDieText(mDie.toString());
    }

}
