/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Actors.factories.dragons.Dragon;
import Movement.*;

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
import java.util.Timer;
import java.util.TimerTask;

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
    private ScrollingBG map = new ScrollingBG();
    //Disparos
    private boolean fEnabled = true;
    private Timer playerT = new Timer();
    private TimerTask enableFire = new TimerTask() {
        @Override
        public void run() {
            fEnabled=true;
        }
    };

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
                    Enemies.moveHorde();
                    fireManager.moveFire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newGame() throws IOException, JAXBException {
        foo.getChildren().add(map.getBG1());
        foo.getChildren().add(map.getBG2());
        foo.getChildren().add(map.getBG3());
        player.setTranslateY(250);
        foo.getChildren().add(player);
        Enemies = new DragonHorde(foo, 100,100,100, 3,671);
        fireManager = new FireManager(foo, width, Enemies);
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
        if ((isPressed(KeyCode.W) || isPressed(KeyCode.UP)) && player.getPosY()>-300){
            player.moveY(-2);
            player.setPosY(player.getPosY()-2);
        }
        if ((isPressed(KeyCode.S) || isPressed(KeyCode.DOWN)) && player.getPosY()<260){
            player.moveY(2);
            player.setPosY(player.getPosY()+2);
        }

        if ((isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT))){
            map.move(1);
            if(player.getPosX()<830){
                player.moveX(2);
                player.setPosX(player.getPosX()+2);
            }
        }
        if ((isPressed(KeyCode.A) || isPressed(KeyCode.LEFT))){
            map.move(-1);
            if(player.getPosX()>-20){
                player.moveX(-2);
                player.setPosX(player.getPosX()-2);
            }
        }
        if(isPressed(KeyCode.F) && fEnabled){
            fEnabled=false;
            Fire fire = new Fire(player.getPosX()+136, player.getPosY()+340);
            addToPane(fire);
            fireManager.getFriendlyFireList().add(fire);
            playerT.scheduleAtFixedRate(enableFire, 0, 300);
        }
    }

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
