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

public class PullDayActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExerciseObject> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_day);

    createExampleList();
    buildRecyclerView();
    }

    public void changeItem(int position, String text){
        mList.get(position).setmText(text);
        mAdapter.notifyItemChanged(position);
    }

    public void createExampleList(){
       mList = new ArrayList<>();
       mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Deadlift"));
       mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Pull-ups"));
       mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Hi-Rows"));
       mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Lo-Rows"));
       mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Face pull"));
       mList.add(new ExerciseObject(R.drawable.ic_accessibility_black_24dp, "Z-Bar"));
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

                Intent intent = new Intent(PullDayActivity.this, ExerciseDetails_activity.class);
                intent.putExtra("Extra", mList.get(position).getmText());
                startActivity(intent);
            }
        });

    }
}
