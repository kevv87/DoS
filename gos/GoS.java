/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import logic.*;
import utils.ArbolBinario;

/**
 *
 * @author kevv87
 */
public class GoS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        int[] a = new int[10];
        System.out.println("valor 1: " + a[0] + " valor 2: " + a[9]);
        ArrayList lista = new ArrayList();
        lista.add(1);
        lista.add(2);
        System.out.println("posicion 2: ");
        if (-7.1>-14.0){
            System.out.println("true");
        }

        int[] array = new int[12];

        array[0] = 10;
        array[1] = 80;
        array[2] = 30;
        array[3] = 90;
        array[4] = 40;
        array[5] = 50;
        array[6] = 70;
        array[7] = 100;
        array[8] = 9;
        array[9] = 4;
        array[10] = 54;
        array[11] = 72;

        Sorter.insertionSortDescendente(array);
        System.out.println(array[0] + " " +
        array[1] + " " +
        array[2] + " " +
        array[3] + " " +
        array[4] + " " +
        array[5] + " " +
        array[6] + " " +
        array[7] + " " +
        array[8] + " " +
        array[9] + " " +
        array[10] + " " +
        array[11] );

        Dragon A = DragonFactory.getDragon("A",2,2,"SS",null);
        A.id = 3;
        Dragon B = DragonFactory.getDragon("B",2,2,"SS",null);
        B.id = 2;
        Dragon C = DragonFactory.getDragon("C",2,2,"SS",null);
        C.id = 1;
        Dragon D = DragonFactory.getDragon("A",2,2,"SS",null);
        D.id = 6;
        Dragon E = DragonFactory.getDragon("B",2,2,"SS",null);
        E.id = 5;
        Dragon F = DragonFactory.getDragon("A",2,2,"SS",null);
        F.id = 4;
        Dragon G = DragonFactory.getDragon("B",2,2,"SS",null);
        G.id = 9;
        Dragon H = DragonFactory.getDragon("C",2,2,"SS",null);
        H.id = 8;
        Dragon I = DragonFactory.getDragon("A",2,2,"SS",null);
        I.id = 7;

        B.setPadre(A);
        C.setPadre(A);
        D.setPadre(B);
        F.setPadre(B);
        G.setPadre(F);
        E.setPadre(C);
        H.setPadre(C);
        I.setPadre(E);

        LinkedList<Dragon> listaDragon = new LinkedList<>();
        listaDragon.add(A);
        listaDragon.add(B);
        listaDragon.add(C);
        listaDragon.add(D);
        listaDragon.add(E);
        listaDragon.add(F);
        listaDragon.add(G);
        listaDragon.add(H);
        listaDragon.add(I);

        /**
         System.out.println("Arboles: prueba insert");
         ArbolBinario arbolBinario = new ArbolBinario();
         arbolBinario.insert(null, A);
         arbolBinario.insert(arbolBinario.getRaiz(), B);
         arbolBinario.insert(arbolBinario.getRaiz(), C);
         arbolBinario.insert(arbolBinario.iterativeSearch(B), D);
         arbolBinario.insert(arbolBinario.iterativeSearch(C), E);
         arbolBinario.insert(arbolBinario.iterativeSearch(B), F);
         arbolBinario.insert(arbolBinario.iterativeSearch(F), G);
         arbolBinario.insert(arbolBinario.iterativeSearch(C), H);
         arbolBinario.insert(arbolBinario.iterativeSearch(E), I);
         arbolBinario.preorderPrint(arbolBinario.getRaiz());

         Queue<Dragon> cola = new LinkedList<>();
         cola.add(A);
         cola.add(B);
         ((LinkedList<Dragon>) cola).pop();
         System.out.println("Orden de elementos Add: " + ((LinkedList<Dragon>) cola).getFirst().id);

         */
        //System.out.println("implementacion de arbol \n");
        //Sorter.arbolBinario(listaDragon,listaDragon);

    }
}
