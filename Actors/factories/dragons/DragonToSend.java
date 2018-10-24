package Actors.factories.dragons;



//import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase destinada a ser incluida en un XML y ser enviada por protoclo HTTP. Nace de la incapacidad del XML Parser para
 * manejar objetos tipo Pane de JavaFx.
 * @author Kevin Zeledon
 * */
//@XmlRootElement
public class DragonToSend {
    protected String name;
    protected int velocidad_recarga;
    protected int edad;
    protected int resistencia;
    protected String tipo;
    protected DragonToSend padre;
    protected double x;
    protected double y;
    protected String image_url;
    protected boolean alive;protected int width;
    protected int height;

    /**
     * Constructor
     * */
    public DragonToSend(String name, int velocidad_recarga, int edad, int resistencia, String tipo, DragonToSend padre, double x, double y, String image_url, boolean alive) {
        this.name = name;
        this.velocidad_recarga = velocidad_recarga;
        this.edad = edad;
        this.resistencia = resistencia;
        this.tipo = tipo;
        this.padre = padre;
        this.x = x;
        this.y = y;
        this.image_url = image_url;
        this.alive = alive;

    }

    public DragonToSend(){

    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVelocidad_recarga() {
        return velocidad_recarga;
    }

    public void setVelocidad_recarga(int velocidad_recarga) {
        this.velocidad_recarga = velocidad_recarga;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DragonToSend getPadre() {
        return padre;
    }

    public void setPadre(DragonToSend padre) {
        this.padre = padre;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
