package Fonction;

public class Matrice {
    public static int[][] creationMatrice() {
        int ligne = 12;  // 10 lignes plus 2 bordures
        int colonne = 13; // 11 colones plus 2 bordures

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

    public static void ajouterJoueur(int[][] matrice, Joueur joueur) {
        matrice[joueur.getPositionX()][joueur.getPositionY()] = 2; // On utilise 2 pour représenter la position du joueur
    }

    public static void affichageMatrice(int[][] matrix) {
        // Parcourir les lignes de la matrice
        for (int ligne = 0; ligne < matrix[0].length; ligne++) {
            // Parcourir les colonnes de la matrice
            for (int colonne = 0; colonne < matrix.length; colonne++) {
                // Si c'est une bordure
                if (matrix[colonne][ligne] == 1) { // 1 est la valeur égale à une bordure dans notre matrice
                    // Affichage pour la bordure de droite
                    if (colonne == matrix.length - 1) {
                        System.out.print("#");
                    }
                    // Affichage pour les bordures du haut et du bas
                    else if (ligne == 0 || ligne == matrix.length - 2) {
                        System.out.print("# # ");
                    }
                    // Affichage pour la bordure de gauche
                    else {
                        System.out.print("# | ");
                    }
                    // Si c'est un joueur
                } else if (matrix[colonne][ligne] == 2) { // 2 est la valeur égale à un joueur dans notre matrice
                    System.out.print("X | "); // Affiche X pour les positions des joueurs
                } else {
                    // Affiche . pour les cases sans joueur
                    System.out.print(". | ");
                }
            }
            // Afficher le numéro de la ligne
            if (ligne != 0 && ligne != matrix.length - 2) {
                System.out.println("  " + (ligne + 1));
            } else {
                System.out.println();
            }
        }

        // Afficher la lettre de la colonne
        System.out.println("    A   B   C   D   E   F   G   H   I   J   K  ");
    }
}
