/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Actors.factories.dragons.Dragon;
import Arduino.Connection;
import Movement.*;

import java.io.IOException;


import static java.lang.Thread.sleep;


import java.io.UnsupportedEncodingException;

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

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.scene.control.Button;
import javafx.scene.control.Control;
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


import Logger.Logging;
import javafx.stage.WindowEvent;


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
    private static String controlCommand = "n";


    private Cliente cliente = new Cliente();

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

    private boolean pause_b = false;
    private static int ordenamiento = 0;


    public InterfazJuego() throws NTLMException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Conexion con arduino
        Connection main = new Connection();
        main.initialize();



        primaryStage.setOnCloseRequest((WindowEvent event1) -> {
            try {
                finish();
            } catch (JAXBException | UnsupportedEncodingException | NTLMException e) {
                e.printStackTrace();
            }
        });


        client.sendMessage("Start");
        Logging.log("info","Iniciando juego");

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

                    if(!pause_b){
                        Enemies.moveHorde();
                        fireManager.moveFire();
                    }

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

        Enemies = new DragonHorde(foo, cliente.getDragons());

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
        String command = InterfazJuego.getControlCommand();

        if ((isPressed(KeyCode.W) || isPressed(KeyCode.UP)  && player.getPosY()>-300 && !pause_b)){
            player.moveY(-2);
            player.setPosY(player.getPosY()-2);
        }
        if ((isPressed(KeyCode.S) || isPressed(KeyCode.DOWN) && player.getPosY()<260 && !pause_b)){

            player.moveY(2);
            player.setPosY(player.getPosY()+2);
        }


        if ((isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT) && !pause_b)){

            map.move(1);
            if(player.getPosX()<830){
                player.moveX(2);
                player.setPosX(player.getPosX()+2);
            }
        }

        if ((isPressed(KeyCode.A) || isPressed(KeyCode.LEFT) && !pause_b)){

            map.move(-1);
            if(player.getPosX()>-20){
                player.moveX(-2);
                player.setPosX(player.getPosX()-2);
            }
        }

        if(isPressed(KeyCode.F) || command.equals("f") && fEnabled && !pause_b){

            fEnabled=false;
            Fire fire = new Fire(player.getPosX()+136, player.getPosY()+340);
            addToPane(fire);
            fireManager.getFriendlyFireList().add(fire);
            playerT.scheduleAtFixedRate(enableFire, 0, 400);
        }



        if (isPressed(KeyCode.P)) {
            pause_b = !pause_b;
            if (pause_b) {

                for(Dragon dragon:Enemies.getHorde()){
                    Text texto = new Text();
                    switch (ordenamiento){
                        case 0:
                            texto.setText("Edad: "+dragon.getEdad());
                            texto.setY(76);
                            break;
                        case 1:
                            texto.setText("Velocidad de recarga: "+dragon.getVelocidad_recarga());
                            texto.setY(76);
                            break;
                        case 2:
                            texto.setText("Edad: "+dragon.getEdad());
                            texto.setY(76);
                            break;
                        case 3:
                            if(dragon.getPadre() != null) {
                                texto.setText("Nombre: " + dragon.getName() + ".Padre: " + dragon.getPadre().getName());
                            } else{
                                texto.setText("Huerfano");
                            }
                            texto.setY(76);
                            break;
                        case 4:
                            texto.setText("Edad: "+dragon.getEdad());
                            texto.setY(76);
                            break;
                    }
                    dragon.getChildren().addAll(texto);
                }

                //enemyMovement.suspend();
                //fireMovement.suspend();
            } else {
                for(Dragon dragon:Enemies.getHorde()){
                    dragon.getChildren().remove(dragon.getChildren().size()-1);
                }
                //enemyMovement.resume();
                //fireMovement.resume();
            }
            Thread.sleep(100);

        }



        //Control
        if(command.equals("l")){
            map.move(-1);
            if(player.getPosX()>-20){
                player.moveX(-2);
                player.setPosX(player.getPosX()-2);
            }
        }
        if(command.equals("r")){
            map.move(1);
            if(player.getPosX()<830){
                player.moveX(2);
                player.setPosX(player.getPosX()+2);
            }
        }
        if(command.equals("a")){
            player.moveY(2);
            player.setPosY(player.getPosY()+2);
        }
        if(command.equals("u")){
            player.moveY(-2);
            player.setPosY(player.getPosY()-2);
        }

        setControlCommand("n");

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

    public static String getControlCommand() {
        return controlCommand;
    }

    public static void setControlCommand(String controlCommand) {
        InterfazJuego.controlCommand = controlCommand;
    }


    /**
     * Finaliza el juego
     * */
    public static void finish() throws JAXBException, UnsupportedEncodingException, NTLMException {
        Cliente client = new Cliente();
        client.sendMessage("end");
        Logger.Logging.log("info","Juego terminado");
        System.exit(0);
    }


    public static void nextOrden(){
        ordenamiento = (ordenamiento+1)%5;
    }



}
