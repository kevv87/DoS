/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories.dragons;

import javafx.geometry.Rectangle2D;

/**
 *
 * @author kevv87
 */
public class DragonC extends Dragon{
    
    /**
     * Constructor basico del dragon tipo C.
     * @param x Posicion en x en la pantalla
     * @param y Posicion en y en la pantalla
     * @param nombre Nombre
     * @param padre BolaFuego padre, null si no tiene
     */
    public DragonC(double x, double y, String nombre, Dragon padre){
        this.velocidad_recarga = 10;  // Podria estar en un rango de 10 a 43
        this.edad = 701; // Podria estar en un rango de 701 a 1000
        this.resistencia = 3;
        this.tipo = "C";
        this.x = x;
        this.y = y;
        width = 76;
        height = 76;
        dragonIV.setViewport(new Rectangle2D(0,0, width, height));
        getChildren().addAll(dragonIV);
    }   
    
    public void moveX(int x){
        boolean right = x>0;
        for(int i = 0; i < Math.abs(x); i++){
            if(right) this.setTranslateX(this.getTranslateX() +1);
            else this.setTranslateX(this.getTranslateX() -1);
        }
    }

    public void moveY(int y){
        boolean right = y>0;
        for(int i = 0; i < Math.abs(y); i++){
            if(right) this.setTranslateY(this.getTranslateY() +1);
            else this.setTranslateY(this.getTranslateY() -1);
        }
        
    }
    
}