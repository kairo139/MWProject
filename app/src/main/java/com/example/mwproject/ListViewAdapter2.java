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

public class ListViewAdapter2 extends BaseAdapter {
    private ImageView ivThumb;
    private TextView tvContent;
    private TextView tvTitle;
    private TextView tvCase;
    private TextView tvRank;

    private  ArrayList<ListViewItem2> listViewItemList2 = new ArrayList<ListViewItem2>();

    public ListViewAdapter2(){

    }

    @Override
    public int getCount() {
        return listViewItemList2.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList2.get(position);
    }

    public void addItem2(String title, String actor, String content, String thumb, String rank){
        ListViewItem2 item2 = new ListViewItem2();

        item2.setTitle(title);
        item2.setActor(actor);
        item2.setThumb(thumb);
        item2.setContent(content);
        item2.setRank(rank);

        listViewItemList2.add(item2);
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
            convertView = inflater.inflate(R.layout.ranking_listitem,parent,false);
        }

        ivThumb = (ImageView)convertView.findViewById(R.id.ivRank);
        tvTitle = (TextView)convertView.findViewById(R.id.tvRank_WDTitle);
        tvCase = (TextView)convertView.findViewById(R.id.tvRank_WDCase);
        tvContent = (TextView)convertView.findViewById(R.id.tvRank_WDContent);
        tvRank = (TextView)convertView.findViewById(R.id.tvNum1);

        ListViewItem2 listViewItem2 = listViewItemList2.get(position);

        tvTitle.setText(listViewItem2.getTitle());
        tvCase.setText(listViewItem2.getActor());
        tvContent.setText(listViewItem2.getContent());
        tvRank.setText(listViewItem2.getRank());
        Glide.with(context).load(listViewItem2.getThumb()).into(ivThumb);

        return convertView;
    }

    public void clearList(){
        listViewItemList2.clear();
    }
}
