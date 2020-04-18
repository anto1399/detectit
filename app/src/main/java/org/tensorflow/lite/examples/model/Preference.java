package org.tensorflow.lite.examples.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.tensorflow.lite.examples.activities.HomeActivity;


public class Preference {

    //shared prefrences
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static String APPLICATION_NAME = "org.tensorflow.lite.examples.detection";


    @SuppressLint("CommitPrefEdits")
    public Preference(Activity activity) {
        preferences = activity.getSharedPreferences(APPLICATION_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void savePreferences(User user, Context context) {
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("contact", user.getContact());
        editor.putString("password", user.getPassword());
        editor.apply();
        Toast.makeText(context, "Record Saved Succefully", Toast.LENGTH_LONG).show();
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

}
