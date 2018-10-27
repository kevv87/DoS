/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.IOException;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
/**
 *
 * @author Tomás
 */
public class PantallaLateral2 extends Application {
    TextArea textarea;
    Scene scene;
    Btree TreeB;
    /**
     * @throws java.io.IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.TreeB = new Btree(); 
        test(this.TreeB);
        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("FXML.fxml"));
        Parent hola = inicio.load();
        this.textarea = (TextArea)inicio.getNamespace().get("textarea");
        this.scene = new Scene(hola);
        textarea.setText(TreeB.print());
        textarea.setRotate(90.0);
        System.out.println("hola wapo "+TreeB.print());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        test_tr();        
    }
    public void del_act(String a){
        this.TreeB.delete(a);
        textarea.setText(TreeB.print());
    }
    
    public void test(Btree b){
        for(int i = 0; i<=9; i++){
                TreeB.insert("A"+i);
                System.out.println("A"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("B"+i);
                System.out.println("B"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("C"+i);
                System.out.println("C"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("D"+i);
                System.out.println("D"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("E"+i);
                System.out.println("E"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("F"+i);
                System.out.println("F"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("G"+i);
                System.out.println("G"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("H"+i);
                System.out.println("H"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("I"+i);
                System.out.println("I"+i);
            }
            for(int i = 0; i<=9; i++){
                TreeB.insert("J"+i);
                System.out.println("J"+i);
            }
                TreeB.insert("K0");
        //---------------------------------------------------
       
    }
    public void test_tr(){
       Scanner in = new Scanner(System.in);  
       System.out.println("Inserte el nombre del dragon que desea eliminar: \n");
       del_act(in.next());
    }
    public static void main(String[] args) {
      launch(args);
    }
    
}
