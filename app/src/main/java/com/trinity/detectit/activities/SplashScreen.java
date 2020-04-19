package com.trinity.detectit.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trinity.detectit.model.Preference;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Preference preference = new Preference(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if(preference.getContact().isEmpty() || preference.getContact().equals("") && preference.getPassword().isEmpty() || preference.getPassword().equals("")){

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else if(!preference.getStatus()){

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        }, 2000);

    }
}
