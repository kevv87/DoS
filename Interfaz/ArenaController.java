package Interfaz;

import javafx.scene.shape.Circle;
import logic.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.application.Platform;
import java.awt.event.MouseListener;

public class ArenaController {

    @FXML
    private ImageView pepa;

    @FXML
    private ImageView potato;

    @FXML
    private Pane mainPane;

    private Group images = new Group();

    private int movX= 50;
    private int disX = 400;

    public void initialize(){

        Platform.setImplicitExit(false);

        for(int i = 0; i<3; i++){
            Image image = new Image("/imgs/potato.jpg");
            ImageView image2 = new ImageView(image);
            image2.setX(image2.getX()+disX);
            image2.setY(45);

            disX += 150;
            images.getChildren().add(image2);
            System.out.println(i);
        }

        addToPane(images);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100), (evt) -> {
                    pepa.setX(pepa.getX()+movX);
                    int cont=0;
                    while(cont<images.getChildren().size()){
                        if (pepa.getBoundsInParent().intersects(images.getChildren().get(0).getBoundsInParent())){
                            movX = -movX;
                            images.getChildren().remove(0);
                    }
                    cont++;
                    }
                    if (pepa.getX()<1){
                        movX=-movX;
                    }
        }
        ));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void addToPane(Group group){
        mainPane.getChildren().add(group);
    }

    public ImageView getPepa() {
        return pepa;
    }

    public ImageView getPotato() {
        return potato;
    }

}
