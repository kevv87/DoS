package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import logic.Sorter;

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

                    if ( idAlineacion%5 == 0) { //primer Caso de eliminacion

                        listaDragonPos.clear();
                        listaDragonPos.addAll(Enemies.copyHorde(Enemies.getHorde())); //OBTIENE POSICION DE DRAGONES

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por QUICKSORT
                        Enemies.setHorde(sorter.quickSortAscendente(Enemies.getHorde()));

                        Enemies.setExitDragonMov(true);

                        Thread.sleep(2000);
                        //Acomodo VISUAL

                        boolean bool = false;
                        int cont = 0;

                        for (Dragon dragon : Enemies.getHorde()){

                            double limX = listaDragonPos.get(cont).getPosX();
                            double limY = listaDragonPos.get(cont).getPosY();

                            double actualX = dragon.getPosX();
                            double actualY = dragon.getPosY();

                            double movX = 0;
                            double movY = 0;

                            //ASIGNA DIRECCION DE MOVIMIENTO PARA EL DRAGON

                            if ((limX - actualX)<0){
                                movX = (limX - actualX)/10;
                            }

                            else if((limX - actualX) > 0){
                                movX = (limX - actualX)/10;
                            }

                            if ((limY - actualY)<0){
                                movY = (limY - actualY)/10;
                            }

                            else if((limY - actualY)> 0){
                                movY = (limY - actualY)/10;
                            }



                            if (limX != actualX || limY != actualY) {

                                    if(movX < 0 && movY <= 0){
                                        while (!bool) {
                                            Thread.sleep(50);
                                            if (limX >= dragon.getPosX() && limY >= dragon.getPosY()) {
                                                dragon.setPosX(dragon.getPosX() + (limX - dragon.getPosX()));
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(dragon.getPosY() + (limY - dragon.getPosY()));
                                                dragon.setTranslateY(dragon.getPosY());
                                                bool = true;
                                            }
                                            else{
                                                dragon.setPosX(dragon.getPosX() + movX);
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(dragon.getPosY() + movY);
                                                dragon.setTranslateY(dragon.getPosY());
                                            }
                                        }

                                    }

                                    else if ( movX <= 0 && movY > 0){
                                        while (!bool) {
                                            Thread.sleep(50);
                                            if (limX >= dragon.getPosX() && limY <= dragon.getPosY()) {
                                                dragon.setPosX(dragon.getPosX() + (limX - dragon.getPosX()));
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(limY + (dragon.getPosY() - limY));
                                                dragon.setTranslateY(dragon.getPosY());
                                                bool = true;
                                            } else {
                                                dragon.setPosX(dragon.getPosX() + movX);
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(dragon.getPosY() + movY);
                                                dragon.setTranslateY(dragon.getPosY());
                                            }
                                        }
                                    }

                                    else if (movX >= 0 && movY < 0){
                                        while (!bool) {
                                            Thread.sleep(50);
                                            if (limX <= dragon.getPosX() && limY >= dragon.getPosY()) {
                                                dragon.setPosX(limX + (dragon.getPosX() - limX));
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(dragon.getPosY() + (limY - dragon.getPosY()));
                                                dragon.setTranslateY(dragon.getPosY());
                                                bool = true;
                                            } else {
                                                dragon.setPosX(dragon.getPosX() + movX);
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(dragon.getPosY() + movY);
                                                dragon.setTranslateY(dragon.getPosY());
                                            }
                                        }
                                    }

                                    else {
                                        while (!bool) {
                                            Thread.sleep(50);
                                            if (limX <= dragon.getPosX() && limY <= dragon.getPosY()) {
                                                dragon.setPosX(limX + (dragon.getPosX() - limX));
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(limY + (dragon.getPosY() - limY));
                                                dragon.setTranslateY(dragon.getPosY());
                                                bool = true;
                                            } else {
                                                dragon.setPosX(dragon.getPosX() + movX);
                                                dragon.setTranslateX(dragon.getPosX());
                                                dragon.setPosY(dragon.getPosY() + movY);
                                                dragon.setTranslateY(dragon.getPosY());
                                            }
                                        }
                                    }
                            }

                            bool = false;
                            cont++;

                        }

                        Enemies.setExitDragonMov(false);

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
