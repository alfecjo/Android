package com.controledohistricodedoenasemumalocalidade.apoioSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class ApoioSharedPreferences {

    public boolean lerSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        boolean myBoolean = sharedPreferences.getBoolean("myBoolean", false);
        return myBoolean;
    }

    public void escreveSharedPreferences(Context context, boolean isChecked) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("myBoolean", isChecked);
        editor.commit();
    }
}
