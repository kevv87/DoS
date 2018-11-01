package pruebas;
import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;
import conectividad.Cliente;
import junit.framework.*;
import utils.LinkedList;

public class ObjectTransformations extends TestCase{

    Dragon dragon;
    DragonToSend dragontosend;
    LinkedList<Dragon> listadragon = new LinkedList<>();
    LinkedList<DragonToSend> listadragontosend = new LinkedList<>();

    public ObjectTransformations(String name){
        super(name);
    }

    public void setUp(){
        dragon = DragonFactory.getDragon("A",0,0,"nom",null);
        dragontosend = DragonFactory.getDragon(dragon);
        listadragon.add(dragon);
        listadragontosend.add(dragontosend);
    }

    public void testListsDragonToSendToDragon(){
        Dragon result = new Cliente().DragonToSend_to_Dragon(listadragontosend).getInicio().getElemento();
        assertEquals(result.getEdad(), dragon.getEdad());
    }

    public void testDragonToSendtoDragon(){
        assertEquals(dragon.getEdad(), DragonFactory.getDragon(dragontosend).getEdad());
    }

    public void testDragontoDragonToSend(){
        assertEquals(dragontosend.getEdad(), dragon.getEdad());
    }

}
