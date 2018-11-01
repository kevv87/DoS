package pruebas;
import Actors.factories.DragonFactory;
import Actors.factories.dragons.*;
import junit.framework.*;

public class ObjectCreation extends TestCase{

    DragonA dragonA;
    DragonB dragonB;
    DragonC dragonC;
    DragonToSend dragonToSend;


    public ObjectCreation(String name){
        super(name);
    }

    public void setUp(){
        dragonA = new DragonA(23,0,"nomA",null);
        dragonB = new DragonB(0,72,"nomB",null);
        dragonC = new DragonC(21,43,"nomC",null);
        dragonToSend = new DragonToSend("nomA", 0, 123321, 2, "A", null, 23,0, "null",false);
    }

    public void testDragonCreation(){
        Dragon resultA = DragonFactory.getDragon("A", 23, 0, "nomA", null);
        Dragon resultB = DragonFactory.getDragon("B", 0, 72, "nomB", null);
        Dragon resultC = DragonFactory.getDragon("C", 21, 43, "nomC", null);
        DragonToSend result = DragonFactory.getDragon(dragonA);
        // Dragon A
        assertEquals(resultA.getX(), dragonA.getX());
        assertEquals(resultA.getY(), dragonA.getY());
        assertEquals(resultA.getName(), dragonA.getName());
        assertEquals(resultA.getPadre(), dragonA.getPadre());
        // Dragon B
        assertEquals(resultB.getX(), dragonB.getX());
        assertEquals(resultB.getY(), dragonB.getY());
        assertEquals(resultB.getName(), dragonB.getName());
        assertEquals(resultB.getPadre(), dragonB.getPadre());
        // Dragon C
        assertEquals(resultC.getX(), dragonC.getX());
        assertEquals(resultC.getY(), dragonC.getY());
        assertEquals(resultC.getName(), dragonC.getName());
        assertEquals(resultC.getPadre(), dragonC.getPadre());
        // DragonToSend
        assertEquals(result.getX(), dragonToSend.getX());
        assertEquals(result.getY(), dragonToSend.getY());
        assertEquals(result.getName(), dragonToSend.getName());
        assertEquals(result.getPadre(), dragonToSend.getPadre());
    }

}
