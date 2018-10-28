/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


/**
 *
 * @author Tomás
 */
public class InterfazInicio extends Application {
    static MediaPlayer player;
    @Override
    public void start(Stage stage) throws Exception {
        Parent inicio = FXMLLoader.load(getClass().getResource("PantallaInicio.fxml"));
        Scene iniciar = new Scene(inicio);
        //Media media = new Media(getClass().getClassLoader().getResource("file:///utils/PantallaInicio.mp3").toString());
        //this.player = new MediaPlayer(media);
        //player.setCycleCount(MediaPlayer.INDEFINITE);
        //player.play();
        
        stage.setResizable(false); 
         stage.setScene(iniciar);
         stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
