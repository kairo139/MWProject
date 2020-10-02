package com.example.mwproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context){
        super(context, "PDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // User Table
        db.execSQL("CREATE TABLE User (User_ID VARCHAR(20) PRIMARY KEY, PW VARCHAR(10), NickNAME VARCHAR(10), Birth DATE, Gender VARCHAR(1), Hint VARCHAR(10));");
        db.execSQL("INSERT INTO User VALUES ('KIM', '0000', 'DUCK', 1997-03-25, 'M', '명지전문대학');");

        // WebDrama Table
        db.execSQL("CREATE TABLE WebDrama (Video_ID VARCHAR(20) PRIMARY KEY, Genre INT, Recommendation INT, Lookup INT, Title VARCHAR(20), Date DATE, Episode INT);");
        db.execSQL("INSERT INTO WebDrama VALUES ('');");

        // ViewRecord Table
        db.execSQL("CREATE TABLE ViewRecord (Num INT PRIMARY KEY, User_ID VARCHAR(20), Video_ID VARCHAR(20), Date DATE, Time INT, FOREIGN KEY (User_ID) REFERENCES User(User_ID), FOREIGN KEY (Video_ID) REFERENCES WebDrama(Video_ID));");


        // Preference Table
        db.execSQL("CREATE TABLE Preference (Num INT PRIMARY KEY, User_ID VARCHAR(20), Name VARCHAR(10), FOREIGN KEY (User_ID) REFERENCES User(User_ID));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
