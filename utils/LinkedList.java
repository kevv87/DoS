/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;

/**import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;*/
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *  Clase encargada de crear listas de datos genéricas
 * @author Sebastián
 */
//@XmlRootElement
public class LinkedList<T>{ //Lista para nodos genericos

    private Nodo<T> Inicio;
    private Nodo<T> Ultimo;
    private int Tamanio;
    
    /**
     * Dummy Constructor
     */
    public LinkedList(){    
        Inicio=null;
        Ultimo=null;
        Tamanio=0;
    }
    
    /**
     * Anade un elemento al inicio de la lista
     * @param dato Objeto del tipo de dato establecido
     */
    public void anadirInicio(T dato){
        Nodo<T> nuevo = new Nodo<T>(dato);
        if(Inicio == null)
            this.Inicio = nuevo;
        else{
            nuevo.setSiguiente(Inicio);
            Inicio= nuevo;
        }
        Tamanio++;
    }
    
    /**
     * Anade un elemento al final de la lista
     * @param dato Objeto del tipo de dato establecido
     */
    public void add(T dato){
        Nodo<T> nuevo = new Nodo<T>(dato);
        if(Inicio==null){
            Inicio = nuevo;
        }else{
            Nodo<T> current = Inicio;
            while(current.getSiguiente()!=null){
                current=current.getSiguiente();
            }
            current.setSiguiente(nuevo);
        }
        Ultimo=nuevo;
        Tamanio++;
    }
    /**
     * Retorna true si un objeto pertenece a una lista
     * @param dato Objeto del tipo de dato establecido
     * @return True si dato se encuentra en la lista
     */             
    public boolean isIn(T dato){
        boolean dentro=false;
        if(Inicio==null){
            return dentro;
        } else{
            Nodo<T> current = new Nodo();
            current=Inicio;
            while(current!=null && dentro==false){
                if(current.getElemento()!=dato){
                    current=current.getSiguiente();
                } else{
                    dentro=true;
                }
            }
            return dentro;
        }
    }

    /**
     * Elimina un dato de una lista
     * @param dato Objeto del tipo de dato establecido
     */    
    public void eliminar(T dato){
        if(isIn(dato)){
            Nodo<T> aux = Inicio;
            if(aux.getElemento()==dato){
                Inicio=Inicio.getSiguiente();
            }
            else if(Ultimo.getElemento()==dato){
                while(aux.getSiguiente()!=Ultimo){
                    aux=aux.getSiguiente();
                }
                aux.setSiguiente(null);
            }
            else{
                while(aux.getSiguiente().getElemento()!=dato){
                    aux=aux.getSiguiente();
                }
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
            }
            Tamanio--;
        }
    }

    public void mostrar(){
        Nodo aux = Inicio;
        while(aux!=null){
            System.out.println(((DragonToSend)aux.getElemento()).getTipo());
            aux = aux.getSiguiente();
        }
    }
    

    //GETTERS
    public Nodo<T> getInicio() {
        return Inicio;
    }

    public Nodo<T> getUltimo() {
        return Ultimo;
    }

    public int getTamanio() {
        return Tamanio;
    }
    
    //SETTERS
    public void setInicio(Nodo<T> inicio) {
        this.Inicio = inicio;
    }

    public void setUltimo(Nodo<T> Ultimo) {
        this.Ultimo = Ultimo;
    }
    
    public void setTamanio(int tamanio) {
        this.Tamanio = tamanio;
    }    
    
    public void eliminar(){
        Inicio = null;
        Tamanio = 0;
    }



}
