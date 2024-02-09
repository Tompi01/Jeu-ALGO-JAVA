package Fonction;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Fonction.Jeu.listeJoueurs;

public class DestructionCase {

    /**
     * Cette fonction permet de détruire une case dans une matrice en demandant à l'utilisateur de saisir la case à détruire.
     *
     * @param matrice La matrice dans laquelle la case doit être détruite.
     */
    public static void destructionCase(int[][] matrice) {
        // Demande à l'utilisateur de saisir la case à détruire
        System.out.println("Entrez la case à détruire (exemple : A1)");
        Scanner scannerDestructionCase = new Scanner(System.in);
        try {
            // Récupération de la saisie et conversion en majuscules
            String caseADetruire = scannerDestructionCase.nextLine().toUpperCase();
            // Utilisation d'une regex pour valider le format de l'entrée
            Pattern pattern = Pattern.compile("^([A-K])\\s*([1-9]|10)$");
            Matcher matcher = pattern.matcher(caseADetruire);
            if (matcher.matches()) {
                // Extraction de la lettre et du chiffre à partir de la saisie
                char lettre = matcher.group(1).charAt(0);
                int ligne = Integer.parseInt(matcher.group(2));
                // On converti le code ASCII de la lettre en indice de colonne
                int colonne = lettre - 64;

                // Vérification si la case est libre
                if (matrice[colonne][ligne] != 0) {
                    System.out.println("Veuillez détruire une case libre.");
                    destructionCase(matrice);
                } else {
                    // Destruction de la case dans la matrice
                    matrice[colonne][ligne] = 1;
                }
            } else {
                // Affichage d'un message d'erreur si le format de saisie est incorrect
                System.out.println("Format d'entrée incorrect. La lettre doit être entre A et K, et le chiffre entre 1 et 10.");
                destructionCase(matrice);
            }
        } catch (Exception e) {
            // Gestion des erreurs de saisie
            System.out.println("Erreur de saisie.");
            destructionCase(matrice);
        }
    }
    public static void joueurEstMort(int[][] matrice, Joueur joueur) {
        int positionX = joueur.getPositionX();
        int positionY = joueur.getPositionY();

        int caseDroite = matrice[positionX + 1][positionY];
        int caseGauche = matrice[positionX - 1][positionY];
        int caseBas = matrice[positionX][positionY + 1];
        int caseHaut = matrice[positionX][positionY - 1];

        if (caseDroite != 0 && caseGauche != 0 && caseBas != 0 && caseHaut != 0) {
            listeJoueurs.remove(joueur);
            System.out.println("Le joueur " + (joueur.getId()-1) + " est dead");
        }
    }
}
