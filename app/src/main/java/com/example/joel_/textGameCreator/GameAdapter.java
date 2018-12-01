package com.example.joel_.textGameCreator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private Context context;
    private List<Game> mGames;

    final private GameClickListener mGameClickListener;

    public interface GameClickListener{
        void gameOnClick (int i);
    }

    public GameAdapter(GameClickListener mGameClickListener, List<Game> listGameObject) {
        //this.context = context;
        this.mGames = listGameObject;
        this.mGameClickListener = mGameClickListener;
    }

    public void swapList (List<Game> newList) {
        mGames = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridcell, parent, false);
        // Return a new holder instance
        GameAdapter.GameViewHolder gameViewHolder = new GameAdapter.GameViewHolder(view);
        return gameViewHolder;
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public String mUrlName;
        public View view;
        public TextView mTextTitle, mTextPlatform, mTextStatus, mTextDate;

        public GameViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            mTextTitle = itemView.findViewById(R.id.gameTitle);
            mTextPlatform = itemView.findViewById(R.id.gamePlatform);
            mTextStatus = itemView.findViewById(R.id.gameStatus);
            mTextDate = itemView.findViewById(R.id.currentDate);

            view = itemView;

        }
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mGameClickListener.gameOnClick(clickedPosition);
        }

    }
/*
    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridcell, parent, false);

        return new GameViewHolder(view);
    } */


    @Override
    public void onBindViewHolder(@NonNull final GameViewHolder holder, final int position) {
        // Gets a single item in the list from its position
        final Game gameObject = mGames.get(position);
        holder.mTextTitle.setText(gameObject.getTitle());
        holder.mTextPlatform.setText(gameObject.getPlatform());
        holder.mTextStatus.setText(gameObject.getStatus());
        holder.mTextDate.setText(gameObject.getDate());
    }


    @Override
    public int getItemCount() {
        if (mGames != null) {
            return mGames.size();
        } else
        {
            return 0;
        }
    }
}