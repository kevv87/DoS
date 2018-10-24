package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import logic.Sorter;
import Interfaz.SorterDisplayer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public class FireManager {

    private static LinkedList<Fire> friendlyFireList = new LinkedList<>();
    private static LinkedList<Fire> foeFireList = new LinkedList<>();
    private static Pane pane;
    private double screenWidth;
    private static DragonHorde Enemies;
    private static LinkedList<Dragon> listaDragonPos = new LinkedList<>(); //Para obtener posiciones invariables
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

                    if (idAlineacion%2 == 0) { //primer Caso de eliminacion

                        listaDragonPos.clear();
                        listaDragonPos.addAll(Enemies.copyHorde(Enemies.getHorde())); //OBTIENE POSICION DE DRAGONES

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por QUICKSORT
                        Enemies.setHorde(sorter.quickSort(Enemies.getHorde()));

                        Enemies.setExitDragonMov(true);

                        Thread.sleep(1000);
                        //Acomodo VISUAL

                        boolean bool = false;
                        int cont = 0;
                        SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        Enemies.setExitDragonMov(false);
                        idAlineacion+=1;
                        return;
                    }

                    else if (idAlineacion%2 == 1) { //segundo Caso de eliminacion

                        listaDragonPos.clear();
                        listaDragonPos.addAll(Enemies.copyHorde(Enemies.getHorde())); //OBTIENE POSICION DE DRAGONES

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por QUICKSORT
                        Enemies.setHorde(sorter.selectionSort(Enemies.getHorde()));

                        Enemies.setExitDragonMov(true);

                        Thread.sleep(1000);
                        //Acomodo VISUAL

                        boolean bool = false;
                        int cont = 0;
                        SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        Enemies.setExitDragonMov(false);
                        idAlineacion+=1;
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
