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
    public DragonC(double x, double y, String nombre, Dragon padre, int edad){
        this.velocidad_recarga = 10;  // Podria estar en un rango de 10 a 43
        this.edad = 701; // Podria estar en un rango de 701 a 1000
        this.resistencia = 3;
        this.tipo = "C";
        this.x = x;
        this.y = y;
        dragonIV.setFitWidth(50);
        dragonIV.setFitHeight(50);
        getChildren().addAll(dragonIV);
    }

    public DragonC(double x, double y, String nombre, Dragon padre, int edad, int velocidad){
        this.velocidad_recarga = 10;  // Podria estar en un rango de 10 a 43
        this.edad = edad; // Podria estar en un rango de 701 a 1000
        this.resistencia = 3;
        this.padre = padre;
        this.velocidad_recarga = velocidad;
        this.tipo = "C";
        this.x = x;
        this.y = y;
        dragonIV.setFitWidth(50);
        dragonIV.setFitHeight(50);
        getChildren().addAll(dragonIV);
    }
}