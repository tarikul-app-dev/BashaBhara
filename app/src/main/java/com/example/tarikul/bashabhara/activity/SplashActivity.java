package com.example.tarikul.bashabhara.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.tarikul.bashabhara.utill.SaveValuePreference.getBoleanValueSharedPreferences;

public class SplashActivity extends AppCompatActivity {
    boolean checkFirstTimeUser = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstTimeUser = getBoleanValueSharedPreferences("check_first_time", SplashActivity.this);
        if (checkFirstTimeUser) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            ActivityCompat.finishAffinity(SplashActivity.this);
        } else {

            Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            ActivityCompat.finishAffinity(SplashActivity.this);
        }
    }
}
