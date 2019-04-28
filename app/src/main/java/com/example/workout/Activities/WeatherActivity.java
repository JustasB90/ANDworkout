package com.example.workout.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.workout.RetroFitWeather.*;

import com.example.workout.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        textViewResult= findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherbit.io/v2.0/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<WeatherJSON> call = jsonPlaceHolderApi.getWeatherJSON();

        call.enqueue(new Callback<WeatherJSON>() {
            @Override
            public void onResponse(Call<WeatherJSON> call, Response<WeatherJSON> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                WeatherJSON weatherJSON = response.body();
                List<Datum> datumList = weatherJSON.getData();

                for(Datum data:datumList){
                    String content="";
                    content+="Local time "+ data.getTimestampLocal() + "\n";
                    content+="Temperature "+ data.getTemp().toString() + "\n";
                    content+="Clouds "+ data.getCloudsMid() + "\n";
                    content+="Precipitation "+data.getPrecip() + "\n\n\n";


                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<WeatherJSON> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    }

