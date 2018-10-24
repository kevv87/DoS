package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import logic.Sorter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

public class FireManager {

    private static LinkedList<Fire> friendlyFireList = new LinkedList<>();
    private static LinkedList<Fire> foeFireList = new LinkedList<>();
    private static Pane pane;
    private double screenWidth;
    private static DragonHorde Enemies;
    private static ArrayList<Dragon> listaDragonPos = new ArrayList<>(); //Para obtener posiciones invariables
    private static int idAlineacion = 0;
    private static Sorter sorter = new Sorter();

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

    public static void moveFire () throws Exception {

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
                    if (idAlineacion%5==0) { //primer Caso de eliminacion

                        listaDragonPos.clear();
                        listaDragonPos.addAll(Enemies.getHorde()); //OBTIENE POSICION DE DRAGONES

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por QUICKSORT
                        Enemies.setHorde(sorter.quickSortAscendente(Enemies.getHorde()));

                        listaDragonPos.remove(Enemies.getHorde().size()); //ELIMINA ULTIMO DRAGON

                        Enemies.setExitDragonMov(true);

                        Thread.sleep(2000);
                        System.out.println("pasé");

                        Enemies.setExitDragonMov(false);
                        //Acomodo VISUAL



                        return;
                    }
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
