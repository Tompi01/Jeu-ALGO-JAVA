package Fonction;
public class creationMatrice {

    public static int[][] creationMatrice() {
        int colonne = 12;
        int ligne = 13;
        //déclarer la matrice
        int[][] matrice = new int[colonne][ligne];
        //Remplissage de la matrice avec des zéros
        for (int i = 0; i < colonne; i++) {
            for (int j = 0; j < ligne; j++) {
                matrice[i][j] = 0;
            }
        }
        return matrice;
    }
}
