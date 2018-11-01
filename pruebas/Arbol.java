package pruebas;
import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonA;
import junit.framework.*;
import logic.AVLTree;
import sun.awt.image.ImageWatched;
import utils.ArbolBinario;
import utils.LinkedList;

public class Arbol extends TestCase{

    public Arbol(String name){
        super(name);
    }
    AVLTree avl;
    Dragon[] dragones;
    java.util.LinkedList ageResult;
    ArbolBinario binario;

    public void setUp(){

        avl = new AVLTree();
        binario = new ArbolBinario();
        //Dragones a introducir
        dragones = new Dragon[5];
        for(double i=0;i<dragones.length;i++) {
            Dragon newDragon = DragonFactory.getDragon("A",0,0,"nom", null);
            newDragon.setEdad((int)((i+20)));
            dragones[(int)i] = newDragon;
        }

        for(Dragon dragon:dragones){
            avl.insert(dragon);
            binario.insert(null,dragon);
        }

        ageResult = new java.util.LinkedList<>();

        for(Dragon dragon:avl.toList()){
            ageResult.add(dragon.getEdad());
        }

    }

    public void testInsertionAVL(){

        java.util.LinkedList<Integer> expected = new java.util.LinkedList<>();
        expected.add(21);
        expected.add(20);
        expected.add(23);
        expected.add(22);
        expected.add(24);

        for(int j = 0;j<5;j++){
            assertEquals(ageResult.get(j), expected.get(j));
        }
    }

    public void testNodeAVLData(){

        assertEquals(avl.getRoot().getBalance(), 0);
        assertEquals(0, avl.getRoot().getLevel());

    }

    public void testSearchinario(){
        assertNull(binario.iterativeSearch(new DragonA(0,0,"nom",null)));
    }

}
