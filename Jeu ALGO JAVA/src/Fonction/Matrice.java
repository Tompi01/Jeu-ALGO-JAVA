package Fonction;

import static Fonction.cli.effacerConsole;

/**
 * Classe représentant la matrice du jeu.
 */
public class Matrice {

    /**
     * Crée une matrice représentant le plateau de jeu.
     *
     * @return La matrice du plateau de jeu.
     */
    public static int[][] creationMatrice() {
        int ligne = 12;  // 10 lignes plus 2 bordures
        int colonne = 13; // 11 colonnes plus 2 bordures

        // Déclarer la matrice
        int[][] matrice = new int[colonne][ligne];

        // Remplissage de la matrice avec des zéros
        for (int l = 0; l < ligne; l++) {
            for (int c = 0; c < colonne; c++) {
                // Vérifier si la case est à l'intérieur de la zone déplaçable
                if (c >= 1 && c <= 11 && l >= 1 && l <= 10) {
                    // La zone déplaçable est remplie de 0, les autres cases sont des #
                    matrice[c][l] = 0;
                } else {
                    matrice[c][l] = 1; // Les cases en dehors de la zone déplaçable sont des #
                }
            }
        }
        return matrice;
    }

    /**
     * Ajoute un joueur à la matrice à sa position.
     *
     * @param matrice Matrice du plateau de jeu.
     * @param joueur  Joueur à ajouter.
     */
    public static void ajouterJoueur(int[][] matrice, Joueur joueur) {
        // Utiliser la valeur du joueur pour le représenter sur la matrice
        matrice[joueur.getPositionX()][joueur.getPositionY()] = joueur.getId();
    }

    /**
     * Affiche la matrice du plateau de jeu avec les joueurs et les bordures.
     *
     * @param matrice Matrice du plateau de jeu.
     */
    public static void affichageMatrice(int[][] matrice) {
        effacerConsole();
        // Parcourir les lignes de la matrice
        for (int ligne = 0; ligne < matrice[0].length; ligne++) {
            // Parcourir les colonnes de la matrice
            for (int colonne = 0; colonne < matrice.length; colonne++) {
                // Si c'est une bordure
                if (matrice[colonne][ligne] == 1) { // 1 est la valeur égale à une bordure dans notre matrice
                    // Affichage pour la bordure de droite
                    if (colonne == matrice.length - 1) {
                        System.out.print("#");
                    }
                    // Affichage pour les bordures du haut et du bas
                    else if (ligne == 0 || ligne == matrice.length - 2) {
                        System.out.print("# # ");
                    }
                    // Affichage pour la bordure de gauche
                    else {
                        System.out.print("# | ");
                    }
                    // Si c'est un joueur
                } else if (matrice[colonne][ligne] >= 2) { // 2 est la valeur égale à un joueur dans notre matrice
                    System.out.print((matrice[colonne][ligne] - 1) + " | "); // Affiche l'id pour les positions des joueurs
                } else {
                    // Affiche . pour les cases sans joueur
                    System.out.print(". | ");
                }
            }
            // Afficher le numéro de la ligne
            if (ligne != 0 && ligne != matrice.length - 2) {
                System.out.println("  " + (ligne));
            } else {
                System.out.println();
            }
        }

        // Afficher la lettre de la colonne
        System.out.println("    A   B   C   D   E   F   G   H   I   J   K  ");
    }
}
