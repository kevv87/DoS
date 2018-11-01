package pruebas;
import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;
import conectividad.Cliente;
import junit.framework.*;
import sun.awt.image.ImageWatched;
import utils.LinkedList;


import javax.xml.bind.JAXBException;

import static conectividad.Cliente.jaxbObjectToXML;

public class XmlTests extends TestCase{

    String xml;
    Dragon dragon;
    LinkedList<DragonToSend> lista;

    public XmlTests(String name){
        super(name);
    }


    public void setUp(){
        xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<linkedList>\n" +
                        "    <inicio>\n" +
                        "        <Elemento xsi:type=\"dragonToSend\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "            <alive>false</alive>\n" +
                        "            <edad>1</edad>\n" +
                        "            <name>nom</name>\n" +
                        "            <resistencia>1</resistencia>\n" +
                        "            <tipo>A</tipo>\n" +
                        "            <velocidad_recarga>77</velocidad_recarga>\n" +
                        "            <x>0.0</x>\n" +
                        "            <y>0.0</y>\n" +
                        "        </Elemento>\n" +
                        "        <elemento xsi:type=\"dragonToSend\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "            <alive>false</alive>\n" +
                        "            <edad>1</edad>\n" +
                        "            <name>nom</name>\n" +
                        "            <resistencia>1</resistencia>\n" +
                        "            <tipo>A</tipo>\n" +
                        "            <velocidad_recarga>77</velocidad_recarga>\n" +
                        "            <x>0.0</x>\n" +
                        "            <y>0.0</y>\n" +
                        "        </elemento>\n" +
                        "    </inicio>\n" +
                        "    <tamanio>1</tamanio>\n" +
                        "    <ultimo>\n" +
                        "        <Elemento xsi:type=\"dragonToSend\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "            <alive>false</alive>\n" +
                        "            <edad>1</edad>\n" +
                        "            <name>nom</name>\n" +
                        "            <resistencia>1</resistencia>\n" +
                        "            <tipo>A</tipo>\n" +
                        "            <velocidad_recarga>77</velocidad_recarga>\n" +
                        "            <x>0.0</x>\n" +
                        "            <y>0.0</y>\n" +
                        "        </Elemento>\n" +
                        "        <elemento xsi:type=\"dragonToSend\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "            <alive>false</alive>\n" +
                        "            <edad>1</edad>\n" +
                        "            <name>nom</name>\n" +
                        "            <resistencia>1</resistencia>\n" +
                        "            <tipo>A</tipo>\n" +
                        "            <velocidad_recarga>77</velocidad_recarga>\n" +
                        "            <x>0.0</x>\n" +
                        "            <y>0.0</y>\n" +
                        "        </elemento>\n" +
                        "    </ultimo>\n" +
                        "</linkedList>\n"+
                        "";

        dragon = DragonFactory.getDragon("A",0,0,"nom",null);
        lista = new LinkedList<>();
        lista.add(DragonFactory.getDragon(dragon));
    }



    public void testObjetctToXml(){
        assertEquals(xml, jaxbObjectToXML(lista));
    }

    public void testXmlToObject() throws JAXBException {
        LinkedList<DragonToSend> result = new Cliente().xmlToObject(xml);
        DragonToSend expected = DragonFactory.getDragon(dragon);
        DragonToSend Dresult = new Cliente().xmlToObject(xml).getInicio().getElemento();
        assertEquals(expected.getName(), Dresult.getName());
        assertEquals(expected.getEdad(), Dresult.getEdad());
        assertEquals(expected.getResistencia(), Dresult.getResistencia());
        assertEquals(expected.getTipo(), Dresult.getTipo());
    }

    public void testExcepcion(){
        try{
            new Cliente().xmlToObject("prueba");
            fail("Tuvo que haber tirado excepcion");
        } catch(JAXBException e){
            assertTrue(true);
        }
    }



}
