package Tests;

import Movement.DragonHorde;
import javafx.scene.layout.Pane;
import junit.framework.TestCase;
import logic.PositionManager;

public class TestPositionManager extends TestCase {

    private Pane pane = new Pane();
    private DragonHorde Enemies = new DragonHorde(pane, 3,3,3, 3,671);

    public TestPositionManager(String name){
        super(name);
    }

    public void testasignaPosLista(){
        PositionManager.asignaPosLista(Enemies);
        if (Enemies.getHorde().get(8).getPosXfinal() == (Enemies.getPosX()+2*76) &&
                Enemies.getHorde().get(8).getPosYfinal() == (20+2*76)){
            assertTrue(true);
        } else{
            fail("no ordenó por quicksortSort");
        }
    }

    public void setUp(){

    }

}
