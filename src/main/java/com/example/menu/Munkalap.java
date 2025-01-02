package com.example.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "munkalap")
public class Munkalap {
    @Column(name = "bedatum")
    public String bedatum;

    @Column(name = "javdatum")
    public String javdatum;

    @Column(name = "helyaz")
    public int helyaz;
    @Column(name = "szeraz")
    public int szeraz;
    @Column(name = "munkaora")
    public int munkaora;
    @Column(name = "anyagar")
    public int anyagar;

}
