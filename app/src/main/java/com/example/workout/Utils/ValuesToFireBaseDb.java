package com.example.workout.Utils;
//Made this class here because making inner class gives errors(fire db tried to turn activity into json.
public class ValuesToFireBaseDb {
    public String exercName;
    public String weight;
    public String s1;
    public String s2;
    public String s3;

    public ValuesToFireBaseDb(){
        //Docs said its needed...
    }
    public ValuesToFireBaseDb(String exercName,String weight, String s1, String s2, String s3){
        this.exercName=exercName;
        this.weight=weight;
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
    }



}