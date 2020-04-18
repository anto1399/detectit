package org.tensorflow.lite.examples.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.tensorflow.lite.examples.detection.R;


public class AccountActivity extends AppCompatActivity {

    private TextView back;
    private TextInputLayout name;
    private TextInputLayout contact;
    private TextInputLayout password;
    private TextInputLayout confirm_password;
    private Button create_account;
    private TextInputEditText name_text;
    private TextInputEditText contact_text;
    private TextInputEditText password_text;
    private TextInputEditText password_con_text;

    private void init(){
        back = findViewById(R.id.txt_back);
        name = findViewById(R.id.username);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.password_confirm);
        create_account = findViewById(R.id.btn_create_account);
        name_text = findViewById(R.id.name_text);
        contact_text = findViewById(R.id.contact_text);
        password_text = findViewById(R.id.password_text);
        password_con_text = findViewById(R.id.password_con_text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //fields initialization
        init();
        back();
        onFocusGain();
        createAccount();
    }

    //going back
    private void back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    //create a new account
    private void createAccount(){
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateName(name_text) || !validateContact(contact_text) || !validatePassword(password_text, password_con_text)){

                } else {
                    Toast.makeText(v.getContext(), "Validation pass", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean validateName(TextInputEditText name_text){
        if(!validate(name_text)){
            name.setError("Name field is required");
            return false;
        } else {
            name.setErrorEnabled(false);
            return true;
        }
    }

    //contact validation
    private boolean validateContact(TextInputEditText editText){
        if(!validate(editText)){
            contact.setError("Contact field is required");
            return false;
        } else if(editText.getText().length() < 10){
            contact.setError("Contact can't be less than ten");
            return false;
        } else {
            contact.setErrorEnabled(false);
            return true;
        }
    }


    //password validation
    private boolean validatePassword(TextInputEditText pass, TextInputEditText con_pass){
        if(!validate(pass)) {
            password.setError("Password field is required");
            return false;
        }

        if(!validate(con_pass)){
            confirm_password.setError("Confirm password is required");
            return false;
        }

        if (!pass.getText().toString().trim().equals(con_pass.getText().toString().trim())){
            password.setError("Password confirmation does not match");
            confirm_password.setError("Password confirmation does not match");
            return false;
        }

        return  true;
    }

    //on focus gain
    @SuppressLint("ClickableViewAccessibility")
    private void onFocusGain(){

        name_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                name.setErrorEnabled(false);
                return false;
            }
        });

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

        password_con_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                confirm_password.setErrorEnabled(false);
                return false;
            }
        });
    }





    //validation check
    private boolean validate(TextInputEditText editText){
         if(editText.getText().toString().trim().isEmpty() || editText.getText().equals("")) {
            return false;
         } else {
             return true;
         }
    }
}
