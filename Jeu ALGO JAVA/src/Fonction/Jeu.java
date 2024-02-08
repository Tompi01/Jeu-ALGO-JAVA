package Fonction;
import Fonction.Save.EnregistreurResultats;
import Fonction.Save.Resultat;
import Fonction.gestionScore.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import static Fonction.cli.menu;

public class Jeu {

    public static void afficherMenuFinPartie() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1 - Rejouer une partie");
        System.out.println("2 - Voir le tableau des scores");
        System.out.println("3 - Sauvegarder");
        System.out.println("4 - Revenir au menu principal");
    }

    // Liste pour stocker les joueurs
    static List<Joueur> listeJoueurs = new ArrayList<>();


    public static void initialisationJeu() {
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        Joueur.genererJoueurs(listeJoueurs);

        // Ajouter les joueurs à la matrice
        for (Joueur joueur : listeJoueurs) {
            Matrice.ajouterJoueur(matrice, joueur);
        }

        // Choisir aléatoirement le joueur qui commence
        Random random = new Random();
        int joueurCommenceIndex = random.nextInt(listeJoueurs.size());
        Joueur joueurCommence = listeJoueurs.get(joueurCommenceIndex);

        // Afficher le joueur qui commence
        System.out.println((joueurCommence.getPseudo()) + " (" + (joueurCommence.getId()-1) + ")" +" commence !");

        // On appelle la fonction boucle de jeu
        boucleJeu(matrice, joueurCommence);

        System.out.println(listeJoueurs.get(0).getPseudo() + " à gagné");
        gestionScore.mettreAJourScores(listeJoueurs.get(0).getPseudo(), true);
        listeJoueurs.get(0).incrementerScore(5);
        listeJoueurs.clear(); // On vide la liste des Joueurs en prévision d'un prochaine partie

        afficherMenuFinPartie();
        Scanner scanner = new Scanner(System.in);
        int finPartieResponse = scanner.nextInt();
        switch (finPartieResponse) {
            case 1:
                initialisationJeu(); // Rejouer une partie
                break;
            case 2:
                cli.scores(); // Voir les scores
                menu(); // Revenir au menu principal
                break;
            case 3:
                // À la fin de la partie
                List<Resultat> resultatsPartie = new ArrayList<>();

                // Ajoutez les résultats de chaque joueur à la liste
                for (Joueur joueur : listeJoueurs) {
                    Resultat resultatJoueur = new Resultat(joueur.getPseudo(), joueur.getScore());
                    resultatsPartie.add(resultatJoueur);
                }

                // Enregistrez les résultats dans un fichier
                EnregistreurResultats.enregistrerResultats(resultatsPartie, "resultats_partie.ser");
                menu();
                break;
            case 4:
                menu(); // Revenir au menu principal
                break;
            default:
                System.out.println("Option invalide");
                break;
        }

    }

    public static void boucleJeu(int[][] matrice, Joueur joueur) {

        int indexEnTrainDeJouer;
        // boucle de jeu
        while (true) {
            indexEnTrainDeJouer = listeJoueurs.indexOf(joueur);
            // On créer une copie de la liste des joueurs pour éviter l'erreur 'ConcurrentModificationException'
            List<Joueur> copieListeJoueurs = new ArrayList<>(listeJoueurs);

            // On update la liste des joueurs en vie
            for (Joueur joueurDansListe : copieListeJoueurs) {
                joueurEstMort(matrice,joueurDansListe);
            }
            // Conditions de fin
            if (listeJoueurs.size() == 1) { // Si un seul joueur restant
                break;
            }

            Matrice.affichageMatrice(matrice);
            
            deplacement.deplacementDuJoueur(joueur, matrice);

            Matrice.affichageMatrice(matrice);

            DestructionCase.destructionCase(matrice);

            //changement de joueur en cours
            if (indexEnTrainDeJouer == listeJoueurs.size()-1) {
                joueur = listeJoueurs.get(0);
            }
            else {
                joueur = listeJoueurs.get(indexEnTrainDeJouer+1);
            }
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
            gestionScore.mettreAJourScores(joueur.getPseudo(), false);
            System.out.println(joueur.getPseudo() + " est dead");
        }
    }
}
