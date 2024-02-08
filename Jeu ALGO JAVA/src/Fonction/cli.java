package Fonction;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Fonction.Jeu.initialisationJeu;

public class cli {
    public static void menu() {
        // Efface la console pour une nouvelle interaction utilisateur
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Initialise un scanner pour lire l'entrée utilisateur
        Scanner scanner = new Scanner(System.in);

        // Affiche le message de bienvenue et le menu
        System.out.println("\n    BIENVENUE SUR CHESS DESTRUCT\r\n\n");
        System.out.println("1 - Commencer une partie    \n2 - Règles  \n3 - Scores   \n4 - Quitter\r\n");

        try {
            // Lit le choix de l'utilisateur pour le menu
            int response = scanner.nextInt();

            // Utilise une instruction switch pour traiter différentes options du menu
            switch (response) {
                case 1:
                    // Option 1: le joueur lance le jeu
                    initialisationJeu();
                    break;
                case 2:
                    // Option 2: Affiche les règles, puis attend pendant 5 secondes
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
                    menu(); // Appelle la fonction menu
                    break;
                case 3:
                    // Option 3: Affiche "Scores" à tout moment
                    System.out.println("Scores");
                    Jeu.afficherScores();
                    Jeu.afficherMenuFinPartie();

                    // Attendre l'entrée de l'utilisateur pour le menu de fin de partie
                    int finPartieResponse = scanner.nextInt();
                    switch (finPartieResponse) {
                        case 1:
                            // Rejouer une partie
                            initialisationJeu();
                            break;
                        case 2:
                            // Voir le tableau des scores
                            Jeu.afficherScores();
                            break;
                        case 3:
                            // Revenir au menu principal
                            menu();
                            break;
                        default:
                            System.out.println("Option invalide");
                            break;
                    }
                    break;
                case 4:
                    // Option 4: Affiche "Quitter" et quitte le programme
                    System.out.println("Quitter");
                    System.exit(0);
                default:
                    // Cas par défaut pour une entrée invalide
                    System.out.println("Rentrez un chiffre entier en 1 et 4");

                    try {
                        Thread.sleep(1000); // Attendre pendant 1 seconde
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    menu(); // Appelle la fonction menu
                    break;
            }
            scanner.close();
        } catch (InputMismatchException e) {
            // Gère l'exception si l'utilisateur entre une valeur non entière
            System.out.println("Rentrez un CHIFFRE entier en 1 et 4");
            try {
                Thread.sleep(1000); // Attendre pendant 1 seconde
            } catch (InterruptedException r) {
                throw new RuntimeException(r);
            }
            menu();
        }
    }

}
