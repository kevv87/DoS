package Interfaz;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.event.MouseListener;

public class ArenaController {

    @FXML
    private ImageView pepa;

    @FXML
    private ImageView potato;

    private int movX= 3;

    public void initialize(){
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100), (evt) -> {
                    pepa.setX(pepa.getX()+movX);

            if (pepa.getBoundsInParent().intersects(potato.getBoundsInParent())){
                movX = -movX;
                potato.setVisible(false);
            }
        }
        ));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public ImageView getPepa() {
        return pepa;
    }

    public ImageView getPotato() {
        return potato;
    }

}
