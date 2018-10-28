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
import javafx.scene.control.Label;
import utils.LinkedList;
/**
 *
 * @author Tomás
 */
public class PantallaLateral2 extends Application {
    TextArea textarea;
    Label layoutactual;
    Label infodragon;
    Scene scene;
    /**
     * @throws java.io.IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaLateral2.fxml"));
        Parent hola = inicio.load();
        this.textarea = (TextArea)inicio.getNamespace().get("textarea");
        this.layoutactual = (Label)inicio.getNamespace().get("layout_actual");
        this.infodragon = (Label)inicio.getNamespace().get("info_dragon");
        this.scene = new Scene(hola);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
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
        sleep(3000);
        LinkedList prueba1 = new LinkedList();
        prueba2.add("A1");
        prueba2.add("A2");
        prueba2.add("A3");
        prueba2.add("A6");
        prueba2.add("A7");
        prueba2.add("A8");
        prueba2.add("B1");
        mostrar_arbol(prueba1);
    }
  
    public void mostrar_arbol(LinkedList lista){
        Btree TreeB = new Btree();
        TreeB.insertion(lista);
        this.textarea.setText(TreeB.print());
     
    }
    public void mostrar_layout(String a){
        this.layoutactual.setText(a);
    }
    
    public void mostrar_info(String b){
        this.infodragon.setText(b);
    }
    public static void main(String[] args) {
      launch(args);
    }
    
}
