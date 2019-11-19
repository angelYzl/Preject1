package com.example.two;

import android.os.Bundle;

import com.example.two.adapter.ViewPagerAdapter;
import com.example.two.fragment.CollectionFragment;
import com.example.two.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTable = (TabLayout) findViewById(R.id.table);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollectionFragment());
        ArrayList<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("收藏");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mVp.setAdapter(adapter);
        mTable.setupWithViewPager(mVp);


    }
}
