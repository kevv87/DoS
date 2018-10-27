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

    public void preorderPrint(NodoArbol node)
    {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.getDragon().id+ " ");

        /* then recur on left sutree */
        preorderPrint(node.getHijoIzq());

        /* now recur on right subtree */
        preorderPrint(node.getHijoDer());
    }

    public void setPositions(NodoArbol node, int lvl, int posY){
        if(node!=null){
            node.getDragon().setPosX(raiz.getDragon().getPosX()+(lvl*76));
            node.getDragon().setPosY(posY);
            setPositions(node.getHijoDer(), lvl+1, posY-(230/(lvl+1)));
            setPositions(node.getHijoIzq(), lvl+1, posY+(230/(lvl+1)));
        }
    }

    public void preorderToList(NodoArbol node, LinkedList <Dragon> dragonLinkedList)
    {
        if (node == null)
            return;

        /* first print data of node */
        dragonLinkedList.add(node.getDragon());

        /* then recur on left sutree */
        preorderToList(node.getHijoIzq(), dragonLinkedList);

        /* now recur on right subtree */
        preorderToList(node.getHijoDer(), dragonLinkedList);
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
