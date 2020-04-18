package org.tensorflow.lite.examples.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.examples.detection.DetectorActivity;
import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.model.Preference;
import org.tensorflow.lite.examples.model.User;


import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HomeActivity extends AppCompatActivity {

    private Button opt1, opt2, opt3;
    private int selected = 1;
    private TextView logout;
    private Preference preference;

    private void init(){
        opt1 = findViewById(R.id.btn_option_one);
        opt2 = findViewById(R.id.btn_option_two);
        opt3 = findViewById(R.id.btn_option_three);
        logout = findViewById(R.id.logout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //shared preferences
        preference = new Preference(this);


        //screen shot permission
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //init
        init();
        selectedOption();
        optionOneClick();
        optionTwoClick();
        optionThreeClick();
        logout(this);
    }


    //option one selected
    private void optionOneClick(){
        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selected = 1;
               selectedOption();
               launchCamera();
            }
        });
    }

    //option two selected
    private void optionTwoClick(){
        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = 2;
                selectedOption();
                Toast.makeText(HomeActivity.this, "Implementation not available", Toast.LENGTH_LONG).show();
            }
        });
    }

    //option three selected
    private void optionThreeClick(){
        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = 3;
                selectedOption();
                Toast.makeText(HomeActivity.this, "Implementation not available", Toast.LENGTH_LONG).show();
            }
        });
    }

    //setting color for selected option
    private void selectedOption(){
        if(selected == 1){
            opt1.setBackgroundResource(R.drawable.selected_button_shape);
        } else {
            opt1.setBackgroundResource(R.drawable.button_shape);
        }

        if(selected == 2){
            opt2.setBackgroundResource(R.drawable.selected_button_shape);
        } else {
            opt2.setBackgroundResource(R.drawable.button_shape);
        }

        if(selected == 3){
            opt3.setBackgroundResource(R.drawable.selected_button_shape);
        } else {
            opt3.setBackgroundResource(R.drawable.button_shape);
        }
    }

    //launching camera
    private void launchCamera(){
        try{

            Intent intent = new Intent(HomeActivity.this, DetectorActivity.class);
            startActivity(intent);

        } catch (Exception e){
            Log.e("Error Launching", e.getMessage());
        }
    }

    //logging out
    private void logout(Context context){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //preference.clearStatus();

                User user = new User();
                user.setStatus(false);
                preference.refreshStatus(user, context);

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
