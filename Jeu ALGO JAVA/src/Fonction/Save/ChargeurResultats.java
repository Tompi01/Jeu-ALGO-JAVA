package Fonction.Save;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChargeurResultats {

    public static List<Resultat> chargerResultats(String nomFichier) {
        List<Resultat> resultats = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            resultats = (List<Resultat>) ois.readObject();
            System.out.println("Les résultats ont été chargés depuis " + nomFichier);
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de sauvegarde " + nomFichier + " n'a pas été trouvé.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des résultats depuis " + nomFichier + ": " + e.getMessage());
        }
        return resultats;
    }
}
