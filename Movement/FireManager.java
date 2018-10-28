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
            }

            for(Dragon enemy:Enemies.getHorde()){
                boolean interseccion = fire.getBoundsInParent().intersects(enemy.getBoundsInParent());
                if(interseccion){

                    if (idAlineacion%3 == 0) { // primer Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por SelectionSort
                        Enemies.setHorde(sorter.selectionSort(Enemies.getHorde()));

                        //Acomodo VISUAL


                        for (Dragon dragon : Enemies.getHorde()){
                            dragon.setConstantPosPerDragon(false);
                        }

                        //Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort2(Enemies,bool);

                        //Enemies.setEnemiesStop(false);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        break;
                    }

                    else if (idAlineacion%3 == 1) { //segundo Caso de eliminacion insertionSort

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por Insertion
                        Enemies.setHorde(sorter.insertionSort(Enemies.getHorde()));


                        //Acomodo VISUAL


                        for (Dragon dragon : Enemies.getHorde()){
                            dragon.setConstantPosPerDragon(false);
                        }

                       // Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        break;
                    }

                    else if (idAlineacion%3 == 2) { //primer Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por QUICKSORT
                        Enemies.setHorde(sorter.quickSort(Enemies.getHorde()));

                        //Enemies.setExitDragonMov(true);

                        //Enemies.setExitDragonMov(false);

                        //Acomodo VISUAL

                        boolean bool = false;
                        int cont = 0;

                       for (Dragon dragon : Enemies.getHorde()){
                           dragon.setConstantPosPerDragon(false);
                       }

                        //Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        break;
                    }
                }
            }
            break;
        }
        /**

        for (Fire fire : foeFireList) { // Movimiento de fuegos enemigos
            fire.setPosX(fire.getPosX() - 2);
            fire.setTranslateX(fire.getPosX());
        }
         */
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
