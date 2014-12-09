package com.bignerdranch.android.nerdroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.presenter.DicePresenter;

import java.util.List;

import javax.inject.Inject;

import mortar.Mortar;

public class DiceView extends ListView {

    @Inject DicePresenter presenter;

    public DiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Mortar.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }

    public void showDice(List<Die> dieList) {
        Adapter adapter = new Adapter(getContext(), dieList);
        setAdapter(adapter);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onDieSelected(position);
            }
        });
    }

    private static class Adapter extends ArrayAdapter<Die> {
        public Adapter(Context context, List<Die> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }

}
