package logic;

public class BolaFuego extends Sprite{

    //Atributos
    private double x;
    private double y;
    private int velocidadRecarga;
    private int edad;
    private int resistencia;
    private String nombre;
    private String clase;
    private BolaFuego padre;
    private BolaFuego hijo1;
    private BolaFuego hijo2;

    /**
     * Constructor por defecto
     */
    public BolaFuego(){
        x = 0;
        y = 0;
        velocidadRecarga = 1;
        edad = 1;
        resistencia = 3;
        nombre = "BolaFuego";
        clase = "infateria";
        padre = null;
        hijo1 = null;
        hijo2 = null;
    }

    //GETTERS

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getVelocidadRecarga() {
        return velocidadRecarga;
    }

    public int getEdad() {
        return edad;
    }

    public int getResistencia() {
        return resistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClase() {
        return clase;
    }

    public BolaFuego getPadre() {
        return padre;
    }

    public BolaFuego getHijo1() {
        return hijo1;
    }

    public BolaFuego getHijo2() {
        return hijo2;
    }

    //SETTERS

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelocidadRecarga(int velocidadRecarga) {
        this.velocidadRecarga = velocidadRecarga;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setPadre(BolaFuego padre) {
        this.padre = padre;
    }

    public void setHijo1(BolaFuego hijo1) {
        this.hijo1 = hijo1;
    }

    public void setHijo2(BolaFuego hijo2) {
        this.hijo2 = hijo2;
    }

    @Override
    public void update() {

    }
}
