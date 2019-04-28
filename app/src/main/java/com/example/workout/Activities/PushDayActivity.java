package com.example.workout.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.workout.Recycler.ExerciseAdapter;
import com.example.workout.Recycler.ExerciseObject;
import com.example.workout.R;

import java.util.ArrayList;

public class PushDayActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExerciseObject> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_day);

        createExampleList();
        buildRecyclerView();



    }

    public void changeItem(int position, String text){
        mList.get(position).setmText(text);
        mAdapter.notifyItemChanged(position);
    }

    public void createExampleList(){
        mList = new ArrayList<>();
        mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Bench press"));
        mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Incline"));
        mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Dips"));
        mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "OHP"));
        mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Cable"));
        mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Z-bar tri"));
    }


    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerViewID);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter=new ExerciseAdapter(mList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(PushDayActivity.this, ExerciseDetails_activity.class);
                intent.putExtra("Extra", mList.get(position).getmText());
                startActivity(intent);
            }
        });

    }
}
