package com.example.mojmajstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView textViewName, textViewEmail;
    Button buttonLogout, buttonResetPassword, buttonNarudzbe, buttonDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // PROMJENA TEME PRIJE prikaza layout-a
        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        buttonNarudzbe = findViewById(R.id.buttonNarudzbe);
        buttonDeleteAccount = findViewById(R.id.buttonDeleteAccount);

        // Prikaz korisničkih podataka
        if (user != null) {
            String displayName = user.getDisplayName();
            String email = user.getEmail();

            if (displayName != null && !displayName.isEmpty()) {
                textViewName.setText("Korisnik: " + displayName);
            } else {
                textViewName.setText("Korisnik: (nije postavljeno)");
            }

            textViewEmail.setText("Email: " + email);
        }

        // Odjava korisnika
        buttonLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        });

        // Reset lozinke
        buttonResetPassword.setOnClickListener(v -> {
            if (user != null && user.getEmail() != null) {
                auth.sendPasswordResetEmail(user.getEmail())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Email za reset lozinke je poslan.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProfileActivity.this, "Greška pri slanju emaila.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Prikaz narudžbi
        buttonNarudzbe.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, CartActivity.class));
        });

        // Brisanje računa
        buttonDeleteAccount.setOnClickListener(v -> {
            if (user != null) {
                user.delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Račun je obrisan.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ProfileActivity.this, "Greška pri brisanju računa.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    // Tema (Dark / Light) primjena
    private void applyTheme() {
        String theme = Prefs.getTheme(this);
        if ("dark".equals(theme)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
