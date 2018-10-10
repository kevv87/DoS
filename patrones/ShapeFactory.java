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
public class ShapeFactory extends AbstractFactory{
    

    @Override
    Colors getColor(String color) {
        return null;
    }

    @Override
    Shape getShape(String shapeType) {
        switch(shapeType){
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            default:
                return null;
        }
    }
    
}
