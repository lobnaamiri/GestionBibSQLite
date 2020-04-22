package com.example.gestionbinsqlite;

public class Livre {
    private int id;
    private String titre;
    private int nbpage;

    public Livre(int id, String titre, int nbpage) {
        this.id = id;
        this.titre = titre;
        this.nbpage = nbpage;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getNbpage() {
        return nbpage;
    }

    @Override
    public String toString() {
        return id+" - "+titre+" - "+nbpage;
    }
}
