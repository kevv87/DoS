package pruebas;
import junit.framework.*;
import utils.LinkedList;

public class LinkedListTests extends TestCase{

    LinkedList<String> resultado= new LinkedList<>();

    public LinkedListTests(String name){
        super(name);
    }

    public void setUp(){
        resultado.add("hola");
        resultado.add("hey");
    }

    public void testAdd(){
        assertEquals("hey", resultado.getInicio().getSiguiente().getElemento());
    }

    public void testSearch(){
        assertTrue(resultado.isIn("hey"));
    }



}
