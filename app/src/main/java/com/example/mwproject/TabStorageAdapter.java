package com.example.mwproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TabStorageAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<TabStorageVO> sample;

    public TabStorageAdapter(Context context, ArrayList<TabStorageVO> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public TabStorageVO getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.tabstorage_custom, null);

        TextView movieName = (TextView)view.findViewById(R.id.TabStorageTitle);
        TextView grade = (TextView)view.findViewById(R.id.TabStorageSubTitle);

        movieName.setText(sample.get(position).getDramaName());
        grade.setText(sample.get(position).getContent());

        return view;
    }
}
