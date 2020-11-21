package com.example.mwproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ThumbAdapter extends BaseAdapter {
    Bitmap[] dataSource;
    LayoutInflater minflater;
    Activity parentActivity;
    int itemLayout;
    Context TA;

    public ThumbAdapter(Activity activity, int layout, Bitmap[] ds) {
        parentActivity = activity;
        itemLayout = layout;
        dataSource = ds;
    }

    @Override
    public int getCount() {
        return dataSource.length;
    }

    @Override
    public Object getItem(int position) {
        return dataSource[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view;

        if(convertView!=null){
            view=(ImageView)convertView;
        }
        else{
            view=(ImageView)minflater.inflate(itemLayout,parent,false);
            view.setImageBitmap(dataSource[position]);

        }
        return view;
    }

}
