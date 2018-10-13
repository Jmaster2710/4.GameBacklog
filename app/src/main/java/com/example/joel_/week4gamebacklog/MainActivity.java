package com.example.joel_.week4gamebacklog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

    public final static int TASK_GET_ALL_REMINDERS = 0;
    public final static int TASK_DELETE_REMINDER = 1;
    public final static int TASK_UPDATE_REMINDER = 2;
    public final static int TASK_INSERT_REMINDER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = AppDatabase.getInstance(this);
        new ReminderAsyncTask(TASK_GET_ALL_REMINDERS).execute();

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

    public class ReminderAsyncTask extends AsyncTask<Game, Void, List> {


        private int taskCode;

        public ReminderAsyncTask(int taskCode) {
            this.taskCode = taskCode;
        }


        @Override
        protected List doInBackground(Game... games) {

            switch (taskCode){

                case TASK_DELETE_REMINDER:

                    db.GameDao().deleteReminders(games[0]);

                    break;

                case TASK_UPDATE_REMINDER:

                    db.GameDao().updateReminders(games[0]);

                    break;

                case TASK_INSERT_REMINDER:

                    db.GameDao().insertReminders(games[0]);

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
