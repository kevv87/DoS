/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories.dragons;

import javafx.scene.shape.Rectangle;

/**
 * Clase base de cada dragon.
 * @author kevv87
 */
public abstract class Dragon {
    protected String name;
    protected int velocidad_recarga;
    protected int edad;
    protected int resistencia;
    protected String tipo;
    protected Dragon padre;
    protected double x;
    protected double y;
    protected Rectangle Hitbox;
    protected String image_url;
    protected boolean alive;
    protected static int size =35;

    public static int getSize() {
        return size;
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

    public int getEdad() {
        return edad;
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

    public Rectangle getHitbox() {
        return Hitbox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVelocidad_recarga(int velocidad_recarga) {
        this.velocidad_recarga = velocidad_recarga;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public void setHitbox(Rectangle Hitbox) {
        this.Hitbox = Hitbox;
    }
    
    
}
