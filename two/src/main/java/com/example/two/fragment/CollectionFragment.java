package com.example.two.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.two.DbUtils;
import com.example.two.R;
import com.example.two.adapter.CollectionAdapter;
import com.example.two.bean.StudentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {

    private RecyclerView mRecycler;
    private List<StudentBean> list;
    private CollectionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View collection = inflater.inflate(R.layout.fragment_collection, null);
        initView(collection);
        initData();
        return collection;
    }


    private void initData() {
        adapter.notifyDataSetChanged();
        List<StudentBean> beans = DbUtils.getDbUtils().loadAll();
        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    private void initView(View collection) {
        mRecycler = collection.findViewById(R.id.collection_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        list = new ArrayList<>();

        adapter = new CollectionAdapter(list, getActivity());
        mRecycler.setAdapter(adapter);

    }

}
