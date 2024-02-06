package Fonction;

public class Joueur {
    private int positionX;
    private int positionY;
    private int id;

    // Constructeur de la classe Joueur
    public Joueur(int positionX, int positionY, int id) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.id = id;
    }

    // Getter de la position X du joueur
    public int getPositionX() {
        return positionX;
    }

    // Getter de la position Y du joueur
    public int getPositionY() {
        return positionY;
    }

    // Setter des positions X et Y du joueur
    public void setPosition(int newX, int newY) {
        this.positionX = newX;
        this.positionY = newY;
    }

    // Getter de l'id du joueur
    public int getId() {
        return id;
    }



}
