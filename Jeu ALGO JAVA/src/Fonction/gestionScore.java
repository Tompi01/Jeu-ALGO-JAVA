package Fonction;
import java.util.HashMap;
import java.util.Map;

public class gestionScore {
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
        System.out.println("3 - Sauvegarder");
        System.out.println("4 - Revenir au menu principal");
    }
}
