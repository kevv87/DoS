/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.LinkedList;

/**
 * FXML Controller class
 *
 * @author kevv87
 */
public class JuegoController implements Initializable {

    Group dragonHitboxGroup = new Group();
    LinkedList<Dragon> dragons = new LinkedList<>();
    
    @FXML private Pane gamePane;
    private Stage window;
    private double height;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        gamePane.getChildren().add(dragonHitboxGroup);
    }    
    
    /**
     * Funcion encargada de la creacion de oleadas.
     * @param cantidadA Cantidad de dragones tipo A
     * @param cantidadB Cantidad de dragones tipo B
     * @param cantidadC Cantidad de dragones tipo C
     * @param columnas Cantidad de columnas, si la cantidad de filas necesarias
     * para llenar la cantidad de columnas especificadas excede el tamanno de la
     * pantalla se agregan mas columnas hasta que la ultima fila quede dentro de
     * la misma
     * @param xo Posicion inicial en x
     * @param yo Posicion inicial en y
     */
    public void createWave(int cantidadA, int cantidadB, int cantidadC, int columnas, int xo, int yo){
        
        double x;
        double y = yo;
        double espacio = 5;
        int total = cantidadA + cantidadB + cantidadC;
        int filas = total/columnas;
        Dragon nuevoDragon;
        int j;
        
        height = window.getHeight();
        while((yo+espacio)*filas>height){  // Verifica que no se pase del alto de la pantalla
            columnas++;
            filas = total/columnas;
        }
        
        
        for(int i=0;i<filas;i++){
            x =xo;
            for(j=0;j<columnas;j++){
                if(cantidadA!=0){
                    nuevoDragon = DragonFactory.getDragon("A", x, y, "NombrePrueba", null);
                    cantidadA--;
                }else if(cantidadB!=0){
                    nuevoDragon = DragonFactory.getDragon("B",x,y,"NombrePrueba", null);
                    cantidadB--;
                }else if(cantidadC!=0){
                    nuevoDragon = DragonFactory.getDragon("C", x, y, "NombrePrueba", null);
                    cantidadC--;
                }else{
                    System.out.println("Error");
                    return;
                }
                height = nuevoDragon.getHitbox().getHeight();
                dragonHitboxGroup.getChildren().add(nuevoDragon.getHitbox());  // agrega el hitbox del nuevo dragon a la interfaz
                dragons.add(nuevoDragon);
                x += nuevoDragon.getHitbox().getWidth() + espacio;
            }
                y += height + espacio;
        } 
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
    
    
    
}

