package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Rudster on 11/17/2016.
 */

public final class ExerciseContract {

    public static abstract class ExerciseEntry implements BaseColumns {
        public static final String TABABLE_NAME = "workouts";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EXERCISE_NAME = "exercise";
        public static final String COLUMN_EXERCISE_WEIGHT = "weight";
        public static final String COLUMN_EXERCISE_SETS = "sets";
        public static final String COLUMN_EXERCISE_REPS = "reps";
    }
}
