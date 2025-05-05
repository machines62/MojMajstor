package com.example.mojmajstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MajstorProfilActivity extends AppCompatActivity {

    TextView textViewIme, textViewOpis, textViewCijena, textViewEmail, textViewTelefon;
    Button buttonDodajUKorpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 🔥 PRIMJENA TEME PRIJE prikaza
        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majstor_profil);

        // Inicijalizacija
        textViewIme = findViewById(R.id.textViewIme);
        textViewOpis = findViewById(R.id.textViewOpis);
        textViewCijena = findViewById(R.id.textViewCijena);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewTelefon = findViewById(R.id.textViewTelefon);
        buttonDodajUKorpu = findViewById(R.id.buttonDodajUKorpu);

        // Dohvati ime majstora
        String majstorIme = getIntent().getStringExtra("majstor_ime");
        textViewIme.setText("Majstor: " + majstorIme);

        // Opis (možeš kasnije dodati svoj opis po želji)
        textViewOpis.setText("Iskusni " + majstorIme.toLowerCase() + " sa višegodišnjim iskustvom. Pouzdano i kvalitetno.");

        // Random cijena
        Random random = new Random();
        int cijena = 10 + random.nextInt(41); // 10 - 50 KM
        textViewCijena.setText("Cijena: " + cijena + " KM");

        // Random email
        String email = majstorIme.toLowerCase().replace("č", "c").replace("ć", "c").replace("š", "s").replace("đ", "dj").replace("ž", "z") + "@majstor.ba";
        textViewEmail.setText("Email: " + email);

        // Random broj telefona
        int broj = 100000 + random.nextInt(900000);
        textViewTelefon.setText("Telefon: 062/" + broj);

        // ➡️ Dodavanje u korpu
        buttonDodajUKorpu.setOnClickListener(v -> {
            CartManager.getInstance().addItem(majstorIme);
            Toast.makeText(MajstorProfilActivity.this, majstorIme + " dodano u korpu!", Toast.LENGTH_SHORT).show();
        });
    }

    // 🌙 Tema (Dark / Light) primjena
    private void applyTheme() {
        String theme = Prefs.getTheme(this);
        if ("dark".equals(theme)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
