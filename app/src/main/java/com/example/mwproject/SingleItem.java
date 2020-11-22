package com.example.mwproject;

import androidx.annotation.NonNull;

public class SingleItem {
    String name;
    String mobile;
    int resId;

    public SingleItem(String name, String mobile, int resId){
        this.name = name;
        this.mobile = mobile;
        this.resId = resId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public int getResId(){
        return resId;
    }

    @NonNull
    @Override
    public String toString() {
        return "SingerItem{"+
                "name='"+name + '\'' +
                ", mobile='"+mobile + '\''+
                '}';

    }
}
