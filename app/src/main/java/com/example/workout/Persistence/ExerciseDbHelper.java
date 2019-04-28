package com.example.workout.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.workout.Persistence.ExerciseContract.ExerciseEntry;

public class ExerciseDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME="exercise.db";

    public ExerciseDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_EXERCISES_TABLE ="CREATE TABLE "+ ExerciseEntry.TABLE_NAME + " ("
                +ExerciseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ExerciseEntry.COLUMN_EXERCISE_NAME + " TEXT NOT NULL, "
                +ExerciseEntry.COLUMN_WEIGHT + " TEXT, "
                +ExerciseEntry.COLUMN_S1 + " TEXT, "
                +ExerciseEntry.COLUMN_S2 + " TEXT, "
                +ExerciseEntry.COLUMN_S3 + " TEXT);)";
        db.execSQL(SQL_CREATE_EXERCISES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
