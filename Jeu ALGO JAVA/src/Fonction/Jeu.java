package Fonction;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Jeu {

    // Liste pour stocker les joueurs
    static List<Joueur> listeJoueurs = new ArrayList<>();

    public static void initialisationJeu() {
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        int nombreJoueur = 2;

        // Liste pour stocker les joueurs
        List<Joueur> joueurs = new ArrayList<>();

        // Créer le nombre de joueurs en conséquence
        for (int id = 2; id <= nombreJoueur + 1; id++) {
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
            listeJoueurs.add(joueur); // Ajouter le joueur à la liste
        }


        // Ajouter les joueurs à la matrice
        for (Joueur joueur : listeJoueurs) {
            Matrice.ajouterJoueur(matrice, joueur);
        }

        // Choisir aléatoirement le joueur qui commence
        Random random = new Random();
        int joueurCommenceIndex = random.nextInt(listeJoueurs.size());
        Joueur joueurCommence = listeJoueurs.get(joueurCommenceIndex);

        // Afficher le joueur qui commence
        System.out.println("Le joueur " + (joueurCommence.getId()-1) + " commence !");

        // On appelle la fonction boucle de jeu
        boucleJeu(matrice, joueurCommence);

        System.out.println("Le joueur" + (listeJoueurs.get(0).getId() - 1) + " à gagné");
    }

    public static void boucleJeu(int[][] matrice, Joueur joueurCommence) {

        // boucle de jeu
        while (true) {
            // On créer une copie de la liste des joueurs pour éviter l'erreur 'ConcurrentModificationException'
            List<Joueur> copieListeJoueurs = new ArrayList<>(listeJoueurs);

            // On update la liste des joueurs en vie
            for (Joueur joueur : copieListeJoueurs) {
                joueurEstMort(matrice,joueur);
            }
            // Conditions de fin
            if (listeJoueurs.size() == 1) { // Si un seul joueur restant
                break;
            }
            //Matrice.affichageMatrice(matrice);
            // fonction deplacement

            if (listeJoueurs.size() == 1) { // Si un seul joueur restant
                break;
            }
            Matrice.affichageMatrice(matrice);
            DestructionCase.destructionCase(matrice);
            // Conditions de fin
            if (listeJoueurs.size() == 1) { // Si un seul joueur restant
                break;
            }

        }
        Matrice.affichageMatrice(matrice);
        System.out.println("FIN DE LA PARTIE");
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
