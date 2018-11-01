package Movement;

import Actors.factories.dragons.Dragon;

import Interfaz.InterfazJuego;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import logic.AsignadorParametros;
import logic.PositionManager;
import logic.Sorter;

import java.util.LinkedList;

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

    public static void moveFire (){

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
                  if(enemy.getResistencia()==0){
                    if (idAlineacion%5 == 0) { // primer Caso de eliminacion
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

                        PositionManager.asignaPosLista(Enemies);
                        Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort2(Enemies,bool);

                        //Enemies.setEnemiesStop(false);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        return;
                    }

                    else if (idAlineacion%5 == 1) { //segundo Caso de eliminacion insertionSort

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

                        PositionManager.asignaPosLista(Enemies);
                        Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        return;
                    }

                    else if (idAlineacion%5 == 2) { //tercer Caso de eliminacion


                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por QUICKSORT
                        Enemies.setHorde(sorter.quickSort(Enemies.getHorde()));

                        //Enemies.setExitDragonMov(true);

                        //Enemies.setExitDragonMov(false);

                        //Acomodo VISUAL


                        for (Dragon dragon : Enemies.getHorde()){
                            dragon.setConstantPosPerDragon(false);
                        }


                        PositionManager.asignaPosLista(Enemies);
                        Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        return;
                    }

                    else if (idAlineacion%5 == 3) { //cuarto Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos
                    }
                  }else{
                      enemy.setResistencia(enemy.getResistencia()-1);
                  }

                        //Acomodo por AVLTREE

                        AsignadorParametros asignador = new AsignadorParametros(Enemies.getHorde());
                        asignador.asignaPadres(Enemies.getHorde());

                        Enemies.setHorde(sorter.arbolBinario(Enemies.getHorde()));

                        //Enemies.setExitDragonMov(true);

                        //Enemies.setExitDragonMov(false);

                        //Acomodo VISUAL

                        for (Dragon dragon : Enemies.getHorde()){
                            dragon.setConstantPosPerDragon(false);
                        }


                        Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
                        return;
                    }
                    else if (idAlineacion%5 == 4) { //quinto Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por AVLTREE

                        AsignadorParametros asignador = new AsignadorParametros(Enemies.getHorde());
                        asignador.asignaPadres(Enemies.getHorde());

                        Enemies.setHorde(sorter.arbolBinario(Enemies.getHorde()));

                        //Enemies.setExitDragonMov(true);

                        //Enemies.setExitDragonMov(false);

                        //Acomodo VISUAL

                        for (Dragon dragon : Enemies.getHorde()){
                            dragon.setConstantPosPerDragon(false);
                        }


                        Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;
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
