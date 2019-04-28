package com.example.workout.Recycler;

public class ExerciseObject {

    private int mImageResource;
    private String mText;

    public ExerciseObject(int imgResource, String text){
        this.mImageResource=imgResource;
        this.mText=text;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
