package com.example.mwproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ImageView ivThumb;
    private TextView tvEpi;
    private TextView tvTitle;
    private TextView tvCase;
    private TextView tvContent;

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

        return convertView;
    }
}
