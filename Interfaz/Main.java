package Interfaz;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Duration;
import javafx.event.ActionEvent;

public class Main extends Application {

    Stage window;
    private  int dX = 100;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("arena.fxml"));
        Parent mainParent = loader.load();

        ArenaController controller = loader.getController();


        window = primaryStage;

        window.setTitle("GoS");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeRequest();
        });


        window.setScene(new Scene(mainParent, 800, 600));
        window.show();
    }

    public void closeRequest() {
        boolean booli = AlertWindow.display();
        if (booli == true) {
            window.close();
        }
    }

}
