/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories.dragons;

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
     * @param padre Dragon padre, null si no tiene
     */
    public DragonA(double x, double y, String nombre, Dragon padre){
        this.velocidad_recarga = 77;  // Podria estar en un rango de 77 a 100
        this.edad = 1; // Podria estar en un rango de 1 a 200
        this.resistencia = 1;
        this.tipo = "A";
    }   
    
}
