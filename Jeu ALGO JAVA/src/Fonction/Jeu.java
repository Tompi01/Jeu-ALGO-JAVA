package Fonction;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import static Fonction.cli.menu;

public class Jeu {

    static Map<String, Integer> scores = new HashMap<>();

    // Méthode pour mettre à jour les scores à la fin de chaque partie
    public static void mettreAJourScores(String joueurGagnant) {
        // Incrémente le score du joueur gagnant
        scores.put(joueurGagnant, scores.getOrDefault(joueurGagnant, 0) + 5);
        // Incrémente le score des autres joueurs
        for (String joueur : scores.keySet()) {
            if (!joueur.equals(joueurGagnant)) {
                scores.put(joueur, scores.getOrDefault(joueur, 0) + 2);
            }
        }
    }

    // Méthode pour afficher les scores
    public static void afficherScores() {
        System.out.println("Scores :");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " points");
        }
    }

    public static void afficherMenuFinPartie() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1 - Rejouer une partie");
        System.out.println("2 - Voir le tableau des scores");
        System.out.println("3 - Revenir au menu principal");
    }

    // Liste pour stocker les joueurs
    static List<Joueur> listeJoueurs = new ArrayList<>();

    public static void initialisationJeu() {
        // Appeler la création de la matrice depuis la classe Matrice
        int[][] matrice = Matrice.creationMatrice();

        int nombreJoueur = 2;

        // Liste pour stocker les joueurs
        List<Joueur> joueurs = new ArrayList<>();
        // Liste pour stocker les pseudos déjà saisis
        ArrayList<String> pseudos = new ArrayList<>();

        // Créer le nombre de joueurs en conséquence
        for (int id = 2; id <= nombreJoueur + 1; id++) {
            int positionX, positionY;
            String pseudo;
            Scanner entreePseudo = new Scanner(System.in);

            // Boucle do-while pour demander le pseudo jusqu'à ce qu'il soit valide
            do {
                // Demande le pseudo du joueur
                System.out.println("Entre le pseudo du joueur " + (id - 1));
                pseudo = entreePseudo.nextLine();

                // Vérifie la longueur du pseudo
                if (pseudo.length() < 2 || pseudo.length() > 10) {
                    System.out.println("Votre pseudo doit contenir entre 2 et 10 caractères.");
                } else if (pseudos.contains(pseudo)) {
                    // Vérifie si le pseudo est déjà pris
                    System.out.println("Ce pseudo est déjà utilisé. Veuillez choisir un pseudo unique.");
                }
            } while (pseudo.length() < 2 || pseudo.length() > 10 || pseudos.contains(pseudo));

            // Ajoute le pseudo à la liste des pseudos
            pseudos.add(pseudo);

            // Définir  les positions des joueurs
            if (id == 2) {
                positionX = 6;
                positionY = 5;

            } else {
                positionX = 6;
                positionY = 6;
            }
            // Création du joueur
            Joueur joueur = new Joueur(positionX, positionY, id, pseudo);

            // Ajout du joueur dans une liste contenant tout les joueurs
            listeJoueurs.add(joueur);
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
        System.out.println((joueurCommence.getPseudo()) + " (" + (joueurCommence.getId()-1) + ")" +" commence !");

        // On appelle la fonction boucle de jeu
        boucleJeu(matrice, joueurCommence);

        System.out.println("Le joueur" + (listeJoueurs.get(0).getId() - 1) + " à gagné");

        mettreAJourScores(listeJoueurs.get(0).getPseudo());

        afficherMenuFinPartie();
        Scanner scanner = new Scanner(System.in);
        int finPartieResponse = scanner.nextInt();
        switch (finPartieResponse) {
            case 1:
                initialisationJeu(); // Rejouer une partie
                break;
            case 2:
                afficherScores(); // Voir les scores
                menu(); // Revenir au menu principal
                break;
            case 3:
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
            System.out.println("Le joueur " + (joueur.getId()-1) + " est dead");
        }
    }
}
