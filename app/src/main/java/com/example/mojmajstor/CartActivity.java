package com.example.mojmajstor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView cartListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> naruceneUsluge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 🔥 PRIMJENA TEME PRIJE prikaza layout-a
        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        Button buttonClearCart = findViewById(R.id.buttonClearCart);

        naruceneUsluge = CartManager.getInstance().getCartItems();

        // Koristimo activated_1 koji poštuje boju teksta iz teme
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, naruceneUsluge);
        cartListView.setAdapter(adapter);

        // 🔴 Dugme za brisanje korpe
        buttonClearCart.setOnClickListener(v -> {
            // Očisti listu u CartManager-u
            CartManager.getInstance().clearCart();

            // Također očisti lokalnu listu za prikaz
            naruceneUsluge.clear();

            // Obavijesti adapter da su podaci promijenjeni
            adapter.notifyDataSetChanged();

            // Prikaz poruke
            Toast.makeText(CartActivity.this, "Korpa je ispražnjena.", Toast.LENGTH_SHORT).show();
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
