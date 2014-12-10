package com.bignerdranch.android.nerdroll.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.nerdroll.MainApplication;
import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.model.DieList;

import java.util.Random;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DieFragment extends Fragment {

    public static final String EXTRA_INDEX = DieFragment.class.getPackage() + ".EXTRA_INDEX";

    @InjectView(R.id.fragment_die_random) TextView mRandomTextView;
    @InjectView(R.id.fragment_die_value) TextView mValueTextView;

    @Inject DieList mDieList;
    private Die mDie;

    public static DieFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_INDEX, index);

        DieFragment fragment = new DieFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.get(getActivity()).inject(this);
        int index = getArguments().getInt(EXTRA_INDEX, 0);
        mDie = mDieList.get(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_die, container, false);
        ButterKnife.inject(this, layout);
        setHasOptionsMenu(true);

        updateRandomText(mDie.toString());

        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_die, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_die_roll:
                roll();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateRandomText(CharSequence text) {
        mRandomTextView.setText(text);
    }

    public void updateValueText(CharSequence text) {
        mValueTextView.setText(text);
    }

    public void roll() {
        Random r = new Random();
        int num = r.nextInt(mDie.getSides()) + 1;
        updateValueText(Integer.toString(num));
    }

    @OnClick(R.id.fragment_die_container)
    public void onClickContainer() {
        roll();
    }

}