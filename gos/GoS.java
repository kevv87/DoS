/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gos;

import java.util.ArrayList;
import logic.*;
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

        Sorter.selectionSortDescendiente(array);
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

    }
    
}
