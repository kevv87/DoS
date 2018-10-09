package Objetos;

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

    /**
     * Nombre generado aleatoriamente.
     * ● Velocidad de recarga de fuego (de 1 a 100)
     * ● Edad (de 1 a 1000 años). No hay dos dragones con la misma edad por oleada.
     * ● Resistencia (de 1 a 3): indica que tantos dispararon soportan antes de morir.
     * ● Clase: comandante (solo 1 por oleada), capitanes (tienen grupos de dragones y capitanes
     * bajo su control) e infantería.
     * ● Padre. Cada dragón tiene un padre (menos el primer dragon). Esto se asigna aleatoriamente
     * a la hora de crearlo. Un dragón no puede ser el padre de más de dos dragones.
     */
}
