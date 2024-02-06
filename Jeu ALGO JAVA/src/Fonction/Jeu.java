package Fonction;
import java.util.Random;

public class Jeu {
    public static void jeu() {
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        // Créer deux joueurs
        Joueur joueur1 = new Joueur(6, 4); // Position initiale du joueur 1
        Joueur joueur2 = new Joueur(6, 6); // Position initiale du joueur 2

        // Ajouter les joueurs à la matrice
        Matrice.ajouterJoueur(matrice, joueur1);
        Matrice.ajouterJoueur(matrice, joueur2);

        // Choisir aléatoirement le joueur qui commence
        Random random = new Random();
        boolean joueur1Commence = random.nextBoolean();

        // Afficher le message du joueur qui commence
        if (joueur1Commence) {
            System.out.println("Le joueur 1 commence !");
        } else {
            System.out.println("Le joueur 2 commence !");
        }

        // Afficher la matrice avec les joueurs depuis la classe AffichageMatrice
        Matrice.affichageMatrice(matrice);
    }
}
