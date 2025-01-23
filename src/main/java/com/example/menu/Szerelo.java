package com.example.menu;

public class Szerelo {
    private int id;
    private String nev;

    public Szerelo(int id, String nev) {
        this.id = id;
        this.nev = nev;
    }

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    @Override
    public String toString() {
        return nev; // Ezzel jeleníti meg a nevet a ComboBox-ban
    }
}

