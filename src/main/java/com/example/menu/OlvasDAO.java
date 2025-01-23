package com.example.menu;


public class OlvasDAO {

    private int helyaz;
    private String telepules;
    private String utca;
    private String nev;
    private int munkaora;
    private int anyagar;


    // Alapértelmezett konstruktor
    public OlvasDAO() {
    }

    // Konstruktor (id nélkül, mert az automatikusan generálódik)

    public OlvasDAO(int helyaz, String telepules, String utca, String nev, int munkaora, int anyagar) {
        this.helyaz = helyaz;
        this.telepules = telepules;
        this.utca = utca;
        this.nev = nev;
        this.munkaora = munkaora;
        this.anyagar = anyagar;
    }


    // Getterek és setterek

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getMunkaora() {
        return munkaora;
    }

    public void setMunkaora(int munkaora) {
        this.munkaora = munkaora;
    }

    public int getAnyagar() {
        return anyagar;
    }

    public void setAnyagar(int anyagar) {
        this.anyagar = anyagar;
    }

    public int getHelyaz() {
        return helyaz;
    }

    public void setId(int helyaz) {
        this.helyaz = this.helyaz;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }
}
