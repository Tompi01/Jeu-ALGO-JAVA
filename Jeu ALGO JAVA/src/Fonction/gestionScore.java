package Fonction;
import java.util.*;

/**
 * Classe pour gérer les scores des joueurs.
 */
public class gestionScore {

    // HashMap pour stocker les scores des joueurs
    public static HashMap<String, Integer> scoresGestion = new HashMap<>();

    /**
     * Met à jour les scores à la fin de chaque partie en fonction du résultat.
     *
     * @param joueur   Le nom du joueur.
     * @param aGagne   Indique si le joueur a gagné la partie.
     */
    public static void mettreAJourScores(String joueur, boolean aGagne) {
        // Incrémente ou décrémente le score du joueur en fonction du résultat
        if (aGagne) {
            scoresGestion.put(joueur, scoresGestion.getOrDefault(joueur, 0) + 5);
        } else {
            scoresGestion.put(joueur, scoresGestion.getOrDefault(joueur, 0) - 2);
        }
    }

    /**
     * Trie la liste des scores dans l'ordre décroissant.
     *
     * @param scores La liste des scores à trier.
     * @return La liste triée des scores dans l'ordre décroissant.
     */
    public static List<Map.Entry<String, Integer>> triInsertionScoreInverse(HashMap<String, Integer> scores) {
        List<Map.Entry<String, Integer>> listScores = new ArrayList<>(scores.entrySet());
        // On récupèere la taille du tableau
        int taille = listScores.size();

        // Boucle à travers chaque élément du tableau à partir du deuxième élément
        for (int i = 1; i < taille; i++) {
            Map.Entry<String, Integer> temp = listScores.get(i); // Stocke temporairement l'élément actuel
            int j = i - 1; // Initialise l'indice pour comparer avec les éléments précédents

            // Boucle tant que l'indice n'est pas hors du tableau et l'élément actuel est plus grand que l'élément précédent
            while (j >= 0 && listScores.get(j).getValue() > temp.getValue()) {
                listScores.set(j + 1, listScores.get(j)); // Décale l'élément précédent vers la droite
                j--; // Décrémente l'indice pour comparer avec le prochain
            }
            listScores.set(j + 1, temp); // Place l'élément actuel à la position correcte dans le sous-tableau trié
        }
        return listScores; // Retourne le tableau trié
    }

    /**
     * Trie la liste des scores dans l'ordre croissant.
     *
     * @param scores La liste des scores à trier.
     * @return La liste triée des scores dans l'ordre croissant.
     */
    public static List<Map.Entry<String, Integer>> triInsertionScore(HashMap<String, Integer> scores) {
        List<Map.Entry<String, Integer>> listScores = new ArrayList<>(scores.entrySet());
        // On récupèere la taille du tableau
        int taille = listScores.size();

        // Boucle à travers chaque élément du tableau à partir du deuxième élément
        for (int i = 1; i < taille; i++) {
            Map.Entry<String, Integer> temp = listScores.get(i); // Stocke temporairement l'élément actuel
            int j = i - 1; // Initialise l'indice pour comparer avec les éléments précédents

            // Boucle tant que l'indice n'est pas hors du tableau et l'élément actuel est plus petit que l'élément précédent
            while (j >= 0 && listScores.get(j).getValue() < temp.getValue()) {
                listScores.set(j + 1, listScores.get(j)); // Décale l'élément précédent vers la droite
                j--; // Décrémente l'indice pour comparer avec le prochain
            }
            listScores.set(j + 1, temp); // Place l'élément actuel à la position correcte dans le sous-tableau trié
        }
        return listScores; // Retourne le tableau trié
    }

    /**
     * Affiche les 10 derniers scores dans l'ordre spécifié.
     *
     * @param tri L'ordre de tri : 1 pour croissant, 2 pour décroissant, autre pour l'ordre d'ajout.
     */
    public static void afficherScores(int tri) {
        System.out.println("Scores :");

        // Obtient la liste triée des scores
        List<Map.Entry<String, Integer>> sortedScores;
        if (tri == 1) {
            sortedScores = triInsertionScore(scoresGestion);
        } else if (tri == 2) {
            sortedScores = triInsertionScoreInverse(scoresGestion);
        } else {
            sortedScores = new ArrayList<>(scoresGestion.entrySet());
        }

        // Détermine le nombre de scores à afficher (au plus 10)
        int nbScoresAafficher = Math.min(sortedScores.size(), 10);

        // Affiche les 10 derniers scores
        for (int i = sortedScores.size() - nbScoresAafficher; i < sortedScores.size(); i++) {
            Map.Entry<String, Integer> entry = sortedScores.get(i);
            System.out.println(entry.getKey() + " : " + entry.getValue() + " points");
        }

        // Propose des options pour l'utilisateur
        System.out.println("\n1 - Autre tri \n2 - Menu principal");
        Scanner choixEntree = new Scanner(System.in);
        int choix = choixEntree.nextInt();

        try {
            switch (choix) {
                case 1:
                    cli.scores();
                    break;
                case 2:
                    cli.menu();
                    break;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
        } catch (InputMismatchException e) {
            cli.gestionErreur("Rentrez un CHIFFRE entier en 1 et 5", 1000);
        }
    }
}
