/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories.dragons;

import Actors.factories.DragonFactory;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

import javafx.scene.shape.Rectangle;

//import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase base de cada dragon.
 * @author kevv87
 */
public abstract class Dragon extends Pane{
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

    public Dragon copy(Dragon dragon){
        Dragon dragon1 = DragonFactory.getDragon("A",0, 0, "probe", null);
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
