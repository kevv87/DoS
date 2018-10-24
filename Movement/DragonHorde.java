package Movement;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

public class DragonHorde{
    private int SpawningPointX = 1290;
    private int SpawningPointY = 0;
    private int PosX = 1290;
    private ArrayList<Dragon> Horde = new ArrayList<>();

    public DragonHorde(int HordeSize, AnchorPane pane){
        if(Horde.isEmpty()) {
            for (int i = 0; i < HordeSize; i++) {
                Dragon newDragon = new Dragon(SpawningPointX, SpawningPointY);
                Horde.add(newDragon);
                setSpawningPointY(getSpawningPointY() + 76);
                newDragon.setTranslateX(newDragon.getPosX());
                newDragon.setTranslateY(newDragon.getPosY());
                pane.getChildren().add(newDragon);
            }
            SpawningPointY=0;
        }
    }

    public void moveHorde(){
        for(int i=0; i<Horde.size(); i++){
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
