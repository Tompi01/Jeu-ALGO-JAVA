package Fonction;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Jeu {
    public static void jeu() {
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        int nombreJoueur = 2;

        // Liste pour stocker les joueurs
        List<Joueur> joueurs = new ArrayList<>();

        // Créer le nombre de joueurs en conséquence
        for (int i = 2; i <= nombreJoueur+1; i++) {
            int positionX, positionY;

            // Définir  les positions des joueurs
            if (i == 2) {
                positionX = 6;
                positionY = 4;
            } else {
                positionX = 6;
                positionY = 6;
            }
            Joueur joueur = new Joueur(positionX, positionY, i); // Position initiale du joueur
            joueurs.add(joueur); // Ajouter le joueur à la liste
        }

        // Ajouter les joueurs à la matrice
        for (Joueur joueur : joueurs) {
            Matrice.ajouterJoueur(matrice, joueur);
        }

        // Choisir aléatoirement le joueur qui commence
        Random random = new Random();
        int joueurCommenceIndex = random.nextInt(joueurs.size());
        Joueur joueurCommence = joueurs.get(joueurCommenceIndex);

        // Afficher le joueur qui commence
        System.out.println("Le joueur " + joueurCommence.getId() + " commence !");

        // Afficher la matrice avec les joueurs depuis la classe AffichageMatrice
        Matrice.affichageMatrice(matrice);
    }
}
