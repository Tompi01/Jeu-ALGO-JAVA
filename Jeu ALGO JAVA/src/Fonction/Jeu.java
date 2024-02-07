package Fonction;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Jeu {
    public static void initialisationJeu() {
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        int nombreJoueur = 2;

        // Liste pour stocker les joueurs
        List<Joueur> joueurs = new ArrayList<>();

        // Créer le nombre de joueurs en conséquence
        for (int id = 2; id <= nombreJoueur+1; id++) {
            int positionX, positionY;

            // Définir  les positions des joueurs
            if (id == 2) {
                positionX = 6;
                positionY = 5;
            } else {
                positionX = 6;
                positionY = 6;
            }
            Joueur joueur = new Joueur(positionX, positionY, id); // création d'un joueur
            joueurs.add(joueur); // Ajouter le joueur à la liste
        }

        // Ajouter les joueurs à la matrice
        for (Joueur joueur : joueurs) {
            Matrice.ajouterJoueur(matrice, joueur);
        }

        // Choisir aléatoirement le joueur qui commence
        Random random = new Random();
        int joueurCommenceIndex = random.nextInt(joueurs.size());

        // Afficher le joueur qui commence
        System.out.println("Le joueur " + (joueurCommenceIndex+1) + " commence !");

        boolean partieEnCours = true;
        Matrice.affichageMatrice(matrice);
        // boucle de jeu
        while(partieEnCours){
            deplacement.deplacementDuJoueur(joueurs.get(joueurCommenceIndex), matrice);
            // DestructionCase.destructionCase(matrice);
            //changement de joueur en cours
            joueurCommenceIndex ++;
            if (joueurCommenceIndex==joueurs.size()) {
                joueurCommenceIndex = 0;
            }
            // Conditions de fin
            if(joueurs.size() == 1){ // Si un seul joueur restant
                partieEnCours = false;
            }
        }
        Matrice.affichageMatrice(matrice);
        System.out.println("félicitation le joueur " + joueurs.get(0) + " à gagner");

    }
}
