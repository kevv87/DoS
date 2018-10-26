package utils;

import Actors.factories.dragons.Dragon;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario {

    private NodoArbol raiz;
    private int nElementos = 0;
    private NodoArbol temp = raiz;

    /**
     * Constructor por default
     */
    public ArbolBinario(){
        this.raiz = null;
        this.nElementos = 0;
        this.temp = raiz;

    }

    /**
     * Constructor
     * @param raiz
     */
    public ArbolBinario(NodoArbol raiz){
        this.raiz = raiz;
        this.nElementos = 0;
        this.temp = raiz;
    }

    public void inorder(NodoArbol temp)
    {
        if (temp == null)
            return;

        inorder(temp.getHijoIzq());
        System.out.print(temp.getDradon().getEdad()+" ");
        inorder(temp.getHijoDer());
    }

    /**function to insert element in binary tree https://www.geeksforgeeks.org/insertion-in-a-binary-tree-in-level-order/ */
    public void insert(NodoArbol temp, Dragon key)
    {
        Queue<NodoArbol> q = new LinkedList<NodoArbol>();
        q.add(temp);

        // Do level order traversal until we find
        // an empty place.
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.getHijoIzq() == null) {
                temp.setHijoIzq(new NodoArbol(key));
                break;
            } else
                q.add(temp.getHijoIzq());

            if (temp.getHijoDer() == null) {
                temp.setHijoDer(new NodoArbol(key));
                break;
            } else
                q.add(temp.getHijoDer());
        }
    }
    /**
    public void vaciar() {
        raiz = null;
    }

    public void add(Integer value) {
        if (value < this.value) {
            if (left != null) {
                left.add(value);
            } else {
                left = new Node(value);
            }
        } else {
            if (right != null) {
                right.add(value);
            } else {
                right = new Node(value);
            }
        }
    }*/

    //SETTERS AND GETTERS

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }




}
