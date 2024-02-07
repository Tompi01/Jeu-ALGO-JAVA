package Fonction;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestructionCase {

    public static void destructionCase(int[][] matrice){

        System.out.println("Entrez la case à détruire (exemple : A1)");
        Scanner scannerDestructionCase = new Scanner(System.in);
        try {
            String caseADetruire = scannerDestructionCase.nextLine().toUpperCase();

            // Utiliser une regex pour valider le format de l'entrée
            Pattern pattern = Pattern.compile("^([A-K])\\s*([1-9]|10)$");
            Matcher matcher = pattern.matcher(caseADetruire);

            if (matcher.matches()) {
                char lettre = matcher.group(1).charAt(0);
                int ligne = Integer.parseInt(matcher.group(2));
                int colonne = lettre - 64;
                if (matrice[colonne][ligne] != 0){
                    System.out.println("Veuillez détruire une case libre");
                }else {
                    matrice[colonne][ligne] = 1;
                }


                // Faire quelque chose avec la lettre et le chiffre ici (par exemple, détruire la case dans la matrice)
            } else {
                System.out.println("Format d'entrée incorrect. La lettre doit être entre A et K, et le chiffre entre 1 et 10.");
            }

        } catch (Exception e) {
            System.out.println("Erreur de saisie.");
        }


        // matrice[caseADetruireX][caseADetruireY] = 1;
    }
}
