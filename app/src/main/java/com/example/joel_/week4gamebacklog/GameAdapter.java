package com.example.joel_.week4gamebacklog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private Context context;
    public List<Game> listGames;


    public GameAdapter(Context context, List<Game> listGameObject) {

        this.context = context;

        this.listGames = listGameObject;

    }

    @Override

    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridcell, parent, false);

        return new GameViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final GameViewHolder holder, final int position) {

        // Gets a single item in the list from its position

        final Game portalObject = listGames.get(position);

        // The holder argument is used to reference the views inside the viewHolder

        // Populate the views with the data from the list
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddingGameActivity.class);
                //intent.putExtra("Url", portalObject.getmLinkUrl());
                context.startActivity(intent);

            }
        });
       // holder.mButton.setText(portalObject.getmLinkName());


    }

    @Override
    public int getItemCount() {

        return listGames.size();
    }
}