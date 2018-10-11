/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories.dragons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
     * @param padre Dragon padre, null si no tiene
     */
    public DragonB(double x, double y, String nombre, Dragon padre){
        this.velocidad_recarga = 44;  // Podria estar en un rango de 44 a 76
        this.edad = 201; // Podria estar en un rango de 201 a 700
        this.resistencia = 2;
        this.tipo = "B";
        alive = true;
        this.Hitbox = new Rectangle(x,y,size,size);
        this.Hitbox.setFill(Color.GREEN);
    }   
    
}