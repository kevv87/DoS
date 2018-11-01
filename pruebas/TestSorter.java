package Tests;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import junit.framework.TestCase;
import logic.Sorter;

import java.util.LinkedList;


public class TestSorter extends TestCase {

    private LinkedList<Dragon> listaPrueba = new LinkedList<>();

    public TestSorter(String name){
        super(name);
    }

    public void testArbolBinario(){

        assertEquals("no ordena por arbol binario",null, Sorter.arbolBinario(listaPrueba).get(0).getPadre());
    }

    public void testArbolAVL(){


        assertEquals("no ordena por arbol AVL",2, Sorter.AVLTree(listaPrueba).get(0).getEdad());
    }

    public void testquickSort(){
        LinkedList<Dragon> listaBoolena = Sorter.quickSort(listaPrueba);
        if (4 == listaBoolena.get(0).getEdad() || 1 == listaBoolena.get(0).getEdad()) {
            assertTrue(true);
        } else{
            fail("no ordenó por quicksortSort");
        }

    }

    public void testselectionSort(){

        LinkedList<Dragon> listaBoolena = Sorter.selectionSort(listaPrueba);
        if (4 == listaBoolena.get(0).getEdad() || 1 == listaBoolena.get(0).getEdad()) {
                assertTrue(true);
            } else{
                fail("no ordenó por selectionSort");
            }
    }

    public void testinsertionSort(){

        LinkedList<Dragon> listaBoolena = Sorter.insertionSort(listaPrueba);
        if (5 == listaBoolena.get(0).getVelocidad_recarga() || 1 == listaBoolena.get(0).getVelocidad_recarga()) {
            assertTrue(true);
        } else{
            fail("no ordenó por insertionSort");
        }
    }

    public void setUp(){

        listaPrueba.add(DragonFactory.getDragon("A",0,0, "dragonA",null));
        listaPrueba.add(DragonFactory.getDragon("C",0,0, "dragonc",listaPrueba.get(0)));
        listaPrueba.add(DragonFactory.getDragon("B",0,0, "dragonB",listaPrueba.get(0)));
        listaPrueba.add(DragonFactory.getDragon("B",0,0, "dragonB",listaPrueba.get(2)));
        listaPrueba.get(0).setEdad(3);
        listaPrueba.get(1).setEdad(2);
        listaPrueba.get(2).setEdad(1);
        listaPrueba.get(3).setEdad(4);
        listaPrueba.get(0).setVelocidad_recarga(3);
        listaPrueba.get(1).setVelocidad_recarga(1);
        listaPrueba.get(2).setVelocidad_recarga(2);
        listaPrueba.get(3).setVelocidad_recarga(5);
    }
}