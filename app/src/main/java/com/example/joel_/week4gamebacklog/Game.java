package com.example.joel_.week4gamebacklog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "game")
public class Game{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    private String mTitle;
    private String mPlatform;
    private String mNotes;
    private String mStatus;


    public Game(String mTitle, String mPlatform, String mNotes, String mStatus)
    {
        this.mTitle = mTitle;
        this.mPlatform = mPlatform;
        this.mNotes = mNotes;
        this.mStatus = mStatus;
    }

    //Getters
    public String getTitle()
    {
        return mTitle;
    }

    public String getPlatform()
    {
        return mPlatform;
    }

    public long getId()
    {
        return id;
    }

    public String getNotes()
    {
        return mNotes;
    }

    public String getStatus()
    {
        return mStatus;
    }

    //Setters
    public void setId(long id)
    {
        this.id = id;
    }

    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }
    public void setPlatform(String mPlatform)
    {
        this.mPlatform = mPlatform;
    }
    public void setNotes(String mNotes)
    {
        this.mNotes = mNotes;
    }
    public void setStatus(String mStatus)
    {
        this.mStatus = mStatus;
    }

}
