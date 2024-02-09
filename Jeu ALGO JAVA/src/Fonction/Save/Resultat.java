package Fonction.Save;
import java.io.Serializable;

public class Resultat implements Serializable {
    private String nomJoueur;
    private int score;

    public Resultat(String nomJoueur, int score) {
        this.nomJoueur = nomJoueur;
        this.score = score;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public int getScore() {
        return score;
    }
}
