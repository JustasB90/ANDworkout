package com.example.workout.Recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workout.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private ArrayList<ExerciseObject> mExerciseList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mText;
        public ExerciseViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageID);
            mText=itemView.findViewById(R.id.textID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExerciseAdapter(ArrayList<ExerciseObject> exerciseList){
        this.mExerciseList= exerciseList;
    }


    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_layout, parent, false);
        ExerciseViewHolder evh = new ExerciseViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder exerciseViewHolder, int position) {
        ExerciseObject currentItem = mExerciseList.get(position);
        exerciseViewHolder.mImageView.setImageResource(currentItem.getmImageResource());
        exerciseViewHolder.mText.setText(currentItem.getmText());
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }


}
