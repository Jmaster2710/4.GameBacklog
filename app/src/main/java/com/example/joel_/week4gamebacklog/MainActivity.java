package com.example.joel_.week4gamebacklog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Local variables
    private GameAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private List<Game> listGames;
    static AppDatabase db;

    //Constants

    public final static int TASK_GET_ALL_GAMES = 0;
    public final static int TASK_DELETE_GAME = 1;
    public final static int TASK_UPDATE_GAME = 2;
    public final static int TASK_INSERT_GAME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = AppDatabase.getInstance(this);
        new GameAsyncTask(TASK_GET_ALL_GAMES).execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final RecyclerView mRecyclerView = findViewById(R.id.RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GameAdapter(this , listGames);
        mRecyclerView.setAdapter(mAdapter);


        new GameAsyncTask(TASK_INSERT_GAME).execute(new Game("Mario Odyssey", "Nintendo Switch", "Very Cool", "Played"));
        new GameAsyncTask(TASK_INSERT_GAME).execute(new Game("ZELDA Odyssey", "Nintendo SADSAD", "asdasd Cool", "DONE"));

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new GameAdapter(this , listGames);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(listGames);
        }
    }

    public class GameAsyncTask extends AsyncTask<Game, Void, List> {


        private int taskCode;

        public GameAsyncTask(int taskCode) {
            this.taskCode = taskCode;
        }


        @Override
        protected List doInBackground(Game... games) {

            switch (taskCode){

                case TASK_DELETE_GAME:

                    db.GameDao().deleteGames(games[0]);

                    break;

                case TASK_UPDATE_GAME:

                    db.GameDao().updateGames(games[0]);

                    break;

                case TASK_INSERT_GAME:

                    db.GameDao().insertGames(games[0]);

                    break;

            }

            //To return a new list with the updated data, we get all the data from the database again.
            return db.GameDao().getAllGames();

        }
        @Override

        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            onReminderDbUpdated(list);

        }

    }

    public void onReminderDbUpdated(List list) {

        listGames = list;
        updateUI();
    }
}
