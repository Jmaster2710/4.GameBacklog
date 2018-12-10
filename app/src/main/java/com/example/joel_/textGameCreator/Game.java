package com.example.joel_.textGameCreator;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity(tableName = "game")
public class Game{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "text")
    private String mText;


    public Game(String mName, String mText)
    {
        this.mName = mName;
        this.mText = mText;
    }

    //Getters
    public String getName()
    {
        return mName;
    }

    public String getText()
    {
        return mText;
    }

    public long getId()
    {
        return id;
    }

    //Setters
    public void setId(long id)
    {
        this.id = id;
    }
    public void setTitle(String mTitle)
    {
        this.mName = mTitle;
    }
    public void setPlatform(String mPlatform)
    {
        this.mText = mPlatform;
    }

}
