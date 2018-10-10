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
public class ConcreteFactory {
    
    public static AbstractFactory getFactory(String choice){
        switch(choice){
            case "SHAPE":
                return new ShapeFactory();
            case "COLOR":
                return new ColorFactory();
            default:
                return null;
        }
    }
    
}
