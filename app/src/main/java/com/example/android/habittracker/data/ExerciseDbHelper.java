package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker.data.ExerciseContract.ExerciseEntry;

/**
 * Created by Rudster on 11/17/2016.
 */

public class ExerciseDbHelper extends SQLiteOpenHelper {

    //database version
    public static final int DATABASE_VERSION = 1;

    //name of database file
    public static final String DATABASE_NAME =  "workouts.db";


    public ExerciseDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLE workouts
        String SQL_CREATE_TABLE =
                "CREATE TABLE " + ExerciseEntry.TABABLE_NAME + "("
                + ExerciseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ExerciseEntry.COLUMN_EXERCISE_NAME + " TEXT NOT NULL,"
                + ExerciseEntry.COLUMN_EXERCISE_WEIGHT + " INTEGER NOT NULL,"
                + ExerciseEntry.COLUMN_EXERCISE_SETS + " INTEGER NOT NULL,"
                + ExerciseEntry.COLUMN_EXERCISE_REPS + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
