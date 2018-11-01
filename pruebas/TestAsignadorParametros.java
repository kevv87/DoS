package Tests;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import junit.framework.TestCase;
import logic.AsignadorParametros;

import java.util.LinkedList;

public class TestAsignadorParametros extends TestCase {

    private AsignadorParametros asignador = new AsignadorParametros();
    private LinkedList<Dragon> listaPrueba = new LinkedList<>();

    public TestAsignadorParametros(String name){
        super(name);
    }

    public void testAsignaPadres(){

        asignador.asignaPadres(listaPrueba);

        int cont = 0;
        while(cont < listaPrueba.size()){
            if (listaPrueba.get(cont).getPadre()==null){
                assertTrue(true);
                break;
            }
            cont++;
        }
        fail("No encontró nodo padre");
    }


    public void testAsignaEdades(){
        asignador.asignaEdad();
        int comparador = listaPrueba.get(0).getEdad();
        int cont = 0;
        while (cont < listaPrueba.size()){
            if (comparador == listaPrueba.get(cont).getEdad()){
                fail("No se asignaron edades disitntas todos los nodos");
            }
            cont++;
        }

    }

    public void testAsignaVelocidades() {
        asignador.asignaVelocidad();
        int comparador = listaPrueba.get(0).getVelocidad_recarga();
        int cont = 0;
        while (cont < listaPrueba.size()) {
            if(listaPrueba.get(0).getVelocidad_recarga() == listaPrueba.get(cont).getVelocidad_recarga()){
            fail("No se asignaron velocidades disitntas todos los nodos");
        }
        cont++;
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

        asignador = new AsignadorParametros(listaPrueba);
    }

}
