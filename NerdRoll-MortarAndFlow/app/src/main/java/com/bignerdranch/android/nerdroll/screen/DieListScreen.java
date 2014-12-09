package com.bignerdranch.android.nerdroll.screen;

import android.os.Bundle;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.model.DieList;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.util.MainScope;
import com.bignerdranch.android.nerdroll.view.DieListView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.Flow;
import flow.Layout;
import mortar.Blueprint;
import mortar.ViewPresenter;

@Layout(R.layout.view_die_list)
public class DieListScreen implements Blueprint {

    @Override
    public String getMortarScopeName() {
        return getClass().getName();
    }

    @Override
    public Object getDaggerModule() {
        return new Module();
    }

    @dagger.Module(injects = DieListView.class, addsTo = MainScreen.Module.class)
    static class Module {
        @Provides
        List<Die> provideDieList(DieList dieList) {
            return dieList.asList();
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<DieListView> {

        private final ActionBarOwner mActionBarOwner;
        private final Flow mFlow;

        private final List<Die> mDieList;

        @Inject
        public Presenter(List<Die> dieList, @MainScope Flow flow, ActionBarOwner actionBarOwner) {
            this.mDieList = dieList;
            this.mFlow = flow;
            this.mActionBarOwner = actionBarOwner;
        }

        @Override
        public void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            DieListView view = getView();
            if (view == null) {
                return;
            }
            view.showDice(mDieList);
        }

        public void onDieSelected(int position) {
            mFlow.goTo(new DieScreen(position));
        }

    }

}