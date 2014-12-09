package com.bignerdranch.android.nerdroll.screen;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.model.Dice;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.view.DiceView;

import java.util.List;

import dagger.Provides;
import flow.Layout;
import mortar.Blueprint;

@Layout(R.layout.view_dice)
public class DiceScreen implements Blueprint {

    @Override
    public String getMortarScopeName() {
        return getClass().getName();
    }

    @Override
    public Object getDaggerModule() {
        return new Module();
    }

    @dagger.Module(injects = DiceView.class, addsTo = MainScreen.Module.class)
    static class Module {
        @Provides
        List<Die> provideDice(Dice dice) {
            return dice.getAll();
        }
    }

}
