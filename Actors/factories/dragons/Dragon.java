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
    protected Random random = new Random();

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
