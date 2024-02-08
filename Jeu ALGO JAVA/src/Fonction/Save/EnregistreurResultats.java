package Fonction.Save;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EnregistreurResultats {

    public static void enregistrerResultats(List<Resultat> resultats, String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(resultats);
            System.out.println("Les résultats ont été enregistrés dans " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement des résultats dans " + nomFichier + ": " + e.getMessage());
        }
    }
}
