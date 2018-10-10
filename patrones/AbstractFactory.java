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
public abstract class AbstractFactory {
    
    abstract Colors getColor(String color);
    abstract Shape getShape(String shapeType);
    
}
