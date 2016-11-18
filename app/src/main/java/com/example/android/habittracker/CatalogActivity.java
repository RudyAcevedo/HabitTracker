package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.data.ExerciseContract.ExerciseEntry;
import com.example.android.habittracker.data.ExerciseDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private ExerciseDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up FAB to open Editor Activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new ExerciseDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    //Temp helper method to display info
    private void displayDatabaseInfo() {
        ExerciseDbHelper mDbHelper = new ExerciseDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ExerciseEntry._ID,
                ExerciseEntry.COLUMN_EXERCISE_NAME,
                ExerciseEntry.COLUMN_EXERCISE_WEIGHT,
                ExerciseEntry.COLUMN_EXERCISE_SETS,
                ExerciseEntry.COLUMN_EXERCISE_REPS
        };

        Cursor cursor = db.query(
                ExerciseEntry.TABABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_workouts);

        try {
            displayView.setText("The workouts table contains " + cursor.getCount()
                    + " workouts.\n\n");
            displayView.append(ExerciseEntry._ID + " - " +
                    ExerciseEntry.COLUMN_EXERCISE_NAME + " - " +
                    ExerciseEntry.COLUMN_EXERCISE_WEIGHT + " - " +
                    ExerciseEntry.COLUMN_EXERCISE_SETS + " - " +
                    ExerciseEntry.COLUMN_EXERCISE_REPS + "\n");

            int idColumnIndex = cursor.getColumnIndex(ExerciseEntry._ID);
            int exerciseNameColumnIndex = cursor.getColumnIndex(ExerciseEntry.COLUMN_EXERCISE_NAME);
            int weightColumnIndex = cursor.getColumnIndex(ExerciseEntry.COLUMN_EXERCISE_WEIGHT);
            int setColumnIndex = cursor.getColumnIndex(ExerciseEntry.COLUMN_EXERCISE_SETS);
            int repColumnIndex = cursor.getColumnIndex(ExerciseEntry.COLUMN_EXERCISE_REPS);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(exerciseNameColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);
                int currentSet = cursor.getInt(setColumnIndex);
                int currentRep = cursor.getInt(repColumnIndex);

                displayView.append("\n" + currentID + " - " +
                        currentName + " - " +
                        currentWeight + " - " +
                        currentSet + " - " +
                        currentRep);
            }

        } finally {
            cursor.close();
        }
    }

}
