/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author elorim
 */
public class Fire extends Pane{
    double x;
    double y;
    
    
    public Fire(double x, double y){
        this.y = y;
        this.x = x;
        setTranslateX(x);
        setTranslateY(y);
        Image fire = new Image(getClass().getResourceAsStream("playerfire.gif"));
        ImageView pepaIV = new ImageView(fire);
        pepaIV.setFitWidth(40);
        pepaIV.setFitHeight(40);
        getChildren().addAll(pepaIV);
    }
    
    public double getPosX(){
        return x;
    }

    public void setPosX(double x) { this.x = x;}
}
