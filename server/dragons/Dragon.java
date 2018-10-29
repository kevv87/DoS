package dragons;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Clase base de cada dragon.
 * @author kevv87
 */
public class Dragon extends Pane{
    protected String name;
    protected int velocidad_recarga;
    protected int edad;
    protected int resistencia;
    protected String tipo;
    protected Dragon padre;
    protected double x;
    protected double y;
    protected String image_url;
    protected boolean alive;protected int width;
    protected int height;
    protected Image dragon = new Image(getClass().getResourceAsStream("Dragon.gif"));
    protected ImageView dragonIV = new ImageView(dragon);
    public int id;
    protected double movX;
    protected double movY;
    protected double posXfinal;
    protected double posYfinal;
    private boolean constantPosPerDragon = false;


    //GETTER


    public double getPosXfinal() {
        return posXfinal;
    }

    public double getPosYfinal() {
        return posYfinal;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    public int getVelocidad_recarga() {
        return velocidad_recarga;
    }


    public int getResistencia() {
        return resistencia;
    }

    public String getTipo() {
        return tipo;
    }

    public Dragon getPadre() {
        return padre;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMovX() {
        return movX;
    }

    public double getMovY() {
        return movY;
    }

    public boolean isConstantPosPerDragon() { return constantPosPerDragon; }


    //SETTER

    public void setName(String name) {
        this.name = name;
    }

    public void setVelocidad_recarga(int velocidad_recarga) {
        this.velocidad_recarga = velocidad_recarga;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPadre(Dragon padre) {
        this.padre = padre;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPosX(){
        return x;
    }
    
    public double getPosY(){
        return y;
    }
    
    public void setPosX(double x){
        this.x = x;
    }
    
    public void setPosY(double y){
        this.y = y;
    }

    public int getEdad() {return edad;}

    public void setEdad(int edad){ this.edad = edad; }

    public void setMovX(double movX) {
        this.movX = movX;
    }

    public void setMovY(double movY) {
        this.movY = movY;
    }

    public void setPosXfinal(double posXfinal) {
        this.posXfinal = posXfinal;
    }

    public void setPosYfinal(double posYfinal) {
        this.posYfinal = posYfinal;
    }

    public void setConstantPosPerDragon(boolean constantPosPerDragon) { this.constantPosPerDragon = constantPosPerDragon; }

    public Dragon copy(Dragon dragon){
        Dragon dragon1 = DragonFactory.getDragon("A",0, 0, "probe", null,1);
        dragon1.edad = dragon.edad;
        dragon1.name = dragon.name;
        dragon1.velocidad_recarga = dragon.velocidad_recarga;
        dragon1.resistencia = dragon.resistencia;
        dragon1.tipo = dragon.tipo;
        dragon1.padre = dragon.padre;
        dragon1.x = dragon.x;
        dragon1.y = dragon.y;
        dragon1.width = dragon.width;
        dragon1.height = dragon.height;


        return dragon1;

    };

}
