package dragons;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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
     * @param padre BolaFuego padre del dragon a generar
     * @return BolaFuego generado
     */
    public static Dragon getDragon(String type, double x, double y, String nombre, Dragon padre, int edad){
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

    public static Dragon getDragon(String type, double x, double y, String nombre, Dragon padre, int edad, int velocidad){
        switch(type){
            case "A":
                return new DragonA(x,y,nombre,padre, edad, velocidad);
            case "B":
                return new DragonB(x,y,nombre,padre, edad, velocidad);
            case "C":
                return new DragonC(x,y,nombre,padre, edad, velocidad);
            default:
                return null;
        }
    }

    /**
     * Crea una instancia de un dragon para enviar a partir de un cierto dragon especificado
     * @param dragon Dragon a convertir en una instancia lista para el XML Parser
     * @return Dragon con las mismas caracteristicas que el especificado listo para el XML Parser
     * */
    public static DragonToSend getDragon(Dragon dragon){
        if(dragon==null){
            return null;
        }
        return new DragonToSend(dragon.getName(),dragon.getVelocidad_recarga(),dragon.getEdad(),dragon.getResistencia(),dragon.getTipo(), getDragon(dragon.getPadre()),dragon.getPosX(),dragon.getPosY(),dragon.getImage_url(),dragon.isAlive());
    }


    public static Dragon getDragon(DragonToSend dragon){
        if(dragon == null){
            return null;
        }
        return getDragon(dragon.getTipo(), dragon.getX(), dragon.getY(), dragon.getName(), getDragon(dragon.getPadre()), dragon.getEdad(), dragon.getVelocidad_recarga());
    }

}
