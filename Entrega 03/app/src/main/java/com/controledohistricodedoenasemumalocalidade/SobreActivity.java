package com.controledohistricodedoenasemumalocalidade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SobreActivity extends AppCompatActivity {

    public static void sobre(AppCompatActivity activity){
        Intent intent = new Intent(activity,
                SobreActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
    }
}