package Fonction.Save;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EnregistreurResultats {

    // Enregistre les résultats dans un fichier
    public static void enregistrerResultats(List<Resultat> resultats, String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            // Écrit les résultats dans le fichier en utilisant un objet
            oos.writeObject(resultats);
            System.out.println("Les résultats ont été enregistrés dans " + nomFichier);
        } catch (IOException e) {
            // Gère l'exception si une erreur se produit lors de l'écriture des résultats
            System.out.println("Erreur lors de l'enregistrement des résultats dans " + nomFichier + ": " + e.getMessage());
        }
    }
}
