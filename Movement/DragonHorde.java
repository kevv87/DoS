package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;
import logic.AsignadorParametros;
import utils.Nodo;

public class DragonHorde{
    private int SpawningPointX = 1290;
    private int SpawningPointY = 0;
    private double PosX = 1290;
    private LinkedList<Dragon> Horde = new LinkedList<>();
    private boolean enemiesStop = false;
    /**
     * Constructor principal de la clase
     * @param pane Contenedor donde se pondran los dragones
     * @param A Cantidad de dragones tipo A
     * @param B Cantidad de dragones tipo B
     * @param C Cantidad de dragones tipo C
     * @param columnas Cantidad de columnas 
     * @param windowH Altura de la ventana de juego
     */
    public DragonHorde(Pane pane,int A, int B, int C, int columnas, double windowH){

        if(Horde.isEmpty()) {
            int total = A+B+C;
            int dragonsPerColumn = total/columnas;
            System.out.println(windowH);
            while(dragonsPerColumn*76+SpawningPointY>windowH){
                columnas++;
                dragonsPerColumn = total/columnas;
            }
            for(int j = 0; j<columnas;j++){
            for (int i = 0; i<dragonsPerColumn; i++){
                Actors.factories.dragons.Dragon newDragon;
                if(A!=0){ //sobre escribe newDragon
                    newDragon = DragonFactory.getDragon("A", SpawningPointX, SpawningPointY, "probe", null);
                    A--;
                }else if(B!=0){
                    newDragon = DragonFactory.getDragon("B", SpawningPointX, SpawningPointY, "probe", null);
                    B--;
                }else{
                    newDragon = DragonFactory.getDragon("C", SpawningPointX, SpawningPointY, "probe", null);
                    C--;
                }
                System.out.println(newDragon.getPosY());
                Horde.add(newDragon);
                SpawningPointY+=76;
                newDragon.setTranslateX(newDragon.getPosX());
                newDragon.setTranslateY(newDragon.getPosY());
                pane.getChildren().add(newDragon);
            }
            SpawningPointY=0;
            SpawningPointX+=76; 
        }
            AsignadorParametros asignador = new AsignadorParametros(Horde);
            asignador.asignaEdad();
            asignador.asignaVelocidad();
        }
    }

    /**
     * Constructor basado en una lista de dragones ya hecha
     * @param dragons Lista de dragones hecha.
     * */
    public DragonHorde(Pane pane, utils.LinkedList<Dragon> dragons){
        System.out.println(dragons.getTamanio());
        Nodo aux = dragons.getInicio();
        Dragon newDragon;
        while(aux!=null){
            newDragon = (Dragon)aux.getElemento();
            Horde.add(newDragon);
            newDragon.setTranslateX(newDragon.getPosX());
            newDragon.setTranslateY(newDragon.getPosY());
            pane.getChildren().add(newDragon);
            aux = aux.getSiguiente();
        }
        AsignadorParametros asignador = new AsignadorParametros(Horde);
        asignador.asignaEdad();
    }

    public void moveHorde(){
        if(enemiesStop==false){
            for(int i=0; i<Horde.size(); i++){
                Dragon TMP = Horde.get(i);
                TMP.setPosX(TMP.getPosX()-0.3);
                TMP.setTranslateX(TMP.getPosX());
            }
            PosX = PosX-0.3;
        }

    }

    public int getSpawningPointY() {
        return SpawningPointY;
    }

    public void setSpawningPointY(int spawningPointY) {
        SpawningPointY = spawningPointY;
    }

    public double getPosX() {
        return PosX;
    }

    public LinkedList<Dragon> getHorde() {
        return Horde;
    }

    public void setHorde(LinkedList<Dragon> Horde) {this.Horde=Horde; }

    public boolean isEnemiesStop() {
        return enemiesStop;
    }

    public void setEnemiesStop(boolean enemiesStop) {
        this.enemiesStop = enemiesStop;
    }
}
