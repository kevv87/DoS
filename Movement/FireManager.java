package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;

import Actors.factories.dragons.DragonToSend;
import Interfaz.Btree;
import Interfaz.InterfazJuego;

import conectividad.Cliente;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import logic.AsignadorParametros;
import logic.PositionManager;
import logic.Sorter;
import utils.Nodo;

import java.util.LinkedList;

public class FireManager {

    private static LinkedList<Fire> friendlyFireList = new LinkedList<>();
    private static LinkedList<Fire> foeFireList = new LinkedList<>();
    private static Pane pane;
    private double screenWidth;
    private static DragonHorde Enemies;
    private static LinkedList<Dragon> listaDragonPos = new LinkedList<>(); //Para obtener posiciones invariables
    private static int idAlineacion = 0;
    private static TextArea textArea;
    private static Sorter sorter = new Sorter();

    /**
     * Constructor principal de la clase
     */

    public FireManager(Pane pane, double width, DragonHorde Enemies, TextArea textArea){
        this.textArea = textArea;
        this.pane = pane;
        this.screenWidth = width;
        this.Enemies = Enemies;

    }

    public FireManager(){

    }

    public static void mostrar_arbol(){
        LinkedList<Dragon> lista = DragonHorde.Horde;
        utils.LinkedList<String> names = new utils.LinkedList<String>();
        for(Dragon dragon:lista){
            names.add(dragon.getName());
        }
        Btree TreeB = new Btree();
        TreeB.insertion(names);
        textArea.setText("\n\n\n\n"+TreeB.print());

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

                    InterfazJuego.nextOrden();
                    if (idAlineacion%5 == 0) { // primer Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por SelectionSort
                        LinkedList<Dragon> listaordenada = mineToYours(new Cliente().sendList(yoursToMine(Enemies.getHorde()),"selection"));
                        LinkedList<Dragon> listaenemigos = Enemies.getHorde();
                        LinkedList<Dragon> nuevalista = new LinkedList<>();
                        int limite = listaordenada.size();
                        int cont = 0;
                        while(cont<limite){
                            for(Dragon dragon:listaenemigos){
                                if(dragon.getEdad() == listaordenada.get(cont).getEdad()){
                                    nuevalista.add(dragon);
                                    break;
                                }
                            }
                            cont++;
                        }

                        Enemies.setHorde(nuevalista);

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

                    }

                    else if (idAlineacion%5 == 1) { //segundo Caso de eliminacion insertionSort

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por Insertion
                        LinkedList<Dragon> listaordenada = mineToYours(new Cliente().sendList(yoursToMine(Enemies.getHorde()),"insertion"));
                        LinkedList<Dragon> listaenemigos = Enemies.getHorde();
                        LinkedList<Dragon> nuevalista = new LinkedList<>();
                        int limite = listaordenada.size();
                        int cont = 0;
                        while(cont<limite){
                            for(Dragon dragon:listaenemigos){
                                if(dragon.getEdad() == listaordenada.get(cont).getEdad()){
                                    nuevalista.add(dragon);
                                    break;
                                }
                            }
                            cont++;
                        }

                        Enemies.setHorde(nuevalista);


                        //Acomodo VISUAL


                        for (Dragon dragon : Enemies.getHorde()){
                            dragon.setConstantPosPerDragon(false);
                        }

                        PositionManager.asignaPosLista(Enemies);
                        Enemies.setEnemiesStop(true);
                        //SorterDisplayer.acomodoVisualSort(Enemies,listaDragonPos,cont,bool);

                        //Vuelve a correr el Thread del movimiento de la Horde
                        idAlineacion+=1;

                    }

                    else if (idAlineacion%5 == 2) { //tercer Caso de eliminacion


                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por Insertion
                        LinkedList<Dragon> listaordenada = mineToYours(new Cliente().sendList(yoursToMine(Enemies.getHorde()),"quick"));
                        LinkedList<Dragon> listaenemigos = Enemies.getHorde();
                        LinkedList<Dragon> nuevalista = new LinkedList<>();
                        int limite = listaordenada.size();
                        int cont = 0;
                        while(cont<limite){
                            for(Dragon dragon:listaenemigos){
                                if(dragon.getEdad() == listaordenada.get(cont).getEdad()){
                                    nuevalista.add(dragon);
                                    break;
                                }
                            }
                            cont++;
                        }


                        //Acomodo por QUICKSORT
                        Enemies.setHorde(nuevalista);

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

                    }

                    else if (idAlineacion%5 == 3) { //cuarto Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por AVLTREE

                        AsignadorParametros asignador = new AsignadorParametros(Enemies.getHorde());
                        asignador.asignaPadres(Enemies.getHorde());

                        //Acomodo por Insertion
                        LinkedList<Dragon> listaordenada = mineToYours(new Cliente().sendList(yoursToMine(Enemies.getHorde()),"binario"));
                        LinkedList<Dragon> listaenemigos = Enemies.getHorde();
                        LinkedList<Dragon> nuevalista = new LinkedList<>();
                        int limite = listaordenada.size();
                        int cont = 0;
                        while(cont<limite){
                            for(Dragon dragon:listaenemigos){
                                if(dragon.getEdad() == listaordenada.get(cont).getEdad()){
                                    nuevalista.add(dragon);
                                    break;
                                }
                            }
                            cont++;
                        }

                        Enemies.setHorde(nuevalista);

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

                    }
                    else if (idAlineacion%5 == 4) { //quinto Caso de eliminacion

                        Enemies.getHorde().remove(enemy); //remover de ArrayList
                        remove(enemy);//remover enemigo del Pane
                        remove(fire);//remover fuego del Pane
                        friendlyFireList.remove(fire); // Elimina de la lista fuegos

                        //Acomodo por AVLTREE

                        AsignadorParametros asignador = new AsignadorParametros(Enemies.getHorde());
                        asignador.asignaPadres(Enemies.getHorde());

                        LinkedList<Dragon> listaordenada = mineToYours(new Cliente().sendList(yoursToMine(Enemies.getHorde()),"avl"));
                        LinkedList<Dragon> listaenemigos = Enemies.getHorde();
                        LinkedList<Dragon> nuevalista = new LinkedList<>();
                        int limite = listaordenada.size();
                        int cont = 0;
                        while(cont<limite){
                            for(Dragon dragon:listaenemigos){
                                if(dragon.getEdad() == listaordenada.get(cont).getEdad()){
                                    nuevalista.add(dragon);
                                    break;
                                }
                            }
                            cont++;
                        }

                        Enemies.setHorde(nuevalista);

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





                    }
                    mostrar_arbol();

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

    public static utils.LinkedList<Dragon> yoursToMine(java.util.LinkedList<Dragon> list){

        utils.LinkedList<Dragon> newlist = new utils.LinkedList<Dragon>();
        for(Dragon dragon:list){
            newlist.add((dragon));
        }
        return newlist;

    }

    public static java.util.LinkedList<Dragon> mineToYours(utils.LinkedList<DragonToSend> list){
        java.util.LinkedList<Dragon> newlist = new java.util.LinkedList<Dragon>();

        Nodo aux = list.getInicio();

        while(aux!=null){

            newlist.add(DragonFactory.getDragon((DragonToSend)aux.getElemento()));
            aux = aux.getSiguiente();
        }

        return newlist;
    }

}
