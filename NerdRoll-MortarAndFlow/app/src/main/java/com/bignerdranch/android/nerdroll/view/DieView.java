package com.bignerdranch.android.nerdroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.presenter.DiePresenter;

import java.util.Random;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mortar.Mortar;

public class DieView extends LinearLayout {

    @Inject DiePresenter presenter;

    @InjectView(R.id.view_die_text) public TextView mDieTextView;
    @InjectView(R.id.view_die_random) public TextView mRandomTextView;

    public DieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Mortar.inject(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }

    public void updateDieText(CharSequence text) {
        mDieTextView.setText(text);
    }

    public void updateRandomText(CharSequence text) {
        mRandomTextView.setText(text);
    }

    public void roll(int sides) {
        Random r = new Random();
        int num = r.nextInt(sides) + 1;
        updateRandomText(Integer.toString(num));
    }

}
