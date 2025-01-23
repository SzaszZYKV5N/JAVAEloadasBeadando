package com.example.menu;


import jakarta.persistence.*;

@Entity
@Table(name = "szerelo")
public class Szerelo {
    @Id
    @Column(name = "az")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int az;


    @Column(name = "nev")
    public String nev;
    @Column(name = "kezdev")
    public int kezdev;

    public Szerelo() {
    }

    public Szerelo(String nev, int kezdev) {
        this.nev = nev;
        this.kezdev = kezdev;
    }

    public int getAz() {
        return az;
    }

    public void setAz(int az) {
        this.az = az;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getKezdev() {
        return kezdev;
    }

    public void setKezdev(int kezdev) {
        this.kezdev = kezdev;
    }
}
