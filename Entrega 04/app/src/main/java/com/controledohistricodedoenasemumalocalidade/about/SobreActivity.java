package com.controledohistricodedoenasemumalocalidade.about;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

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
        setContentView(R.layout.activity_sobre);
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