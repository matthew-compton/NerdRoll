package com.bignerdranch.android.nerdroll.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.android.nerdroll.MainApplication;
import com.bignerdranch.android.nerdroll.R;
import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.model.DieList;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DieListFragment extends Fragment {

    @InjectView(R.id.fragment_die_list_list) ListView mListView;
    @Inject DieList mDieList;

    private DieListCallback mDieListCallback;

    public interface DieListCallback {
        void onDieSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.get(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_die_list, container, false);
        ButterKnife.inject(this, layout);

        DieListAdapter dieListAdapter = new DieListAdapter(getActivity(), mDieList.asList());
        mListView.setAdapter(dieListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDieListCallback.onDieSelected(position);
            }
        });

        return layout;
    }

    private static class DieListAdapter extends ArrayAdapter<Die> {
        public DieListAdapter(Context context, List<Die> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mDieListCallback = (DieListCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDieListCallback = null;
    }

}