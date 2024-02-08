package Fonction;
import Fonction.Save.ChargeurResultats;
import Fonction.Save.Resultat;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe gérant l'interface utilisateur en ligne de commande.
 */
public class cli {
    // Scanner pour lire l'entrée utilisateur
    private static Scanner choixMenuEntree = new Scanner(System.in);

    /**
     * Fonction principale du menu.
     */
    public static void menu() {
        effacerConsole(); // Efface la console pour une nouvelle interaction utilisateur
        System.out.println("\n    BIENVENUE SUR CHESS DESTRUCT\r\n\n");
        System.out.println("1 - Commencer une partie    \n2 - Règles  \n3 - Scores   \n4 - Charger une sauvegarde \n5 - Quitter\r\n");

        try {
            int response = choixMenuEntree.nextInt(); // Lit le choix de l'utilisateur

            switch (response) {
                case 1:
                    Jeu.initialisationJeu();
                    break;
                case 2:
                    afficherRegles();
                    break;
                case 3:
                    scores();
                    break;
                case 4:
                    chargerResultats();
                    break;
                case 5:
                    System.out.println("Quitter");
                    System.exit(0);
                    break;
                default:
                    gestionErreur("Rentrez un chiffre entier en 1 et 5", 1000);
                    break;
            }
        } catch (InputMismatchException e) {
            gestionErreur("Rentrez un CHIFFRE entier en 1 et 5", 1000);
        }
    }

    /**
     * Fonction pour effacer la console.
     */
    private static void effacerConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Fonction pour gérer les erreurs.
     *
     * @param message Message d'erreur à afficher.
     * @param attente Durée d'attente en millisecondes après l'affichage du message d'erreur.
     */
    public static void gestionErreur(String message, int attente) {
        System.out.println(message);
        try {
            Thread.sleep(attente);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menu();
    }

    /**
     * Fonction pour afficher les règles.
     */
    private static void afficherRegles() {
        System.out.println("Règles : ");
        System.out.println("Pendant son tour, un joueur peut déplacer son pion d’une case (verticalement ou horizontalement), puis il détruit une case du plateau.\n" +
                "Le dernier joueur pouvant encore se déplacer gagne.\n" +
                "Contraintes :\n" +
                "- Un joueur ne peut pas détruire une case occupée.\n" +
                "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +"- Un joueur bloqué pendant un tour est déclaré perdant.");

        try {
            Thread.sleep(2000); // Attendre pendant 2 secondes
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        menu(); // Revenir au menu principal
    }

    /**
     * Fonction pour afficher les scores.
     */
    public static void scores() {
        // Attendre l'entrée de l'utilisateur pour afficher le score
        System.out.println("1 - pas trié \n2 - tri décroissant \n3 - tri croissant \n4 - retour");
        int reponse = choixMenuEntree.nextInt();

        switch (reponse) {
            case 1:
                gestionScore.afficherScores(3); // On appelle la fonction avec 3 en parametre pour ne pas trier la liste
                break;
            case 2:
                gestionScore.afficherScores(1); // On appelle la fonction avec 1 en parametre pour trier de maniere décroissante
                break;
            case 3:
                gestionScore.afficherScores(2); // On appelle la fonction avec 2 en parametre pour trier de maniere croissante
                break;
            case 4:
                menu();
                break;
            default:
                gestionErreur("Option invalide", 0);
                break;
        }
    }

    /**
     * Fonction pour charger les résultats.
     */
    private static void chargerResultats() {
        List<Resultat> resultatsCharges = ChargeurResultats.chargerResultats("resultats_partie.ser");

        if (resultatsCharges != null) {
            System.out.println("Les résultats ont été chargés depuis le fichier.");
            menu();
        }
    }
}
