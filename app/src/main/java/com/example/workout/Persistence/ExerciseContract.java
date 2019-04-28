package com.example.workout.Persistence;

import android.provider.BaseColumns;

public class ExerciseContract {

    private ExerciseContract(){}

    public class ExerciseEntry implements BaseColumns{

        public static final String TABLE_NAME="exercises";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EXERCISE_NAME = "Exercise";
        public static final String COLUMN_WEIGHT = "Weight";
        public static final String COLUMN_S1 = "Set1";
        public static final String COLUMN_S2 = "Set2"; //column names cannot contain spaces.
        public static final String COLUMN_S3 = "Set3";
    }
}
