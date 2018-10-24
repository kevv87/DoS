/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Actors.factories.dragons.Dragon;
import Movement.DragonHorde;
import Movement.Fire;
import Movement.FireManager;
import Movement.Hero;
import java.io.IOException;


import static java.lang.Thread.sleep;
import java.util.HashMap;

import conectividad.Cliente;
import com.sun.security.ntlm.NTLMException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
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
import utils.LinkedList;

import javax.xml.bind.JAXBException;

/**
 *
 * @author Tomas
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
    private static FireManager fireManager;
    private Cliente client = new Cliente();
    double width;
    double height;

    public InterfazJuego() throws NTLMException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        client.sendMessage("Start");
        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaJuego.fxml"));
        Parent padre = inicio.load();
        this.foo = (AnchorPane)inicio.getNamespace().get("paneljuego");
        this.scene = new Scene(padre);
        newGame();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    movePlayer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        FireManager.moveFire();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newGame() throws IOException, JAXBException {
        player.setTranslateY(250);
        foo.getChildren().add(player);
        Enemies = new DragonHorde(foo, client.getDragons());
        enemyMovement.start(); //THREAD
        fireManager = new FireManager(foo, width, Enemies);
        fireMovement.start(); // THREAD
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

    public void movePlayer() throws InterruptedException {
        if ((isPressed(KeyCode.W) || isPressed(KeyCode.UP)) && player.getPosY()>-250-50){
            player.moveY(-2);
            player.setPosY(player.getPosY()-2);
        }
        if ((isPressed(KeyCode.S) || isPressed(KeyCode.DOWN)) && player.getPosY()<294){
            player.moveY(2);
            player.setPosY(player.getPosY()+2);
        }

        if ((isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT)) && player.getPosX()<973-player.getWidth()){
            player.moveX(2);
            player.setPosX(player.getPosX()+2);
        }
        if ((isPressed(KeyCode.A) || isPressed(KeyCode.LEFT)) && player.getPosX()>-75){
            player.moveX(-2);
            player.setPosX(player.getPosX()-2);
        }
        if(isPressed(KeyCode.F)){

            Fire fire = new Fire(player.getPosX()+300, player.getPosY()+250);
            addToPane(fire);
            fireManager.getFriendlyFireList().add(fire);
            Thread.sleep(60);


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

    Thread fireMovement = new Thread(){
        @Override


        public void run() {

            while (true){
                try{
                    sleep(10);
                    FireManager.moveFire();
                }
                catch (Exception E){

                }
            }
        }
    };

    public void addToPane(Fire toad){

        Platform.runLater(new Runnable(){

            @Override public void run(){

                foo.getChildren().addAll(toad);
            }

        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
