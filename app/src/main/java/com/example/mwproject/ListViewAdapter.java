package com.example.mwproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ImageView ivThumb;
    private TextView tvEpi;
    private TextView tvTitle;

    private  ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(String epi, String title, String thumb){
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setEpi(epi);
        item.setThumb(thumb);

        listViewItemList.add(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recom_listitem,parent,false);
        }

        ivThumb = (ImageView)convertView.findViewById(R.id.ivThumb);
        tvTitle = (TextView)convertView.findViewById(R.id.tv_subTitle);
        tvEpi = (TextView)convertView.findViewById(R.id.tv_epi);

        ListViewItem listViewItem = listViewItemList.get(position);

        tvTitle.setText(listViewItem.getTitle());
        tvEpi.setText(listViewItem.getEpiStr());
        Glide.with(context).load(listViewItem.getThumb()).into(ivThumb);

        return convertView;
    }
}
