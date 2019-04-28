package com.example.workout.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workout.R;

public class MainActivity extends AppCompatActivity {

    Button pullButton;
    Button pushButton;
    Button legsButton;
    Button weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullButton = (Button) findViewById(R.id.pullID);
        pushButton = (Button) findViewById(R.id.pushID);
        legsButton = (Button) findViewById(R.id.legsID);
        weatherButton = (Button) findViewById(R.id.weatherButton);

        pullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PullDayActivity.class);
                startActivity(intent);
            }
        });
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PushDayActivity.class);
                startActivity(intent);
            }
        });
        legsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LegAndMiscDayActivity.class);
                startActivity(intent);
            }
        });

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

    }
}
