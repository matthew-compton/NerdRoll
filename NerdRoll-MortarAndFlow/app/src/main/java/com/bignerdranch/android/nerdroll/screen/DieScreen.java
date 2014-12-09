package com.bignerdranch.android.nerdroll.screen;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.model.Dice;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.view.DieView;

import dagger.Provides;
import flow.HasParent;
import flow.Layout;
import mortar.Blueprint;

@Layout(R.layout.view_die)
public class DieScreen implements HasParent<DiceScreen>, Blueprint {

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
    public DiceScreen getParent() {
        return new DiceScreen();
    }

    @dagger.Module(injects = DieView.class, addsTo = MainScreen.Module.class)
    public class Module {
        @Provides
        Die provideConversation(Dice dice) {
            return dice.getAll().get(mDieIndex);
        }
    }

}
