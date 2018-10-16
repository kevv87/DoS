/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors.factories;

import Actors.factories.dragons.DragonB;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonA;
import Actors.factories.dragons.DragonC;

/**
 * Clase encargada de generar los diferentes tipos de dragones, a peticion.
 * Implementa el patron de disenno "Factory"
 * @author kevv87
 */
public class DragonFactory {
    
    /**
     * Metodo encargado de generar dragones
     * @param type Tipo de dragon a generar, A, B o C
     * @param x Posicion del dragon en x en la pantalla
     * @param y Posicion del dragon en y en la patanlla
     * @param nombre Nombre del dragon a generar
     * @param padre Dragon padre del dragon a generar
     * @return Dragon generado
     */
    public static Dragon getDragon(String type, double x, double y, String nombre, Dragon padre){
        switch(type){
            case "A":
                return new DragonA(x,y,nombre,padre);
            case "B":
                return new DragonB(x,y,nombre,padre);
            case "C":
                return new DragonC(x,y,nombre,padre);
            default:
                return null;
        }
    }
    
}
