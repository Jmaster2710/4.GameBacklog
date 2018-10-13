package com.example.joel_.week4gamebacklog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameViewHolder extends RecyclerView.ViewHolder {

    public String mUrlName;
    public Button mButton;
    public View view;

    public GameViewHolder(View itemView) {

        super(itemView);

       // mButton = itemView.findViewById(R.id.urlButton);

        view = itemView;

    }
}