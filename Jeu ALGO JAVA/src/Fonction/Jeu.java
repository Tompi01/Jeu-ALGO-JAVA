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

/**
 * Classe gérant la logique du jeu.
 */
public class Jeu {

    /**
     * Affiche le menu à la fin de la partie.
     */
    public static void afficherMenuFinPartie() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1 - Rejouer une partie");
        System.out.println("2 - Voir le tableau des scores");
        System.out.println("3 - Sauvegarder");
        System.out.println("4 - Revenir au menu principal");
    }

    // Liste pour stocker les joueurs
    static List<Joueur> listeJoueurs = new ArrayList<>();

    /**
     * Initialise le jeu.
     */
    public static void initialisationJeu() {
        int[][] matrice = Matrice.creationMatrice();
        Joueur.genererJoueurs(listeJoueurs);

        for (Joueur joueur : listeJoueurs) {
            Matrice.ajouterJoueur(matrice, joueur);
        }

        Random random = new Random();
        int joueurCommenceIndex = random.nextInt(listeJoueurs.size());
        Joueur joueurCommence = listeJoueurs.get(joueurCommenceIndex);

        System.out.println((joueurCommence.getPseudo()) + " (" + (joueurCommence.getId()-1) + ")" +" commence !");

        boucleJeu(matrice, joueurCommence);

        System.out.println(listeJoueurs.get(0).getPseudo() + " à gagné");
        gestionScore.mettreAJourScores(listeJoueurs.get(0).getPseudo(), true);
        listeJoueurs.get(0).incrementerScore(5);
        listeJoueurs.clear(); // On vide la liste des Joueurs en prévision d'une prochaine partie

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

    /**
     * Gère la boucle principale du jeu.
     *
     * @param matrice Matrice du jeu.
     * @param joueur Joueur actuel.
     */
    public static void boucleJeu(int[][] matrice, Joueur joueur) {
        int indexEnTrainDeJouer;

        // Boucle de jeu
        while (true) {
            indexEnTrainDeJouer = listeJoueurs.indexOf(joueur);
            List<Joueur> copieListeJoueurs = new ArrayList<>(listeJoueurs);

            // Mise à jour de la liste des joueurs en vie
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

            // Changement de joueur en cours
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

    /**
     * Vérifie si un joueur est mort et le supprime de la liste s'il est mort.
     *
     * @param matrice Matrice du jeu.
     * @param joueur Joueur à vérifier.
     */
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
            System.out.println(joueur.getPseudo() + " est mort");
        }
    }
}
