package com.trinity.detectit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.trinity.detectit.detection.R;
import com.trinity.detectit.model.Preference;
import com.trinity.detectit.model.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout contact;
    private TextInputLayout password;
    private TextInputEditText contact_text, password_text;
    private Button login;
    private TextView account;
    private Preference preference;

    private void init(){
        contact = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_option_one);
        account = findViewById(R.id.txt_create_account);
        contact_text = findViewById(R.id.contact_text);
        password_text = findViewById(R.id.password_text);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        preference = new Preference(this);

        //initialising fields
        init();
        createAccount();
        login(this);
        onFocusGain();
    }


    private void login(Context context){
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(!validate(contact_text) || !validate(password_text)){
                validateError();
              } else {
                  checkLogin(context);
              }
          }
      });
    }


    //checking
    private void checkLogin(Context context){
        if(preference.getContact().isEmpty() || preference.getContact().equals("") && preference.getPassword().isEmpty() || preference.getPassword().equals("")){
            Toast.makeText(LoginActivity.this, "Please create a new account", Toast.LENGTH_LONG).show();
        } else {
            launchHome(context);
        }
    }


    //validating input
    private void validateError(){
        if(contact_text.getText().toString().trim().isEmpty() || contact_text.getText().equals("")) {
           contact.setError("Contact field is required");
        }
        if(password_text.getText().toString().trim().isEmpty() || password_text.getText().equals(" ")){
            password.setError("Password field is required");
        }
    }


    //on focus
    private boolean validate(TextInputEditText editText){
        if(editText.getText().toString().trim().isEmpty() || editText.getText().equals(" ")){
           return false;
        } else {
            return true;
        }
    }

    //on focus gain
    @SuppressLint("ClickableViewAccessibility")
    private void onFocusGain(){
        contact_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                contact.setErrorEnabled(false);
                return false;
            }
        });

        password_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                password.setErrorEnabled(false);
                return false;
            }
        });
    }


    //launching account form
    private void createAccount(){
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //lunch home page
    private void launchHome(Context context){
        String con = Objects.requireNonNull(contact_text.getText()).toString().trim();
        String pass = Objects.requireNonNull(password_text.getText()).toString().trim();

        User user = new User();
        user.setStatus(true);
        preference.refreshStatus(user, context);

        if(con.equals(preference.getContact()) && pass.equals(preference.getPassword())){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Account does not exist", Toast.LENGTH_LONG).show();
        }
    }
}
