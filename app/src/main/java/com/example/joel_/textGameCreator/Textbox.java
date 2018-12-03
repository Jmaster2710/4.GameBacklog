package com.example.joel_.textGameCreator;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "textbox")
public class Textbox {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "text")
    private String mText;


    public Textbox(String mName, String mText) {
        this.mName = mName;
        this.mText = mText;
    }

    //Getters
    public String getName() {
        return mName;
    }

    public String getText() {
        return mText;
    }

    public long getId() {
        return id;
    }

    //Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setText(String mText) {
        this.mText = mText;
    }
}
