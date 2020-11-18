package com.example.mwproject;

public class TabStorageVO {
    private int poster;
    private String dramaName;
    private String content;

    public TabStorageVO(int poster, String dramaName, String content){
        this.poster = poster;
        this.dramaName = dramaName;
        this.content = content;
    }

    public int getPoster()
    {
        return this.poster;
    }

    public String getDramaName()
    {
        return this.dramaName;
    }

    public String getContent()
    {
        return this.content;
    }
}
