package Movement;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.LinkedList;

public class DragonHorde{
    private int SpawningPointX = 1290;
    private int SpawningPointY = 0;
    private int PosX = 1290;
    private ArrayList<Actors.factories.dragons.Dragon> Horde = new ArrayList<>();

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
            System.out.println(columnas);
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
        }
    }

    public void moveHorde(){
        for(int i=0; i<Horde.size(); i++){
            System.out.println("entre: " + i);
            Dragon TMP = Horde.get(i);
            TMP.setPosX(TMP.getPosX()-2);
            TMP.setTranslateX(TMP.getPosX());
        }
        PosX = PosX-2;
    }

    public int getSpawningPointY() {
        return SpawningPointY;
    }

    public void setSpawningPointY(int spawningPointY) {
        SpawningPointY = spawningPointY;
    }

    public int getPosX() {
        return PosX;
    }

    public ArrayList<Dragon> getHorde() {
        return Horde;
    }

}
