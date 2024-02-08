package Fonction.Save;
import Fonction.Jeu;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChargeurResultats {
    public static List<Resultat> chargerScores(String file) throws IOException {
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader buffer = new BufferedReader(isr);

        String line;
        while ((line = buffer.readLine()) != null){
            String[] tempScore = line.split(",");
            String pseudo = tempScore[0];
            int score = Integer.parseInt(tempScore[1]);
            Jeu.scoresAPartirSauvegarde(pseudo, score);
        }
        return null;
    }

}


