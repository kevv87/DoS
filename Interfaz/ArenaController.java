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


    private int movX= 3;
    private int disX = 200;
    private int disY = 250;


    public void initialize(){

        Platform.setImplicitExit(false);

        for(int i = 0; i<3; i++){
            Image image = new Image("/imgs/potato.jpg");
            ImageView image2 = new ImageView(image);
            image2.setX(image2.getX()+disX);
            image2.setY(disY);
            disY -= 50;
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
                        if (pepa.getBoundsInParent().intersects(images.getChildren().get(cont).getBoundsInParent())){
                            movX = -movX;
                            System.out.println("cont" + cont);
                            images.getChildren().remove(cont);
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