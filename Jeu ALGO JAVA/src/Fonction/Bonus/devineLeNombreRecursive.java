package Fonction.Bonus;
import Fonction.*;

import java.util.Random;
import java.util.Scanner;

public class devineLeNombreRecursive {


    public static void jeuDeDivination(Joueur joueur, int[][] matrice) {
        int essaiActuel = 0;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int nombreATrouver = random.nextInt(9) + 1; // Nombre aléatoire entre 1 et 10

        System.out.println("Bienvenue dans le jeu de Divination codé en récursif, qui est un easter egg bien caché !");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Trouvez le nombre entre 1 et 10.");

        boolean resultat = devinerNombre(scanner, nombreATrouver, 1);

        if (resultat) {
            System.out.println("Bravo, vous avez trouvé le nombre !");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cli.gestionErreur("Tu vas pouvoir détruire 3 cases d'affilée.", 2000);
            Matrice.affichageMatrice(matrice);
            DestructionCase.destructionCase(matrice);
            Matrice.affichageMatrice(matrice);
            DestructionCase.destructionCase(matrice);
            Matrice.affichageMatrice(matrice);
            DestructionCase.destructionCase(matrice);
            Matrice.affichageMatrice(matrice);

        } else if (essaiActuel > 3) {
            System.out.println("Désolé, vous avez épuisé vos essais. Le nombre était : " + nombreATrouver);
        }
        else{
            devinerNombre(scanner, nombreATrouver, essaiActuel);
        }

    }

    public static boolean devinerNombre(Scanner scanner, int nombreATrouver, int essaiActuel) {
        if (essaiActuel == 5) {
            return false; // Si l'utilisateur a utilisé tous ses essais
        }

        System.out.print("Tentative numéro " + essaiActuel + " : Entrez votre proposition please  : ");
        try {
            int proposition = scanner.nextInt();

            if (proposition == nombreATrouver) {
                return true; // Si l'utilisateur a trouvé le nombre
            } else if (proposition < nombreATrouver) {
                System.out.println("Trop petit bien tenter!");
            } else {
                System.out.println("Trop granderino !");
            }
        }catch(Exception e){
            System.out.println("Reessaye avec un nombre.");
        }

        // Appel récursif pour l'essai suivant
        return devinerNombre(scanner, nombreATrouver, essaiActuel + 1);
    }


}
