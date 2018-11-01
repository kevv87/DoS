package utils;

import Actors.factories.dragons.Dragon;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario {

    private NodoArbol raiz;
    private LinkedList<Dragon> listaPreorden = new LinkedList<>();


    /**
     * Constructor por default
     */
    public ArbolBinario(){
        this.raiz = null;
    }



    public void setPositions(NodoArbol node, int lvl, int posY, Dragon xPos){
        if(node!=null) {
            node.getDragon().setPosXfinal(xPos.getPosX() + (lvl * 76));
            node.getDragon().setPosYfinal(posY);
            setPositions(node.getHijoIzq(), lvl + 1, posY + (130 / (lvl + 1)), xPos);
            setPositions(node.getHijoDer(), lvl + 1, posY - (130 / (lvl + 1)), xPos);
        }
    }

    public void preorderToList(NodoArbol node)
    {
        if (node == null)
            return;

        listaPreorden.add(node.getDragon());

        if(node.getHijoIzq()!=null) {
            preorderToList(node.getHijoIzq());
        }

        if(node.getHijoDer()!=null) {
            preorderToList(node.getHijoDer());
        }
    }

    public NodoArbol iterativeSearch(Dragon x)
    {
        // Base Case
        if (raiz == null)
            return null;

        // Create an empty queue for level order traversal
        Queue<NodoArbol> q = new LinkedList<>();

        // Enqueue Root and initialize height
        q.add(raiz);

        // Queue based level order traversal
        while (q.isEmpty() == false)
        {
            // See if current node is same as x
            NodoArbol node = ((LinkedList<NodoArbol>) q).getFirst();
            if (node.getDragon() == x)
                return node;

            // Remove current node and enqueue its children
            ((LinkedList<NodoArbol>) q).pop();
            if (node.getHijoIzq() != null)
                q.add(node.getHijoIzq());
            if (node.getHijoDer() != null)
                q.add(node.getHijoDer());
        }

        return null;
    }

    /**function to insert element in binary tree https://www.geeksforgeeks.org/insertion-in-a-binary-tree-in-level-order/ */
    public void insert(NodoArbol temp, Dragon key)
    {
        if (raiz == null){
            raiz = new NodoArbol(key);
        }

        else {

            if (iterativeSearch(temp.getDragon()) != null){
                if(temp.getHijoIzq()== null){
                    temp.setHijoIzq(new NodoArbol(key));
                }

                else if(temp.getHijoDer()== null){
                    temp.setHijoDer(new NodoArbol(key));
                }

                else{
                    System.out.println("CANNOT INSERT MORE THAN TWO CHILD PER NODE");
                }
            }

            else{
                System.out.println("NODE not in this tree");
            }

        }


    }

    //SETTERS AND GETTERS

    public NodoArbol getRaiz() {
        return raiz;
    }


    public LinkedList<Dragon> getListaPreorden() {
        return listaPreorden;
    }

}
