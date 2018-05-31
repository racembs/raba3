package com.tonikamitv.loginregister;

/**
 * Created by RBS on 30-May-18.
 */

public class Demande {

    User user;
    Annonce annonce;
    int etat;

    public Demande(User user, Annonce annonce) {
        this.user = user;
        this.annonce = annonce;

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
