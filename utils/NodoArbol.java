package utils;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;

public class NodoArbol {

    private Dragon dragon;
    private NodoArbol padre;
    private NodoArbol hijoIzq;
    private NodoArbol hijoDer;


    /**
     * Constructor por default
     */
    public NodoArbol(){
        this.dragon = DragonFactory.getDragon("A",0,0,"A1",null);
        padre = null;
        hijoDer = null;
        hijoIzq = null;
    }

    /**
     * Constructor
     * @param dragon valor asignado
     */
    public NodoArbol(Dragon dragon){
        this.dragon = dragon;
        padre = null;
        hijoDer = null;
        hijoIzq = null;
    }

    //GETTERS AND SETTERS
    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
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
