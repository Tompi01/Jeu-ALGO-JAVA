package Fonction.Save;

import java.io.*;
import java.util.List;
import java.util.Map;

import static Fonction.gestionScore.scoresGestion;

public class EnregistreurResultats {

    // Enregistre les résultats dans un fichier
    public static void enregistrerResultats(List<Resultat> resultats, String nomFichier) {
        try (PrintWriter writer = new PrintWriter(new File(nomFichier))){
            for (Map.Entry<String, Integer> entry : scoresGestion.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            System.out.println("Scores sauvegarder dans le fichier "+ nomFichier);
        }
        catch (FileNotFoundException e){
            System.err.println("Erreur de sauvegarde du fichier :" + e.getMessage());
        }
    }
}