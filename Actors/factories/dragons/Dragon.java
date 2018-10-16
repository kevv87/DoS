/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories.dragons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
    protected int width;
    protected int height;
    protected Image dragon = new Image(getClass().getResourceAsStream("Dragon.gif"));
    protected ImageView dragonIV = new ImageView(dragon);
    
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
}
