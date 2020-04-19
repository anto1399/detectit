package com.trinity.detectit.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


public class Preference {

    //shared prefrences
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static SharedPreferences.Editor editor1;
    private static String APPLICATION_NAME = "com.trinity.detectit.model";


    @SuppressLint("CommitPrefEdits")
    public Preference(Activity activity) {
        preferences = activity.getSharedPreferences(APPLICATION_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor1 = preferences.edit();
    }

    public void savePreferences(User user, Context context) {
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("contact", user.getContact());
        editor.putString("password", user.getPassword());
        editor.putBoolean("status", user.getStatus());
        editor.apply();
        Toast.makeText(context, "Record Saved Successfully", Toast.LENGTH_LONG).show();
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }

    public int getId(){
        return preferences.getInt("id", 0);
    }

    public String getName() {
        return preferences.getString("name","Isaac");
    }

    public String getContact(){
        return preferences.getString("contact", "");
    }

    public String getPassword(){
        return preferences.getString("password", "");
    }

    public boolean getStatus(){
        return preferences.getBoolean("status", false);
    }

    public void clearStatus(){
        editor1.clear();
        editor1.apply();
    }

    public void refreshStatus(User user, Context context) {
        editor1.putBoolean("status", user.getStatus());
        editor1.apply();
    }

}
