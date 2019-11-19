package com.example.two.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.two.DbUtils;
import com.example.two.R;
import com.example.two.adapter.HomeAdapter;
import com.example.two.bean.HomeBean;
import com.example.two.bean.StudentBean;
import com.example.two.prestenter.ImpHomePrestenter;
import com.example.two.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements HomeView, HomeAdapter.ItemLongClick {

    private RecyclerView mRecycler;
    private List<HomeBean.RecentBean> list;
    private HomeAdapter adapter;
    Button mBtnOk;
    Button mBtnNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.fragment_home, null);
        initView(home);
        initData();
        return home;
    }

    private void initData() {
        ImpHomePrestenter impHomePrestenter = new ImpHomePrestenter(this);
        impHomePrestenter.getData();
    }

    private void initView(View home) {
        mRecycler = home.findViewById(R.id.home_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        list = new ArrayList<>();
        adapter = new HomeAdapter(list, getActivity());
        mRecycler.setAdapter(adapter);

        adapter.setItemLongClick(this);

    }

    @Override
    public void onSucess(HomeBean homeBean) {
        List<HomeBean.RecentBean> data = homeBean.getRecent();
        String title = data.get(1).getTitle();
        Log.e(TAG, "onSucess: " + title);
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFaile(String error) {
        Log.e(TAG, "onFaile: " + error);
    }

    @Override
    public void itemLongClick(final int pos) {
        Toast.makeText(getActivity(), "长按了item", Toast.LENGTH_SHORT).show();
        final View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_pop, null);
         final PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);


        mBtnOk = (Button) inflate.findViewById(R.id.btn_ok);
        mBtnNo = (Button) inflate.findViewById(R.id.btn_no);
        mBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentBean studentBean = new StudentBean();
                studentBean.setId(Long.valueOf(pos));
                studentBean.setText(list.get(pos).getNews_id()+"");
                studentBean.setTitle(list.get(pos).getTitle());
                studentBean.setUrl(list.get(pos).getThumbnail());
                long insert = DbUtils.getDbUtils().insert(studentBean);
                if (insert>=0){
                    Toast.makeText(getActivity(), "已添加", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();
                }

                popupWindow.dismiss();
            }
        });
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(mRecycler, Gravity.CENTER,0, 0);
    }
}
