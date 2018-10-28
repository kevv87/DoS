/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.IOException;
import static java.lang.Thread.sleep;
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
import utils.LinkedList;
/**
 *
 * @author Tomás
 */
public class PantallaLateral2 extends Application {
    TextArea textarea;
    Scene scene;
    /**
     * @throws java.io.IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaLateral2.fxml"));
        Parent hola = inicio.load();
        this.textarea = (TextArea)inicio.getNamespace().get("textarea");
        this.scene = new Scene(hola);
       

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        LinkedList prueba1 = new LinkedList();
        prueba1.add("A1");
        prueba1.add("A2");
        prueba1.add("A3");
        prueba1.add("A4");
        prueba1.add("A5");
        prueba1.add("A6");
        prueba1.add("A7");
        prueba1.add("A8");
        prueba1.add("A9");
        prueba1.add("B1");
        mostrar_arbol(prueba1);
        primaryStage.show();
        sleep(10000);
        System.out.println("----------------------------------------");
        LinkedList prueba2 = new LinkedList();
        prueba2.add("A1");
        prueba2.add("A2");
        prueba2.add("A3");
        prueba2.add("A5");
        prueba2.add("A6");
        prueba2.add("A7");
        prueba2.add("A8");
        prueba2.add("A9");
        prueba2.add("B1");
        mostrar_arbol(prueba2);
    }
  
    public void mostrar_arbol(LinkedList lista){
        Btree TreeB = new Btree();
        TreeB.insertion(lista);
        this.textarea.setText(TreeB.print());
     
    }
    public static void main(String[] args) {
      launch(args);
    }
    
}
