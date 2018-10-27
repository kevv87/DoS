/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

/**
 *
 * @author Tomás
 */
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A test for the {@link btree.Btree} implementation.
 * for CS 380 - Final Project: Implementation of B-Tree Data Structure
 * @since 12/01/2011
 * @author Abdulrhman Alkhodiry, Ahmed Alsalama
 */
public class TestBtree {

    public static void main(String[] args) {
        //Test----------------------------------------------------------------------------------------------------------------------------------------
        Scanner in = new Scanner(System.in);
        Btree bTree = new Btree();
        //bTree.conversion("A9");
        try{
             //...........................................................................................................................
            for(int i = 0; i<=9; i++){
                bTree.insert("A"+i);
                System.out.println("A"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("B"+i);
                System.out.println("B"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("C"+i);
                System.out.println("C"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("D"+i);
                System.out.println("D"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("E"+i);
                System.out.println("E"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("F"+i);
                System.out.println("F"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("G"+i);
                System.out.println("G"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("H"+i);
                System.out.println("H"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("I"+i);
                System.out.println("I"+i);
            }
            for(int i = 0; i<=9; i++){
                bTree.insert("J"+i);
                System.out.println("J"+i);
            }
                bTree.insert("K0");
                System.out.println("K0");
            //...........................................................................................................................
            
            
            while (true) {
                System.out.print("Welcome to the B Tree implementation\n"
                        + "1) to insert a name to the B Tree.\n"
                        + "2) to delete a name from the B Tree.\n"
                        + "3) to search the B Tree.\n"
                        + "4) to print the B Tree.\n"
                        + "5) to insert the B Tree on screen.\n"
                        + "Note: 0 (zero) will be assumed Null"
                        + "Enter any other number to exit.\n"
                        + "Choose:\n");
                int choose;
                String key;
                choose = in.nextInt();
                switch (choose) {
                    case 1:
                        System.out.print("Enter the number to insert in the B tree: ");
                        bTree.insert(in.next());
                        break;
                    case 2:
                        System.out.print("Enter the number to delete from the B tree: ");
                        bTree.delete(in.next());
                        break;
                    case 3:
                        System.out.print("Enter the number to search the B tree for: ");
                        key = in.next();
                        if (bTree.search(key)) {
                            System.out.println(key + " is founded");
                        } else {
                            System.out.println(key + " is NOT founded");

        }
                        break;
                    case 4:
                        System.out.println("-------\n"
                                + "Printing The B Tree\n");
                        bTree.print();
                        break;
                    default:
                        System.exit(0);
                        
                    case 5:
                        System.out.println(bTree.print());
                }
                System.out.println("-----------------------");
            }
        }catch (InputMismatchException e) {
            System.out.println("Accept only numbers... \n Exiting....");
        }
        //Test----------------------------------------------------------------------------------------------------------------------------------------
    }
}
