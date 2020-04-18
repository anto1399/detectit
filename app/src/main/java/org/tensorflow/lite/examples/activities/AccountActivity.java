package org.tensorflow.lite.examples.activities;

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

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.model.Preference;
import org.tensorflow.lite.examples.model.User;

import java.util.Objects;


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
    private Preference preference;

    private void init(){
        back = findViewById(R.id.txt_back);
        name = findViewById(R.id.username);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.password_confirm);
        create_account = findViewById(R.id.btn_option_one);
        name_text = findViewById(R.id.name_text);
        contact_text = findViewById(R.id.contact_text);
        password_text = findViewById(R.id.password_text);
        password_con_text = findViewById(R.id.password_con_text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //preference
        preference = new Preference(this);

        //fields initialization
        init();
        back();
        onFocusGain();
        createAccount(this);
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
    private void createAccount(Context context){
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName(name_text) || !validateContact(contact_text) || !validatePassword(password_text, password_con_text)){

                } else {
                    saveToPreference(context);
                    launchHome();
                }
            }
        });
    }

    //lunch home page
    private void launchHome(){
        Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    //saving data to preferences
    private void saveToPreference(Context context){
        try {
            int id = preference.getId();
            User user = new User();
            user.setId(id++);
            user.setName(Objects.requireNonNull(name_text.getText()).toString().trim());
            user.setContact(Objects.requireNonNull(contact_text.getText()).toString().trim());
            user.setPassword(Objects.requireNonNull(password_text.getText()).toString().trim());
            preference.savePreferences(user, context);
        } catch (Exception e){
            Toast.makeText(AccountActivity.this, "Saving Error " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    //validate name
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
