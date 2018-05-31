package com.tonikamitv.loginregister;

/**
 * Created by RBS on 28-Jan-18.
 */

public class Annonce {
    int id;
    String titre;
    String description;
    String nbrmanquant;
    User user;

    public Annonce(int id,String titre, String description,String nbrmanquant) {
        this.id=id;
        this.titre = titre;
        this.description = description;
        this.nbrmanquant=nbrmanquant;
    }
    public Annonce(int id,String titre, String description,String nbrmanquant,User user) {
        this.id=id;
        this.titre = titre;
        this.description = description;
        this.nbrmanquant=nbrmanquant;
        this.user=user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNbrmanquant() {
        return nbrmanquant;
    }

    public void setNbrmanquant(String nbrmanquant) {
        this.nbrmanquant = nbrmanquant;
    }
}
