package com.example.two.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.two.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View collection = inflater.inflate(R.layout.fragment_collection, null);
        return collection;
    }

}
