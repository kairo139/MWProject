package com.example.mwproject;

public class ListViewItem {
    private String titleStr;
    private String epiStr;
    private String thumbStr;

    public void setTitle(String title){
        titleStr = title;
    }
    public void setEpi(String epi){
        epiStr = epi;
    }
    public void setThumb(String thumb){
        thumbStr = thumb;
    }

    public String getTitle(){
        return this.titleStr;
    }
    public String getEpiStr(){
        return this.epiStr;
    }
    public String getThumb(){
        return this.thumbStr;
    }
}
