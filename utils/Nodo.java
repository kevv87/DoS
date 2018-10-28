/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Actors.factories.dragons.DragonToSend;

/**import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;*/

/**
 * Clase encargada de la creación de un nodo genérico
 * @author Sebastián
 * @param <T> Tipo de dato
 */
//@XmlRootElement
//@XmlSeeAlso(DragonToSend.class)
public class Nodo<T> {  //Nodo generico para usar con distintos elementos
    //@XmlElement
    private T Elemento;
    //@XmlElement
    private Nodo Siguiente;
    
    /**
     * Constructor
     * @param obj Objeto del tipo de dato establecido
     */    
    public Nodo(T obj){
        Elemento=obj;
        Siguiente=null;
    }

    /**
    * Dummy constuctor
    */
    Nodo() {
        Elemento=null;
        Siguiente=null;
    }
    
    //  GETTERS
    public T getElemento() {
        return Elemento;
    }
    
    public Nodo getSiguiente() {
        return Siguiente;
    }
    
    //  SETTERS
    public void setElemento(T elemento) {
        this.Elemento = elemento;
    }

    public void setSiguiente(Nodo siguiente) {
        this.Siguiente = siguiente;
    }
    
    
}
