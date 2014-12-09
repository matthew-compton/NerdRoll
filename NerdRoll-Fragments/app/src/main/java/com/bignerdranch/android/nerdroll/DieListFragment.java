package com.bignerdranch.android.nerdroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.android.nerdroll.model.Die;
import com.bignerdranch.android.nerdroll.model.DieList;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DieListFragment extends BaseFragment {

    @InjectView(R.id.fragment_die_list_list) ListView mListView;
    @Inject DieList mDieList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_die_list, container, false);
        ButterKnife.inject(this, layout);

        DieListAdapter dieListAdapter = new DieListAdapter(getActivity(), mDieList.asList());
        mListView.setAdapter(dieListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DieActivity.class);
                intent.putExtra(DieFragment.EXTRA_INDEX, position);
                startActivity(intent);
            }
        });

        return layout;
    }

    private static class DieListAdapter extends ArrayAdapter<Die> {
        public DieListAdapter(Context context, List<Die> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }

}