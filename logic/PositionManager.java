package logic;

import Actors.factories.dragons.Dragon;
import Movement.DragonHorde;

public class PositionManager {

    public static void asignaPosLista(DragonHorde Enemies) {

        double columna = 0;
        double fila = 0;

        double filaLimite = Enemies.getDragonsPerColum();
        double columnaLimite = Enemies.getColumnas();

        double posXinicial = Enemies.getPosX();

        for (Dragon dragon : Enemies.getHorde()) {

            if (fila >= filaLimite) {
                fila = 0;
                columna += 1;
            }

            dragon.setPosXfinal(posXinicial + (columna * 76));
            dragon.setPosYfinal(20 + (fila * 76));

            fila+=1;
        }
    }

}
