package com.example.mojmajstor;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_THEME = "theme";
    private static final String KEY_LANGUAGE = "language";

    public static void saveTheme(Context context, String theme) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_THEME, theme).apply();
    }

    public static String getTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_THEME, "light"); // default light
    }
}
