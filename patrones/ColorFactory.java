/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patrones;

/**
 *
 * @author kevv87
 */
public class ColorFactory extends AbstractFactory{

    @Override
    Colors getColor(String color) {
        switch(color){
            case "RED":
                return new Red();
            case "GREEN":
                return new Green();
            case "BLUE":
                return new Blue();
            default:
                return null;
        }
    }

    @Override
    Shape getShape(String shapeType) {
        return null;
    }
    
}
