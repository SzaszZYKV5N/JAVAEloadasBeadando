package com.example.menu;

import javax.persistence.*;

@Entity
@Table(name = "hely")
public class Hely {
    @Id
    @GeneratedValue
    @Column(name = "az")
    public int az;

    @Column(name = "telepules")
    public String telepules;

    @Column(name = "utca")
    public String utca;

    public Hely() {
    }

    public Hely(int az, String telepules, String utca) {
        this.az = az;
        this.telepules = telepules;
        this.utca = utca;
    }

    public int getAz() {
        return az;
    }

    public void setAz(int az) {
        this.az = az;
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
