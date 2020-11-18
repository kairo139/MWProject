package com.example.mwproject;

public class TabStorageVO {
    private int poster;
    private String dramaName;
    private String content;
    private String dramaVideoID;

    public TabStorageVO(int poster, String dramaName, String content, String dramaVideoID){
        this.poster = poster;
        this.dramaName = dramaName;
        this.content = content;
        this.dramaVideoID = dramaVideoID;
    }

    public int getPoster()
    {
        return this.poster;
    }

    public String getDramaName()
    {
        return this.dramaName;
    }

    public String getDramaVideoID(){ return this.dramaVideoID;}

    public String getContent()
    {
        return this.content;
    }
}
