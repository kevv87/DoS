/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;

import java.util.ArrayList;

import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *  Clase encargada de crear listas de datos genéricas
 * @author Sebastián
 */
@XmlRootElement
public class LinkedList<T>{ //Lista para nodos genericos

    private Nodo<T> Inicio;
    private int Tamanio;
    
    /**
     * Dummy Constructor
     */
    public LinkedList(){    
        Inicio=null;
        Tamanio=0;
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
     *
     * @return
     */
    public ArrayList recorrer(){
        ArrayList lista = new ArrayList();
        Nodo<T> act;
        act = this.Inicio;
        System.out.println(act);//Debug
        T data = act.getElemento();
        System.out.println(data);//Debug
        while((String)data!=null){
            System.out.println("Entre al ciclo, wao");//Debug
            lista.add(data);
            System.out.println(act);//Debug
            if (act!=null&&act.getSiguiente()!=null){
            act = act.getSiguiente();
            data = act.getElemento();
             }else{
                data = null;
            }
            
            System.out.println(data);//Debug
        }
        return lista;
    }

    //GETTERS
    public Nodo<T> getInicio() {
        return Inicio;
    }

    public int getTamanio() {
        return Tamanio;
    }


}
