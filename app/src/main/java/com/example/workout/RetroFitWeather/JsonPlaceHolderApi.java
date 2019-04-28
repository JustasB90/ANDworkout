package com.example.workout.RetroFitWeather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("hourly?city=Horsens&key=f68ec1a91284469c9a85d449862de6df&hours=24")
    Call<WeatherJSON> getWeatherJSON();
}
