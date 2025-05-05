package com.example.mojmajstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    UslugaAdapter adapter;

    String[] uslugeArray = {"Električar", "Vodoinstalater", "Moler", "Stolar", "Keramičar"};
    List<String> uslugeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // PRIMIJENA TEME PRIJE UI
        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MojMajstor");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        toolbar.getOverflowIcon().setTint(getResources().getColor(android.R.color.white, getTheme()));

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uslugeList = new ArrayList<>(Arrays.asList(uslugeArray));
        adapter = new UslugaAdapter(uslugeList, this::onUslugaClicked);
        recyclerView.setAdapter(adapter);

        // Search funkcionalnost
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }

    // Funkcija za klik na uslugu
    private void onUslugaClicked(String usluga) {
        // Pokreni MajstorProfilActivity i pošalji ime majstora
        Intent intent = new Intent(this, MajstorProfilActivity.class);
        intent.putExtra("majstor_ime", usluga);
        startActivity(intent);
    }

    // Meni (Logout, Korpa, Support, Profil, Settings)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;

        } else if (id == R.id.menu_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;

        } else if (id == R.id.menu_support) {
            startActivity(new Intent(this, SupportActivity.class));
            return true;

        } else if (id == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;

        } else if (id == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Promjena teme na osnovu preferencija
    private void applyTheme() {
        String theme = Prefs.getTheme(this);
        if (theme.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
