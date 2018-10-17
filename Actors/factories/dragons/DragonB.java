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
public class DragonB extends Dragon{
    
    /**
     * Constructor basico del dragon tipo B.
     * @param x Posicion en x en la pantalla
     * @param y Posicion en y en la pantalla
     * @param nombre Nombre
     * @param padre BolaFuego padre, null si no tiene
     */
    public DragonB(double x, double y, String nombre, Dragon padre){
        this.velocidad_recarga = 44;  // Podria estar en un rango de 44 a 76
        this.edad = 201; // Podria estar en un rango de 201 a 700
        this.resistencia = 2;
        this.tipo = "B";
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