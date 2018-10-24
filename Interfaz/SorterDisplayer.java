package Interfaz;

import Actors.factories.dragons.Dragon;
import Movement.DragonHorde;

import java.util.LinkedList;

public class SorterDisplayer {

    public static void acomodoVisualSort(DragonHorde Enemies, LinkedList<Dragon> listaDragonPos, int cont, boolean bool) throws Exception {
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
    }

}