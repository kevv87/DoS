package utils;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;

public class NodoArbol {

    private Dragon dradon;
    private NodoArbol padre;
    private NodoArbol hijoIzq;
    private NodoArbol hijoDer;


    /**
     * Constructor por default
     */
    public NodoArbol(){
        this.dradon = DragonFactory.getDragon("A",0,0,"A1",null);
        padre = null;
        hijoDer = null;
        hijoIzq = null;
    }

    /**
     * Constructor
     * @param dragon valor asignado
     */
    public NodoArbol(Dragon dragon){
        this.dradon = dragon;
        padre = null;
        hijoDer = null;
        hijoIzq = null;
    }

    //GETTERS AND SETTERS
    public Dragon getDradon() {
        return dradon;
    }

    public void setDradon(Dragon dradon) {
        this.dradon = dradon;
    }

    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    public NodoArbol getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(NodoArbol hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public NodoArbol getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(NodoArbol hijoDer) {
        this.hijoDer = hijoDer;
    }
}
