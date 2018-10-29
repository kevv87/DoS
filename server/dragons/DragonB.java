/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dragons;


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
        dragonIV.setFitWidth(50);
        dragonIV.setFitHeight(50);;
        getChildren().addAll(dragonIV);
    }

    public DragonB(double x, double y, String nombre, Dragon padre, int edad, int velocidad){
        this.velocidad_recarga = 44;  // Podria estar en un rango de 44 a 76
        this.edad = edad; // Podria estar en un rango de 201 a 700
        this.resistencia = 2;
        this.tipo = "B";
        this.velocidad_recarga = velocidad;
        this.x = x;
        this.y = y;
        dragonIV.setFitWidth(50);
        dragonIV.setFitHeight(50);;
        getChildren().addAll(dragonIV);
    }
}