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


import java.sql.SQLOutput;

import java.io.UnsupportedEncodingException;


import java.util.HashMap;

import conectividad.Cliente;
import com.sun.security.ntlm.NTLMException;
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

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.control.Control;

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


//import javax.xml.bind.JAXBException;

import java.util.Timer;
import java.util.TimerTask;

import Logger.Logging;
import javafx.stage.WindowEvent;

import javax.xml.bind.JAXBException;


/**
 * @author Tomas
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


    private ScrollingBG map = new ScrollingBG();
    int c = 0;  // Columna weon
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
    //private Cliente client = new Cliente();
    double width;
    double height;


    private boolean pause_b = false;
    private static int ordenamiento = -1;




    @Override

    public void start(Stage primaryStage) throws Exception {


        //client.sendMessage("Start");

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


        Cliente client =  new Cliente();
        client.sendMessage("Start");
        Logging.log("info","Iniciando juego");


        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaJuego.fxml"));
        Parent padre = inicio.load();
        this.foo = (AnchorPane)inicio.getNamespace().get("paneljuego");

        this.textarea = (TextArea)inicio.getNamespace().get("textarea");
        this.layoutactual = (Label)inicio.getNamespace().get("layout_actual");
        this.infodragon = (Label)inicio.getNamespace().get("info_dragon");
        this.scene = new Scene(padre);
        /*
        Media media = new Media(getClass().getClassLoader().getResource("utils/PantallaJuego.mp3").toString());
        MediaPlayer player = new MediaPlayer(media); 
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();*/
          newGame();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                   switch(ordenamiento){
                       case -1:
                           mostrar_layout("No ordenado");
                           break;
                       case 0:
                           mostrar_layout("Linked List por edades");
                           break;
                       case 1:
                           mostrar_layout("Linked list por velocidad");
                           break;
                       case 2:
                           mostrar_layout("Linked list por edades");
                           break;
                       case 3:
                           mostrar_layout("Arbol binario por familias");
                           break;
                       case 4:
                           mostrar_layout("Arbol AVL por edades");
                           break;
                   }
                    movePlayer();

                   

                    if(!pause_b){
                      if(Enemies.isEnemiesStop()==false){
                          Enemies.moveHorde();
                      }
                      else{
                          if (c >= 100)
                          {
                              System.out.println("Cvaz no tiene hambre");
                              c=0;
                              Enemies.setEnemiesStop(false);
                          }
                          c++;
                          SorterDisplayer.acomodoVisualSort2(Enemies,false);
                      }


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


    public void newGame() throws IOException, JAXBException {

        foo.getChildren().add(map.getBG1());
        foo.getChildren().add(map.getBG2());
        foo.getChildren().add(map.getBG3());
        player.setTranslateY(250);
        foo.getChildren().add(player);


        Enemies = new DragonHorde(foo, infodragon,textarea, cliente.getDragons());
        System.out.println(Enemies.getHorde().size());


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

        if(isPressed(KeyCode.F) && fEnabled && !pause_b){

            fEnabled=false;
            Fire fire = new Fire(player.getPosX()+136, player.getPosY()+340);
            addToPane(fire);
            fireManager.getFriendlyFireList().add(fire);
            playerT.scheduleAtFixedRate(enableFire, 1000, 400);

        }



        if (isPressed(KeyCode.P)) {
            //System.out.println("P");
            pause_b = !pause_b;
            if (pause_b) {

                for(Dragon dragon:Enemies.getHorde()){
                    Text texto = new Text();
                    switch (ordenamiento){
                        case -1:
                            texto.setText("N/A");
                            break;
                        case 0:
                            texto.setText("Edad: "+dragon.getEdad());
                            texto.setY(76);
                            break;
                        case 1:
                            texto.setText("Velocidad: "+"\n"+dragon.getVelocidad_recarga());
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
