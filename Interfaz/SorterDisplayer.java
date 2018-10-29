package Interfaz;

import Actors.factories.dragons.Dragon;
import Movement.DragonHorde;

import java.util.LinkedList;

public class SorterDisplayer {

    static int boolCont = 0;
/**
 * Prueba Sorter Displayer
 * @param Enemies horda de Dragones enemigos
 * @param listaDragonPos lista de posiciones de los dragones de la horda
 * @param cont contador
 * @param bool booleano
 * @throws Exception 
 */
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

/**
 * Acomoda las hordas de dragones enemigos en la pantalla de juego
 * @param Enemies horda de dragones enemigos
 * @param bool booleano
 * @throws Exception 
 */
    public static void acomodoVisualSort2(DragonHorde Enemies, boolean bool) throws Exception {

        boolCont = 0;

        for (Dragon dragon : Enemies.getHorde()){

            double limX = dragon.getPosXfinal();
            double limY = dragon.getPosYfinal();

            double actualX = dragon.getPosX();
            double actualY = dragon.getPosY();



            //ASIGNA DIRECCION DE MOVIMIENTO PARA EL DRAGON

            if (dragon.isConstantPosPerDragon() == false){

                if ((limX - actualX)<0){
                    dragon.setMovX((limX - actualX)/100);
                }

                else if((limX - actualX) > 0){
                    dragon.setMovX((limX - actualX)/100);
                }

                if ((limY - actualY)<0){
                    dragon.setMovY((limY - actualY)/100);
                }

                else if((limY - actualY)> 0){
                    dragon.setMovY((limY - actualY)/100);
                }
                dragon.setConstantPosPerDragon(true);
            }

            double movX = dragon.getMovX();
            double movY = dragon.getMovY();

            if (limX != actualX || limY != actualY) {

                if(movX < 0 && movY <= 0){
                        if (limX >= dragon.getPosX() && limY >= dragon.getPosY()) {
                            dragon.setPosX(dragon.getPosX() + (limX - dragon.getPosX()));
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(dragon.getPosY() + (limY - dragon.getPosY()));
                            dragon.setTranslateY(dragon.getPosY());
                            bool = true;
                            boolCont+=1;
                            System.out.println("entreYSALÍ");
                        }
                        else{
                            dragon.setPosX(dragon.getPosX() + movX);
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(dragon.getPosY() + movY);
                            dragon.setTranslateY(dragon.getPosY());
                        }
                    }



                else if ( movX <= 0 && movY > 0){
                        if (limX >= dragon.getPosX() && limY <= dragon.getPosY()) {
                            dragon.setPosX(dragon.getPosX() + (limX - dragon.getPosX()));
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(limY + (dragon.getPosY() - limY));
                            dragon.setTranslateY(dragon.getPosY());
                            bool = true;
                            boolCont+=1;
                        } else {
                            dragon.setPosX(dragon.getPosX() + movX);
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(dragon.getPosY() + movY);
                            dragon.setTranslateY(dragon.getPosY());
                        }
                }

                else if (movX >= 0 && movY < 0){
                        if (limX <= dragon.getPosX() && limY >= dragon.getPosY()) {
                            dragon.setPosX(limX + (dragon.getPosX() - limX));
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(dragon.getPosY() + (limY - dragon.getPosY()));
                            dragon.setTranslateY(dragon.getPosY());
                            bool = true;
                            boolCont +=1;
                        } else {
                            dragon.setPosX(dragon.getPosX() + movX);
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(dragon.getPosY() + movY);
                            dragon.setTranslateY(dragon.getPosY());
                        }
                }

                else {
                        if (limX <= dragon.getPosX() && limY <= dragon.getPosY()) {
                            dragon.setPosX(limX + (dragon.getPosX() - limX));
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(limY + (dragon.getPosY() - limY));
                            dragon.setTranslateY(dragon.getPosY());
                            bool = true;
                            boolCont+=1;
                        } else {
                            dragon.setPosX(dragon.getPosX() + movX);
                            dragon.setTranslateX(dragon.getPosX());
                            dragon.setPosY(dragon.getPosY() + movY);
                            dragon.setTranslateY(dragon.getPosY());
                        }

                }
            }
            bool = false;
        }
    }


}
