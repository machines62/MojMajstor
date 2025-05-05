package com.example.mojmajstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SupportActivity extends AppCompatActivity {

    String[] pitanja = {
            "Kako ti mogu pomoći?",
            "Da li si zadovoljan sa uslugama?",
            "Kako naručiti uslugu?",
            "Kako kontaktirati majstora?",
            "Kako otkazati narudžbu?"
    };

    String[] odgovori = {
            "Možete pregledati usluge i dodati ih u korpu za narudžbu.",
            "Nadamo se da jeste! Ako imate primjedbe, kontaktirajte nas.",
            "Dodajte uslugu u korpu i potvrdite narudžbu.",
            "Kontakt podaci majstora se šalju nakon narudžbe.",
            "Možete otkazati prije potvrde narudžbe kontaktiranjem nas."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // PROMJENA TEME PRIJE prikaza layouta
        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        // Prikaz pitanja
        ListView questionsListView = findViewById(R.id.questionsListView);
        TextView answerTextView = findViewById(R.id.answerTextView);

        // Koristimo activated_1 koji poštuje boju teksta iz teme
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, pitanja);
        questionsListView.setAdapter(adapter);

        questionsListView.setOnItemClickListener((parent, view, position, id) -> {
            answerTextView.setText(odgovori[position]);
            answerTextView.setVisibility(TextView.VISIBLE);
        });

        // Postavi novo pitanje (email dugme)
        Button buttonAskQuestion = findViewById(R.id.buttonAskQuestion);
        buttonAskQuestion.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"tvojemail@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Pitanje za MojMajstor aplikaciju");
            email.putExtra(Intent.EXTRA_TEXT, "Ovdje napiši svoje pitanje...");

            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Odaberi email klijent:"));
        });

        // Rating zvjezdice
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            Toast.makeText(SupportActivity.this, "Hvala na ocjeni: " + (int) rating + " zvjezdica", Toast.LENGTH_SHORT).show();
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
