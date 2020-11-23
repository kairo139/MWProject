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

public class ListViewAdapter3 extends BaseAdapter {
    private ImageView ivThumb;
    private TextView tvContent;
    private TextView tvTitle;
    private TextView tvCase;

    private  ArrayList<ListViewitem3> listViewItemList3 = new ArrayList<ListViewitem3>();

    public ListViewAdapter3(){

    }

    @Override
    public int getCount() {
        return listViewItemList3.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList3.get(position);
    }

    public void addItem3(String title, String actor, String content, String thumb){
        ListViewitem3 item3 = new ListViewitem3();

        item3.setTitle(title);
        item3.setActor(actor);
        item3.setThumb(thumb);
        item3.setContent(content);

        listViewItemList3.add(item3);
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
            convertView = inflater.inflate(R.layout.genre_listitem,parent,false);
        }

        ivThumb = (ImageView)convertView.findViewById(R.id.ivGenreListItem);
        tvTitle = (TextView)convertView.findViewById(R.id.tvGenreTitle);
        tvCase = (TextView)convertView.findViewById(R.id.tvGenreCase);
        tvContent = (TextView)convertView.findViewById(R.id.tvGenreContent);

        ListViewitem3 listViewItem3 = listViewItemList3.get(position);

        tvTitle.setText(listViewItem3.getTitle());
        tvCase.setText(listViewItem3.getActor());
        tvContent.setText(listViewItem3.getContent());

        Glide.with(context).load(listViewItem3.getThumb()).into(ivThumb);

        return convertView;
    }

    public void clearList(){
        listViewItemList3.clear();
    }
}
