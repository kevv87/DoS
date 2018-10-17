package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

public class FireManager {

    private static LinkedList<Fire> friendlyFireList = new LinkedList<>();
    private static LinkedList<Fire> foeFireList = new LinkedList<>();
    private static Pane pane;
    private double screenWidth;
    private static DragonHorde Enemies;

    /**
     * Constructor principal de la clase
     */

    public FireManager(Pane pane, double width, DragonHorde Enemies){
        this.pane = pane;
        this.screenWidth = width;
        this.Enemies = Enemies;
    }

    public FireManager(){

    }

    public static void moveFire() {

        for (Fire fire : friendlyFireList) { // movimiento de fuegos amigos
            fire.setPosX(fire.getPosX() + 2);
            fire.setTranslateX(fire.getPosX());

            if (fire.getPosX() >= 1200){
                remove(fire);
                friendlyFireList.remove(fire);
                return;
            }

            for(Dragon enemy:Enemies.getHorde()){
                boolean interseccion = fire.getBoundsInParent().intersects(enemy.getBoundsInParent());
                if(interseccion){
                    Enemies.getHorde().remove(enemy);
                    remove(enemy);
                    remove(fire);
                    friendlyFireList.remove(fire);
                    return;
                }
            }
        }

        for (Fire fire : foeFireList) { // Movimiento de fuegos enemigos
            fire.setPosX(fire.getPosX() - 2);
            fire.setTranslateX(fire.getPosX());
        }
    }

    public LinkedList<Fire> getFriendlyFireList() {
        return friendlyFireList;
    }

    public LinkedList<Fire> getFoeFireList(){
        return foeFireList;
    }

    public static void remove(Dragon dragon){
        Platform.runLater(() -> pane.getChildren().remove(dragon));
    }
    public static void remove(Fire fire){
        Platform.runLater(() -> pane.getChildren().remove(fire));
    }


}
