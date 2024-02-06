package Fonction;

public class affichageMatrice {

    public static void affichageMatrice(int[][] matrix){
        //Affichage de la matrice
        for (int i = 1; i < matrix.length-1; i++) {
            for (int j = 1; j < matrix[0].length-1; j++) {
                System.out.print(matrix[i][j] + " | ");

            }
            System.out.println("  " + i);
        }
        System.out.println();
        System.out.println("A | B | C | D | E | F | G | H | I | J | K |");
    }
}
