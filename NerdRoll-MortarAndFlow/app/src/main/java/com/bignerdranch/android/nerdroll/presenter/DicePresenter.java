package com.bignerdranch.android.nerdroll.presenter;

import android.os.Bundle;

import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.screen.DieScreen;
import com.bignerdranch.android.nerdroll.util.MainScope;
import com.bignerdranch.android.nerdroll.view.DiceView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import mortar.ViewPresenter;

@Singleton
public class DicePresenter extends ViewPresenter<DiceView> {

    private final ActionBarOwner mActionBarOwner;
    private final Flow mFlow;

    private final List<Die> mDieList;

    @Inject
    public DicePresenter(List<Die> dieList, @MainScope Flow flow, ActionBarOwner actionBarOwner) {
        this.mDieList = dieList;
        this.mFlow = flow;
        this.mActionBarOwner = actionBarOwner;
    }

    @Override
    public void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
        DiceView view = getView();
        if (view == null) {
            return;
        }
        view.showDice(mDieList);
    }

    public void onDieSelected(int position) {
        mFlow.goTo(new DieScreen(position));
    }

}
