package com.example.putworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
                if(isFirstRun){
                    // Если это первый запуск, отображаем активность приветствия
                    startActivity(new Intent(SplashScreen.this, GreetingsActivity.class));
                } else {
                    // Если приложение запускается не впервые, переходим на главный экран
                    startActivity(new Intent(SplashScreen.this,LogInActivity.class));
                }
                finish(); // Закрываем текущую активность
            }
        },3*1000);
    }
}