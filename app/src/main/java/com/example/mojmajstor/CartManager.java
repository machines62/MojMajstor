package com.example.mojmajstor;

import java.util.ArrayList;

public class CartManager {

    // Singleton instanca
    private static CartManager instance;

    // Lista stavki u korpi
    private ArrayList<String> cartItems;

    // Privatni konstruktor (samo unutar klase)
    private CartManager() {
        cartItems = new ArrayList<>();
    }

    // Dobavljanje instance (Singleton pattern)
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Dodavanje stavke u korpu
    public void addItem(String item) {
        cartItems.add(item);
    }

    // Dohvatanje svih stavki iz korpe
    public ArrayList<String> getCartItems() {
        return cartItems;
    }

    // Brisanje svih stavki iz korpe
    public void clearCart() {
        cartItems.clear();
    }
}
