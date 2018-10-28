/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Movement.DragonHorde;
import Movement.Hero;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import utils.LinkedList;

/**
 *
 * @author Tomás
 */
public class InterfazJuego extends Application {
    private  HashMap<KeyCode, Boolean> keys = new HashMap<>();
    TextArea textarea;
    Label layoutactual;
    Label infodragon;
    Scene scene;
     @FXML
     AnchorPane foo;
    //Player
    Hero player = new Hero();
    //Enemies
    DragonHorde Enemies;
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        
        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaJuego.fxml"));
        Parent hola = inicio.load();
        this.foo = (AnchorPane)inicio.getNamespace().get("paneljuego");
        this.textarea = (TextArea)inicio.getNamespace().get("textarea");
        this.layoutactual = (Label)inicio.getNamespace().get("layout_actual");
        this.infodragon = (Label)inicio.getNamespace().get("info_dragon");
        this.scene = new Scene(hola);
        Media media = new Media(getClass().getClassLoader().getResource("utils/PantallaJuego.mp3").toString());
        MediaPlayer player = new MediaPlayer(media); 
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
          newGame();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
            }
        };
        timer.start();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        //Test---------------------------------------------
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
        //Test---------------------------------------------
        primaryStage.show();
        //Test---------------------------------------------
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
        LinkedList prueba3 = new LinkedList();
        prueba3.add("A1");
        prueba3.add("A2");
        prueba3.add("A3");
        prueba3.add("A6");
        prueba3.add("A7");
        prueba3.add("A8");
        prueba3.add("B1");
        mostrar_arbol(prueba3);
        //Test---------------------------------------------
    }
    public void mostrar_arbol(LinkedList lista){
        Btree TreeB = new Btree();
        TreeB.insertion(lista);
        this.textarea.setText("\n\n\n\n"+TreeB.print());
     
    }
    public void mostrar_layout(String a){
        this.layoutactual.setText(a);
    }
    
    public void mostrar_info(String b){
        this.infodragon.setText(b);
    }
    /**
     *
     * @throws IOException
     */
    public void newGame() throws IOException{
        player.setTranslateY(250);
        System.out.println(foo);
        foo.getChildren().add(player);
        Enemies = new DragonHorde(foo, 33, 33, 34, 3, 671);
        enemyMovement.start();
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
    }
//       _____________
    //______/Hero control
    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key, false);
    }

    public void movePlayer(){
        if ((isPressed(KeyCode.W) || isPressed(KeyCode.UP)) && player.getPosY()>-250){
            player.moveY(-2);
            player.setPosY(player.getPosY()-2);
        }
        if ((isPressed(KeyCode.S) || isPressed(KeyCode.DOWN)) && player.getPosY()<294){
            player.moveY(2);
            player.setPosY(player.getPosY()+2);
        }
        if ((isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT)) && player.getPosX()<1218){
            player.moveX(2);
            player.setPosX(player.getPosX()+2);
        }
        if ((isPressed(KeyCode.A) || isPressed(KeyCode.LEFT)) && player.getPosX()>0){
            player.moveX(-2);
            player.setPosX(player.getPosX()-2);
        }
    }

    Thread enemyMovement = new Thread(){
        @Override
        public void run() {
            while (Enemies.getPosX()>-76){
                try{
                    sleep(50);
                    Enemies.moveHorde();
                }
                catch (Exception E){

                }
            }
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
