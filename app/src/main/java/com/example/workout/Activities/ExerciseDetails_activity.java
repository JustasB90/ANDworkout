package com.example.workout.Activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout.Persistence.ExerciseContract.ExerciseEntry;
import com.example.workout.Persistence.ExerciseDbHelper;
import com.example.workout.R;
import com.example.workout.Utils.ValuesToFireBaseDb;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExerciseDetails_activity extends AppCompatActivity {

    Bundle bundle;
    TextView exerciseNameTV;
    String exerciseName;
    SQLiteDatabase wdb;
    SQLiteDatabase rdb;
    FirebaseDatabase databaseFire;
    private DatabaseReference refDbFire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        exerciseNameTV=  findViewById(R.id.exerciseName);
        exerciseNameTV.setText("waza");

        bundle=this.getIntent().getExtras();
        exerciseName =(String) bundle.get("Extra");
        exerciseNameTV.setText((String)  bundle.get("Extra"));

        ExerciseDbHelper exerciseDbHelper = new ExerciseDbHelper(this);
        wdb = exerciseDbHelper.getWritableDatabase();
        rdb = exerciseDbHelper.getReadableDatabase();

        ////////////FIREBASE/////////////////
         databaseFire = FirebaseDatabase.getInstance();
         refDbFire = databaseFire.getReference().child("Workouts");
        //////////////////////////////////////

        Button doneButton = (Button) findViewById(R.id.doneButtonID);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
                Toast.makeText(ExerciseDetails_activity.this,"Results saved!",Toast.LENGTH_LONG).show();
            }
        });


        readInit();
        }

    public void insert(){

        EditText currWeightET = (EditText) findViewById(R.id.currentWeightID);
        EditText currS1 = (EditText) findViewById(R.id.currentSet1ID);
        EditText currS2 = (EditText) findViewById(R.id.currentSet2ID);
        EditText currS3 = (EditText) findViewById(R.id.currentSet3ID);
        TextView nameTV = (TextView) findViewById(R.id.exerciseName);

        ContentValues values = new ContentValues();
        values.put(ExerciseEntry.COLUMN_EXERCISE_NAME,nameTV.getText().toString());
        values.put(ExerciseEntry.COLUMN_WEIGHT,currWeightET.getText().toString());
        values.put(ExerciseEntry.COLUMN_S1,currS1.getText().toString());
        values.put(ExerciseEntry.COLUMN_S2,currS2.getText().toString());
        values.put(ExerciseEntry.COLUMN_S3,currS3.getText().toString());

        wdb.insert(ExerciseEntry.TABLE_NAME,null, values);
        ValuesToFireBaseDb sameValues = new ValuesToFireBaseDb(nameTV.getText().toString(),currWeightET.getText().toString(),
                currS1.getText().toString(),currS2.getText().toString(),currS3.getText().toString());

        refDbFire.push().setValue(sameValues);
    }


    public void readInit(){

        ExerciseDbHelper exerciseDbHelper= new ExerciseDbHelper(this);
        SQLiteDatabase db = exerciseDbHelper.getReadableDatabase();

        String[] projection = {
            ExerciseEntry._ID,
            ExerciseEntry.COLUMN_EXERCISE_NAME,
            ExerciseEntry.COLUMN_WEIGHT,
            ExerciseEntry.COLUMN_S1,
            ExerciseEntry.COLUMN_S2,
            ExerciseEntry.COLUMN_S3
        };
        String whereClause = "Exercise=?"; // "Exercise" is ExerciseEntry.COLUMN_EXERCISE_NAME
        String[] selectionArgs = {exerciseName};
        Cursor cursor = db.query(ExerciseEntry.TABLE_NAME,projection,whereClause,selectionArgs,null,null,null);

        TextView weightTV = (TextView) findViewById(R.id.previousWeightID);
        TextView s1TV = (TextView) findViewById(R.id.previousSet1ID);
        TextView s2TV = (TextView) findViewById(R.id.previousSet2ID);
        TextView s3TV = (TextView) findViewById(R.id.previousSet3ID);

        /*try{
            while(cursor.moveToNext()){ //move to last would be better

                String weight = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_WEIGHT));
                String s1 = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_S1));
                String s2 = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_S2));
                String s3 = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_S3));

                weightTV.setText(weight);
                s1TV.setText(s1);
                s2TV.setText(s2);
                s3TV.setText(s3);
            }*/

        try{
            cursor.moveToLast();

            String weight = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_WEIGHT));
            String s1 = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_S1));
            String s2 = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_S2));
            String s3 = cursor.getString(cursor.getColumnIndex(ExerciseEntry.COLUMN_S3));

            weightTV.setText(weight);
            s1TV.setText(s1);
            s2TV.setText(s2);
            s3TV.setText(s3);
        }
        catch (CursorIndexOutOfBoundsException e){
            weightTV.setText("default");
            s1TV.setText("default");
            s2TV.setText("default");
            s3TV.setText("default");
        }
        finally {
            cursor.close();
        }
    }

}
