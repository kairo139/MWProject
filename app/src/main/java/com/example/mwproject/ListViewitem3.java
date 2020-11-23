package com.example.mwproject;

public class ListViewitem3 {
    private String titleStr;
    private String actorStr;
    private String contentStr;
    private String thumbStr;

    public void setTitle(String title){
        titleStr = title;
    }
    public void setActor(String actor){
        actorStr = actor;
    }
    public void setContent(String content){
        contentStr = content;
    }
    public void setThumb(String thumb){
        thumbStr = thumb;
    }

    public String getTitle(){
        return this.titleStr;
    }
    public String getActor(){
        return this.actorStr;
    }
    public String getContent(){
        return this.contentStr;
    }
    public String getThumb(){
        return this.thumbStr;
    }
}
