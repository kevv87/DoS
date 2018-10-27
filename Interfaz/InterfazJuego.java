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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Tomás
 */
public class InterfazJuego extends Application {
    private  HashMap<KeyCode, Boolean> keys = new HashMap<>();
    Scene scene;
     @FXML
     AnchorPane foo;
    //Player
    Hero player = new Hero();
    //Enemies
    DragonHorde Enemies;
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaJuego.fxml"));
        Parent hola = inicio.load();
        this.foo = (AnchorPane)inicio.getNamespace().get("paneljuego");
        this.scene = new Scene(hola);
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
        primaryStage.show();
    }
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
