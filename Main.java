
import interfaz.JuegoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevv87
 */
public class Main extends Application{
    
    Stage window;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaz/Juego.fxml"));
        Parent root = loader.load();
        JuegoController controller = loader.getController();
        
        window = primaryStage;
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.show();
        controller.setWindow(window);
        controller.createWave(100,0,0,2,0,0);
    }
    
    
    
}
