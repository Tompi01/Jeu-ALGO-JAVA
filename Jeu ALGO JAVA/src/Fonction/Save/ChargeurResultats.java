package Fonction.Save;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChargeurResultats {

    // Charge les résultats à partir d'un fichier
    public static List<Resultat> chargerResultats(String nomFichier) {
        List<Resultat> resultats = new ArrayList<>(); // Crée une nouvelle liste pour stocker les résultats
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            // Lit les résultats à partir du fichier et les convertit en une liste d'objets Resultat
            resultats = (List<Resultat>) ois.readObject();
            System.out.println("Les résultats ont été chargés depuis " + nomFichier);
        } catch (FileNotFoundException e) {
            // Gère l'exception si le fichier n'est pas trouvé
            System.out.println("Le fichier de sauvegarde " + nomFichier + " n'a pas été trouvé.");
        } catch (IOException | ClassNotFoundException e) {
            // Gère l'exception si une erreur se produit lors de la lecture des résultats
            System.out.println("Erreur lors du chargement des résultats depuis " + nomFichier + ": " + e.getMessage());
        }
        return resultats; // Retourne la liste des résultats chargés depuis le fichier
    }

}


