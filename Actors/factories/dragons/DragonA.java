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
public class DragonA extends Dragon{
    
    /**
     * Constructor basico del dragon tipo A.
     * @param x Posicion en x en la pantalla
     * @param y Posicion en y en la pantalla
     * @param nombre Nombre
     * @param padre BolaFuego padre, null si no tiene
     */
    public DragonA(double x, double y, String nombre, Dragon padre) {
        this.velocidad_recarga = 	(int)(Math.random() * ((20 - 3) + 1)) + 3;  // Podria estar en un rango de 1 a 10
        this.elapsedT = this.velocidad_recarga;
        this.edad = 1; // Podria estar en un rango de 1 a 1000
        this.resistencia = 1;
        this.tipo = "A";

        this.x = x;
        this.y = y;
        dragonIV.setFitWidth(50);
        dragonIV.setFitHeight(50);
        ;
        getChildren().addAll(dragonIV);

    }
}
