package Interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("arena.fxml"));
        window = primaryStage;
        window.setTitle("GoS");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeRequest();
        });
        window.setScene(new Scene(root, 600, 400));
        window.show();
    }

    public void closeRequest() {
        boolean booli = AlertWindow.display();
        if (booli == true) {
            window.close();
        }
    }

}
