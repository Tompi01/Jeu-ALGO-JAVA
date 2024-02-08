package Fonction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Fonction.cli.*;

import static Fonction.cli.lireEntier;

/**
 * Classe représentant un joueur dans le jeu.
 */
public class Joueur {
    private int positionX;
    private int positionY;
    private int id;
    private String pseudo;
    private int score;

    /**
     * Constructeur de la classe Joueur.
     *
     * @param positionX Position X initiale du joueur.
     * @param positionY Position Y initiale du joueur.
     * @param id Identifiant du joueur.
     * @param pseudo Pseudo du joueur.
     */
    public Joueur(int positionX, int positionY, int id, String pseudo) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.id = id;
        this.pseudo = pseudo;
    }

    /**
     * Getter de la position X du joueur.
     *
     * @return Position X du joueur.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Getter de la position Y du joueur.
     *
     * @return Position Y du joueur.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Setter des positions X et Y du joueur.
     *
     * @param newX Nouvelle position X du joueur.
     * @param newY Nouvelle position Y du joueur.
     */
    public void setPosition(int newX, int newY) {
        this.positionX = newX;
        this.positionY = newY;
    }

    /**
     * Getter de l'identifiant du joueur.
     *
     * @return Identifiant du joueur.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter du pseudo du joueur.
     *
     * @return Pseudo du joueur.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Setter du pseudo du joueur.
     *
     * @param pseudo Nouveau pseudo du joueur.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Getter du score du joueur.
     *
     * @return Score du joueur.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter du score du joueur.
     *
     * @param score Nouveau score du joueur.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Incrémente le score du joueur.
     *
     * @param points Points à ajouter au score.
     */
    public void incrementerScore(int points) {
        score += points;
    }

    /**
     * Génère les joueurs en fonction du nombre saisi par l'utilisateur.
     *
     * @param listeJoueurs Liste pour stocker les joueurs.
     */
    public static void genererJoueurs(List<Joueur> listeJoueurs) {
        // On demande le nombre de joueurs avec une boucle do-while
        int nombreJoueurs;
        do {
            nombreJoueurs = lireEntier("Entrez le nombre de joueurs (Entre 2 et 4): ");
            if (nombreJoueurs < 2 || nombreJoueurs > 4) {
                System.out.println("ON T'AS DIT ENTRE 2 ET 4");
            }
        } while (nombreJoueurs < 2 || nombreJoueurs > 4);

        // Liste pour stocker les pseudos déjà saisis
        ArrayList<String> pseudos = new ArrayList<>();

        // Créer le nombre de joueurs en conséquence
        for (int id = 2; id <= nombreJoueurs + 1; id++) {
            int positionColonne, positionLigne;
            String pseudo;
            Scanner entreePseudo = new Scanner(System.in);

            // Boucle do-while pour demander le pseudo jusqu'à ce qu'il soit valide
            do {
                // Demande le pseudo du joueur
                System.out.println("Entre le pseudo du joueur " + (id - 1));
                pseudo = entreePseudo.nextLine();

                // Vérifie la longueur du pseudo
                if (pseudo.length() < 2 || pseudo.length() > 10) {
                    System.out.println("Votre pseudo doit contenir entre 2 et 10 caractères.");
                } else if (pseudos.contains(pseudo)) {
                    // Vérifie si le pseudo est déjà pris
                    System.out.println("Ce pseudo est déjà utilisé. Veuillez choisir un pseudo unique.");
                }
            } while (pseudo.length() < 2 || pseudo.length() > 10 || pseudos.contains(pseudo));

            // Ajoute le pseudo à la liste des pseudos
            pseudos.add(pseudo);

            // Gérer la position des joueurs au départ en fonction de leur nombre
            if (nombreJoueurs == 2) {
                if (id == 2) {
                    positionColonne = 6;
                    positionLigne = 5;
                } else {
                    positionColonne = 6;
                    positionLigne = 6;
                }
            } else if (nombreJoueurs == 3) {
                if (id == 2) {
                    positionColonne = 5;
                    positionLigne = 5;
                } else if (id == 3) {
                    positionColonne = 6;
                    positionLigne = 6;
                } else {
                    positionColonne = 7;
                    positionLigne = 5;
                }
            } else {
                if (id == 2) {
                    positionColonne = 5;
                    positionLigne = 5;
                } else if (id == 3) {
                    positionColonne = 5;
                    positionLigne = 6;
                } else if (id == 4) {
                    positionColonne = 7;
                    positionLigne = 5;
                } else {
                    positionColonne = 7;
                    positionLigne = 6;
                }
            }

            // Création du joueur
            Joueur joueur = new Joueur(positionColonne, positionLigne, id, pseudo);

            // Ajout du joueur dans une liste contenant tous les joueurs
            listeJoueurs.add(joueur);
        }
    }
}
