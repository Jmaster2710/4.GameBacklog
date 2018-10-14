package com.example.joel_.week4gamebacklog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddingGameActivity extends AppCompatActivity {

    Integer mNewItem = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_game);
        mNewItem = -1;
        //get the spinner from the xml.
        final Spinner dropdown = findViewById(R.id.editStatus);
        //create a list of items for the spinner.
        String[] status = new String[]{"Want to play", "Playing", "Stalled", "Dropped"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, status);
        dropdown.setAdapter(adapter);

        final EditText mNotesButton = findViewById((R.id.editNotes));
        final EditText mPlatformButton = findViewById((R.id.editPlatform));
        final EditText mTitleButton = findViewById((R.id.editTitle));

        //Check
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            mNewItem = extras.getInt("GameBacklogNew");
            mNotesButton.setText(extras.getString("GameBacklogNotes"));
            mPlatformButton.setText(extras.getString("GameBacklogPlatform"));
            mTitleButton.setText(extras.getString("GameBacklogTitle"));
           // dropdown.setSelection(extras.getInt("GameBacklogNew"));
        }


        //Save settings
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddingGameActivity.this, MainActivity.class);
                intent.putExtra("GameBacklogStatus", dropdown.getSelectedItem().toString());
                intent.putExtra("GameBacklogTitle", mTitleButton.getText().toString());
                intent.putExtra("GameBacklogNotes", mNotesButton.getText().toString());
                intent.putExtra("GameBacklogPlatform", mPlatformButton.getText().toString());
                intent.putExtra("GameBacklogNew", mNewItem);
                startActivity(intent);
            }
        });
    }

    //When leaving:
    //dropdown.getSelectedItem().toString();


}
