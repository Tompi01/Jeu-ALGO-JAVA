package Fonction;
import Fonction.*;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class deplacement{

    /*
    fonction de déplacement du joueur
    * Joueur qui joue : ID | des position X : Y | Condition : Si la case est != 0 | passe le tour au joueur suivant |
    * scanner string = Z : monte - 1 colonne | S + 1 colonne | Q = - 1 ligne | D = + 1 ligne ou bah tu remet ton
    * input chacal.
    * */

    public static Joueur deplacementDuJoueur(Joueur joueur, int[][] matrice){
        Scanner scanner = new Scanner(System.in);
        try {
            int positionLJoueur=joueur.getPositionX(); // stock x et y en ligne et colonne
            int positionCJoueur = joueur.getPositionY();
            System.out.println("Rentrez une lettre entre : Z - Haut | S - Bas | Q - Gauche | D - Droit");
            System.out.println("Le joueur " + (joueur.getId()-1) + " commence !");
            String response = scanner.nextLine().toUpperCase(); // supposer mettre la réponse du joueur en majuscule
            switch (response) {
                case "Z": // déplacement du joueur en haut
                    matrice[positionLJoueur][positionCJoueur-1]=joueur.getId(); // regarde dans la matrice la position du joueur actuel
                    matrice[positionLJoueur][positionCJoueur]=0;//ancienne position ou on réinitialise la case à son états de base (jouable)
                    joueur.setPosition(positionLJoueur, positionCJoueur-1);//update la position dans la matrice du joueur
                    Matrice.affichageMatrice(matrice);
                    break;

                case "Q":// déplacement du joueur à gauche
                    matrice[positionLJoueur-1][positionCJoueur]=joueur.getId(); //regarde dans la matrice la position du joueur actuel
                    matrice[positionLJoueur][positionCJoueur]=0; //ancienne position ou on réinitialise la case à son états de base (jouable)
                    joueur.setPosition(positionLJoueur-1, positionCJoueur); //update la position dans la matrice du joueur
                    Matrice.affichageMatrice(matrice);
                    break;

                case "S":// déplacement du joueur en bas
                    matrice[positionLJoueur][positionCJoueur+1]=joueur.getId();
                    matrice[positionLJoueur][positionCJoueur]=0;
                    joueur.setPosition(positionLJoueur, positionCJoueur+1);
                    Matrice.affichageMatrice(matrice);
                    break;

                case "D":// déplacement du joueur à droite
                    matrice[positionLJoueur+1][positionCJoueur]=joueur.getId();
                    matrice[positionLJoueur][positionCJoueur]=0;
                    joueur.setPosition(positionLJoueur+1, positionCJoueur);
                    Matrice.affichageMatrice(matrice);
                    break;

                default:
                    System.out.println("Rentrez une lettre entre Z - Q - S - D");
                    deplacementDuJoueur(joueur, matrice);
            }

            return joueur;
        }catch (InputMismatchException e){
            // Gère l'exception si l'utilisateur entre une valeur non entière
            System.out.println("Rentrez une lettre entre Z - Q - S - D");
            deplacementDuJoueur(joueur, matrice);
            try {
                Thread.sleep(1000); // Attendre pendant 1 seconde
            } catch (InterruptedException r) {
                throw new RuntimeException(r);
            }
            deplacementDuJoueur(joueur, matrice);
        };

        return joueur;
    }
}


