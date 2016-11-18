package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.data.ExerciseContract.ExerciseEntry;
import com.example.android.habittracker.data.ExerciseDbHelper;

/**
 * Created by Rudster on 11/17/2016.
 */

public class EditorActivity extends AppCompatActivity {

    //EditText field to enter name of exercise
    private EditText mExerciseNameEditText;

    //EditText field to enter weight of dumbbells/barbells etc
    private EditText mWeightEditText;

    //EditText field to enter number of sets done of the exercise
    private EditText mSetEditText;

    //EditText field to enter number of reps done for that set
    private EditText mRepsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mExerciseNameEditText = (EditText) findViewById(R.id.exercise_name);
        mWeightEditText = (EditText) findViewById(R.id.weight);
        mSetEditText = (EditText) findViewById(R.id.sets);
        mRepsEditText = (EditText) findViewById(R.id.reps);

    }

    private void insertExcercise() {

        String exerciseNameString = mExerciseNameEditText.getText().toString().trim();
        String weightString = mWeightEditText.getText().toString().trim();
        int weight = Integer.parseInt(weightString);
        String setString = mSetEditText.getText().toString().trim();
        int set = Integer.parseInt(setString);
        String repString = mRepsEditText.getText().toString().trim();
        int rep = Integer.parseInt(repString);

        ExerciseDbHelper mDbHelper = new ExerciseDbHelper(this);
        //Get the data repository in the write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ExerciseEntry.COLUMN_EXERCISE_NAME, exerciseNameString);
        values.put(ExerciseEntry.COLUMN_EXERCISE_WEIGHT, weight);
        values.put(ExerciseEntry.COLUMN_EXERCISE_SETS, set);
        values.put(ExerciseEntry.COLUMN_EXERCISE_REPS, rep);

        long newRowId = db.insert(ExerciseEntry.TABABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving workout", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Workout saved with id:" + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //user clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            //Respond to cllick on "save" menu option
            case R.id.action_save:
                //Save workout to database
                insertExcercise();
                //exit activity
                finish();
                return true;
            //Respond to click on "up" arrow button in app bar
            case R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
