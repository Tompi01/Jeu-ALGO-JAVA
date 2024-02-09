package Fonction;

import Fonction.Save.ChargeurResultats;
import Fonction.Save.Resultat;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

/**
 * Classe gérant l'interface utilisateur en ligne de commande.
 */
public class cli {
    // Scanner pour lire l'entrée utilisateur
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Fonction principale du menu.
     */
    public static void menu() {
        effacerConsole(); // Efface la console pour une nouvelle interaction utilisateur
        System.out.println("\n    BIENVENUE SUR CHESS DESTRUCT\r\n\n");
        System.out.println("1 - Commencer une partie    \n2 - Règles  \n3 - Scores   \n4 - Charger une sauvegarde \n5 - Quitter\r\n");

        try {
            int response = lireEntier("Veuillez choisir une option (1-5): ");
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
                    // Option 5: Affiche "Quitter" et quitte le programme
                    System.out.println("Quitter");
                    System.exit(0);
                    break;
                default:
                    gestionErreur("Rentrez un chiffre entier en 1 et 5", 1000);
                    menu();
                    break;
            }
        } catch (InputMismatchException e) {
            gestionErreur("Rentrez un CHIFFRE entier en 1 et 5", 1000);
        }
    }

    /**
     * Fonction pour effacer la console.
     */
    public static void effacerConsole() {
        // System.out.println("effacer console");
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
    }


    /**
     * Fonction pour afficher les règles.
     */
    private static void afficherRegles() {
        effacerConsole();
        System.out.println("Règles : ");
        System.out.println("Pendant son tour, un joueur peut déplacer son pion d’une case (verticalement ou horizontalement), puis il détruit une case du plateau.\n" +
                "Le dernier joueur pouvant encore se déplacer gagne.\n" +
                "Contraintes :\n" +
                "- Un joueur ne peut pas détruire une case occupée.\n" +
                "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" + "- Un joueur bloqué pendant un tour est déclaré perdant.");

        try {
            Thread.sleep(5000); // Attendre pendant 5 secondes
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        menu(); // Revenir au menu principal
    }

    /**
     * Affiche les options pour l'utilisateur.
     *
     * @param tri L'ordre de tri : 1 pour croissant, 2 pour décroissant, autre pour l'ordre d'ajout.
     */
    public static void afficherOptions(int tri) {
        System.out.println("\n1 - Autre tri \n2 - Menu principal");

        try {
            int choix = lireEntier("Veuillez choisir une option (1-2): ");
            switch (choix) {
                case 1:
                    scores();
                    break;
                case 2:
                    menu();
                    break;
                default:
                    gestionErreur("Option invalide", 1000);
                    scores();
                    break;
            }
        } catch (InputMismatchException e) {
            gestionErreur("Veuillez entrer un nombre entier.", 1000);
        }
    }

    /**
     * Fonction pour afficher les scores.
     */
    public static void scores() {
        effacerConsole();
        // Attendre l'entrée de l'utilisateur pour afficher le score
        System.out.println("1 - pas trié \n2 - tri décroissant \n3 - tri croissant \n4 - retour");

        try {
            int reponse = lireEntier("Veuillez choisir une option (1-4): ");
            switch (reponse) {
                case 1:
                    gestionScore.afficherScores(3);
                    break;
                case 2:
                    gestionScore.afficherScores(1);
                    break;
                case 3:
                    gestionScore.afficherScores(2);
                    break;
                case 4:
                    menu();
                    break;
                default:
                    gestionErreur("Option invalide", 1000);
                    scores();
                    break;
            }
        } catch (InputMismatchException e) {
            gestionErreur("Veuillez entrer un nombre entier.", 1000);
        }
    }

    /**
     * Fonction pour charger les résultats.
     */
    private static void chargerResultats() {
        effacerConsole();
        try {
            ChargeurResultats.chargerScores("resultats_partie.txt");
            gestionErreur("Les scores ont bien été chargés depuis le fichier", 2000);

        } catch (Exception e) {
            gestionErreur("Erreur dans le chargement du fichier (fichier inexistant)", 2000);
        }
        menu();

    }
        /**
         * Fonction pour lire un entier depuis l'entrée utilisateur.
         *
         * @param message Message à afficher avant de lire l'entier.
         * @return Entier lu depuis l'entrée utilisateur.
         */
        public static int lireEntier(String message) {
            System.out.print(message);
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.next(); // Consommer l'entrée invalide
            }
            return scanner.nextInt();
        }
    }

