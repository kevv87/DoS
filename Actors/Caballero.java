package Actors;

public class Caballero {

    private double x;
    private double y;
    private int resistencia;

    /**
     * Constructor
     * @param x Posicion en X
     * @param y Posicion en Y
     */
    public Caballero(double x, double y){
        this.x = x;
        this.y = y;
        this.resistencia = 3;
    }

    public Caballero(){
        this.x = 0;
        this.y = 0;
        this.resistencia = 3;

    }
    //SETTERS AND GETTERS

    public double isX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double isY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    
}
