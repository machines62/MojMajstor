package com.example.mojmajstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Button buttonLight, buttonDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // PROMJENI TEMU PRIJE UI
        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicijalizacija buttona
        buttonLight = findViewById(R.id.buttonLight);
        buttonDark = findViewById(R.id.buttonDark);

        // Light tema
        buttonLight.setOnClickListener(v -> {
            Prefs.saveTheme(this, "light");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(this, "Light tema aktivirana", Toast.LENGTH_SHORT).show();
            recreateAll();
        });

        // Dark tema
        buttonDark.setOnClickListener(v -> {
            Prefs.saveTheme(this, "dark");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Toast.makeText(this, "Dark tema aktivirana", Toast.LENGTH_SHORT).show();
            recreateAll();
        });
    }

    // PROMIJENI TEMU PRIJE UI
    private void applyTheme() {
        String theme = Prefs.getTheme(this);
        if ("dark".equals(theme)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Kada se tema promijeni → restartuj sve aktivnosti
    private void recreateAll() {
        recreate(); // samo SettingsActivity se restartuje
    }
}
