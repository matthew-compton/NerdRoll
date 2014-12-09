package com.bignerdranch.android.nerdroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.screen.DieScreen;

import java.util.Random;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mortar.Mortar;

public class DieView extends LinearLayout {

    @Inject DieScreen.Presenter presenter;

    @InjectView(R.id.view_die_random) public TextView mRandomTextView;
    @InjectView(R.id.view_die_value) public TextView mValueTextView;

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

    public void updateRandomText(CharSequence text) {
        mRandomTextView.setText(text);
    }

    public void updateValueText(CharSequence text) {
        mValueTextView.setText(text);
    }

    public void roll(int sides) {
        Random r = new Random();
        int num = r.nextInt(sides) + 1;
        updateValueText(Integer.toString(num));
    }

}
