package Fonction;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Jeu {
<<<<<<< Updated upstream
    public static void jeu() {
=======

    static boolean partieEnCours = true;

    // Liste pour stocker les joueurs
    static List<Joueur> listeJoueurs = new ArrayList<>();

    public static void initialisationJeu() {
>>>>>>> Stashed changes
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        int nombreJoueur = 2;


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
<<<<<<< Updated upstream
            Joueur joueur = new Joueur(positionX, positionY, i); // création d'un joueur
            joueurs.add(joueur); // Ajouter le joueur à la liste
=======
            Joueur joueur = new Joueur(positionX, positionY, id); // création d'un joueur
            listeJoueurs.add(joueur); // Ajouter le joueur à la liste
>>>>>>> Stashed changes
        }

        // Ajouter les joueurs à la matrice
        for (Joueur joueur : listeJoueurs){
            Matrice.ajouterJoueur(matrice, joueur);
        }

        // Choisir aléatoirement le joueur qui commence
        Random random = new Random();
        int joueurCommenceIndex = random.nextInt(listeJoueurs.size());
        Joueur joueurCommence = listeJoueurs.get(joueurCommenceIndex);

        // Afficher le joueur qui commence
        System.out.println("Le joueur " + joueurCommence.getId() + " commence !");

<<<<<<< Updated upstream
        // Afficher la matrice avec les joueurs depuis la classe AffichageMatrice
        Matrice.affichageMatrice(matrice);
=======
        // On appelle la fonction boucle de jeu
        boucleJeu(matrice, listeJoueurs);
    }

    public static void boucleJeu(int[][] matrice, List<Joueur> listeJoueurs ){

        // boucle de jeu
        while(partieEnCours){
            Matrice.affichageMatrice(matrice);
            // fonction deplacement
            Matrice.affichageMatrice(matrice);
            DestructionCase.destructionCase(matrice);
            // Conditions de fin
            if(listeJoueurs.size() == 1){ // Si un seul joueur restant
                partieEnCours = false;
            }

        }
        Matrice.affichageMatrice(matrice);
    }

    public static void joueurEstMort(int[][] matrice, Joueur joueur){
        int positionX = joueur.getPositionX();
        int positionY = joueur.getPositionY();

        int caseDroite = matrice[positionX + 1][positionY];
        int caseGauche = matrice[positionX - 1][positionY];
        int caseBas = matrice[positionX][positionY + 1];
        int caseHaut = matrice[positionX][positionY - 1];

        if (caseDroite != 0 && caseGauche != 0 && caseBas != 0 && caseHaut != 0){
            listeJoueurs.remove(joueur);
            System.out.println(joueur.getId() + "est dead");
        }

>>>>>>> Stashed changes
    }



}
