package com.bignerdranch.android.nerdroll.screen;

import android.os.Bundle;
import android.view.MenuItem;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.model.DieList;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.util.MainScope;
import com.bignerdranch.android.nerdroll.view.DieView;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.Flow;
import flow.HasParent;
import flow.Layout;
import mortar.Blueprint;
import mortar.ViewPresenter;
import rx.functions.Action0;

@Layout(R.layout.view_die)
public class DieScreen implements HasParent<DieListScreen>, Blueprint {

    private final int mDieIndex;

    public DieScreen(int mDieIndex) {
        this.mDieIndex = mDieIndex;
    }

    @Override
    public String getMortarScopeName() {
        return "DieScreen{" + "mDieIndex=" + mDieIndex + '}';
    }

    @Override
    public Object getDaggerModule() {
        return new Module();
    }

    @Override
    public DieListScreen getParent() {
        return new DieListScreen();
    }

    @dagger.Module(injects = DieView.class, addsTo = MainScreen.Module.class)
    public class Module {
        @Provides
        Die provideDie(DieList dieList) {
            return dieList.get(mDieIndex);
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<DieView> {

        private final ActionBarOwner mActionBarOwner;
        private final Flow mFlow;

        private final Die mDie;

        @Inject
        public Presenter(Die mDie, @MainScope Flow flow, ActionBarOwner actionBarOwner) {
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
            v.updateRandomText(mDie.toString());
        }

    }

}