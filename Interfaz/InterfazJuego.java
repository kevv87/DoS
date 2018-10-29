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
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import conectividad.Cliente;
import com.sun.security.ntlm.NTLMException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import Logger.Logging;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import javax.xml.bind.JAXBException;


/**
 * Pantalla de juego
 * @author Tomás
 */
public class InterfazJuego extends Application {
    private  HashMap<KeyCode, Boolean> keys = new HashMap<>();
    TextArea textarea;
    Label layoutactual;
    Label infodragon;
    Scene scene;
    ImageView vida1;
    ImageView vida2;
    ImageView vida3;
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
/**
 * Lanza la pantalla de juego
 */
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

        Cliente client =  new Cliente();
        client.sendMessage("Start");
        Logging.log("info","Iniciando juego");

        FXMLLoader inicio =  new FXMLLoader(getClass().getResource("PantallaJuego.fxml"));
        Parent padre = inicio.load();
        this.foo = (AnchorPane)inicio.getNamespace().get("paneljuego");
        this.textarea = (TextArea)inicio.getNamespace().get("textarea");
        this.textarea.setFont(Font.loadFont(InterfazJuego.class.getResource("Game of Thrones.ttf").toExternalForm(),16));
        this.layoutactual = (Label)inicio.getNamespace().get("layout_actual");
        this.layoutactual.setFont(Font.loadFont(InterfazJuego.class.getResource("Game of Thrones.ttf").toExternalForm(),20));
        this.infodragon = (Label)inicio.getNamespace().get("info_dragon");
        this.infodragon.setFont(Font.loadFont(InterfazJuego.class.getResource("Game of Thrones.ttf").toExternalForm(),18));
        this.vida1 = (ImageView)inicio.getNamespace().get("vida1");
        this.vida2 = (ImageView)inicio.getNamespace().get("vida2");
        this.vida3 = (ImageView)inicio.getNamespace().get("vida3");
        this.scene = new Scene(padre);
        Media media = new Media(getClass().getClassLoader().getResource("utils/PantallaJuego.mp3").toString());
        MediaPlayer player = new MediaPlayer(media); 
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
          newGame();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            /**
             * Muestra el layout actual
             */
            public void handle(long now) {
                try {
                   switch(ordenamiento){
                       case -1:
                           mostrar_layout("No ordenado");
                           break;
                       case 0:
                           mostrar_layout("Selection sort por edades");
                           break;
                       case 1:
                           mostrar_layout("Insertion sort por velocidad");
                           break;
                       case 2:
                           mostrar_layout("QuickSort por edades");
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

    /**
     * Muestra el layout actual
     * @param a texto para mostrar en el espacio correspondiente al layout actual
     */
    public void mostrar_layout(String a){
        this.layoutactual.setText(a);
    }
    
    /**
     * Muestra la informacion propia de un dragon en especifico
     * @param b texto para mostrar en el espacio correspondiente a la informacion propia de un dragon
     */
    public void mostrar_info(String b){
        this.infodragon.setText(b);
    }
    
    /**
     * Inserta en pantalla todos los elementos necesarios para el desarrollo del juego
     * @throws IOException
     * @throws JAXBException 
     */
    public void newGame() throws IOException, JAXBException {
        
        foo.getChildren().add(map.getBG1());
        foo.getChildren().add(map.getBG2());
        foo.getChildren().add(map.getBG3());
        player.setTranslateY(250);
        foo.getChildren().add(player);
        vida1.setImage(new Image(getClass().getResourceAsStream("Heart.png")));
        vida2.setImage(new Image(getClass().getResourceAsStream("Heart.png")));
        vida3.setImage(new Image(getClass().getResourceAsStream("Heart.png")));
        vida1.toFront();
        vida2.toFront();
        vida3.toFront();

        Enemies = new DragonHorde(foo,3,3,3,3,671);

        fireManager = new FireManager(foo, width, Enemies);

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });

    }

//Hero control
    /**
     * Muestra si una tecla determinada esta siendo oprimida
     * @param key tecla oprimida
     * @return booleano
     */
    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key, false);
    }
    /**
     * Mueve el personaje por la pantalla de juego
     * @throws InterruptedException 
     */
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
                                texto.setText("N: " + dragon.getName() +"\n"+ "P: " + dragon.getPadre().getName());
                            } else{
                                texto.setText("N: "+dragon.getName()+"\n"+"Huerfano");
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

            } else {
                for(Dragon dragon:Enemies.getHorde()){
                    dragon.getChildren().remove(dragon.getChildren().size()-1);
                }
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
    
    /**
     * Anade un disparo a la pantalla de juego
     * @param toad disparo
     */
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
 
    //Setter & Getter 
    public static String getControlCommand() {
        return controlCommand;
    }
    
    /**
     * 
     * @param controlCommand 
     */
    public static void setControlCommand(String controlCommand) {
        InterfazJuego.controlCommand = controlCommand;
    }


    /**
     * Informa y registra la finalizacion del juego
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     * @throws NTLMException 
     */
    public static void finish() throws JAXBException, UnsupportedEncodingException, NTLMException {
        Cliente client = new Cliente();
        client.sendMessage("end");
        Logger.Logging.log("info","Juego terminado");
        System.exit(0);
    }
}
