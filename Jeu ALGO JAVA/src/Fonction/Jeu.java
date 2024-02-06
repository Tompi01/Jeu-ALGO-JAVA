package Fonction;

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

        // Afficher la matrice avec les joueurs depuis la classe AffichageMatrice
        Matrice.affichageMatrice(matrice);
    }
}
