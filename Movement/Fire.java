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
        Image pepa = new Image(getClass().getResourceAsStream("powerpig.jpg"));
        ImageView pepaIV = new ImageView(pepa);
        pepaIV.setViewport(new Rectangle2D(0,0, 30, 30));
        
        getChildren().addAll(pepaIV);
    }
    
    public void move(){
        x = x+2;
        setTranslateX(x);
    }
    
    public double getPosX(){
        return x;
    }
}
