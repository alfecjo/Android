package com.controledohistricodedoenasemumalocalidade.about;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.controledohistricodedoenasemumalocalidade.R;

public class SobreActivity extends AppCompatActivity {

    public static void sobre(AppCompatActivity activity) {
        Intent intent = new Intent(activity,
                SobreActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean myBoolean = lerSharedPreferences();

        if (myBoolean) {
            setTheme(R.style.darkTema);
        } else {
            setTheme(R.style.lightTema);
        }
        setContentView(R.layout.activity_sobre);
    }

    private boolean lerSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        boolean myBoolean = sharedPreferences.getBoolean("myBoolean", false);
        return myBoolean;
    }

    private void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }
}