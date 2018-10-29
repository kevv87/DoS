import dragons.Dragon;
import dragons.DragonFactory;
import dragons.DragonToSend;
import util.Nodo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.http.HTTPException;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;


@WebServlet(name="Servlet", urlPatterns = ("/Servlet"))
public class Servlet extends HttpServlet {
    int nivel = 0;
    double altura = 671;
    double width = 973;
    int ordenamiento = 0;

    /*
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String dragon = request.getParameter("dragon");
        // If no query string, assume client wants the full list
        if (dragon == null){
            send_typed_response(request, response, );
        }
        else {
            try {
                send_typed_response(request, response, dragon);
            }
            catch(NumberFormatException e) {
                send_typed_response(request, response, -1);
            }
        }
    }
    */

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String message = request.getParameter("message");
        String dragon = request.getParameter("dragon");
        String method = request.getParameter("orden");
        if(message!=null){
            Logging.log("info", "Mensaje recibido");
            switch(message){
                case "start":
                    Logging.log("info","Juego iniciado");
                    send_typed_response(request, response, "Copiado");
                    break;
                case "end":
                    Logging.log("info","Juego terminado");
                    nivel = 0;
                    break;
                case "getDragons":
                    util.LinkedList<DragonToSend> dragonToSendLinkedList = new util.LinkedList<>();
                    int cantidad = 10 + 20*nivel;
                    nivel++;
                    int columnas = 1;
                    int dragonsPerColumn = cantidad;
                    while(dragonsPerColumn*76>altura){
                        columnas++;
                        dragonsPerColumn = cantidad/columnas;
                        while(dragonsPerColumn*columnas<cantidad){
                            dragonsPerColumn++;
                        }
                    }
                    double SpawningPointX = width;
                    double SpawningPointY = 0;
                    Random rand = new Random();
                    for(int j = 0; j<columnas;j++){
                        for (int i = 0; i<dragonsPerColumn; i++){
                            Dragon newDragon;
                            newDragon = DragonFactory.getDragon("A", SpawningPointX, SpawningPointY, "probe", null, rand.nextInt(2000));
                            dragonToSendLinkedList.add(DragonFactory.getDragon(newDragon));
                            SpawningPointY+=76;
                        }
                        SpawningPointY=0;
                        SpawningPointX+=76;
                    }
                    while(dragonToSendLinkedList.getTamanio()>cantidad){
                        dragonToSendLinkedList.eliminarUltimo();
                    }
                    Logging.log("info",dragonToSendLinkedList.getTamanio()+" dragones generados y enviados");
                    send_typed_response(request, response, jaxbObjectToXML(dragonToSendLinkedList));
                    break;
            }
        }else if(dragon!=null){
            Logging.log("info","Lista recibida");
            try {
                util.LinkedList<DragonToSend> listaRecibida = xmlToObject(dragon);
                util.LinkedList<DragonToSend> listaAEnviar = new util.LinkedList<>();
            switch (method){
                case "selection":  // Selection por edad
                    Logging.log("info","Realizando selection por edad");
                    listaAEnviar = Sorter.selectionSort(mineToYours(listaRecibida));
                    break;
                case "insertion":  //Insertion por velocidad
                    Logging.log("info","Realizando insertion por velocidad");
                    listaAEnviar = Sorter.insertionSort(mineToYours(listaRecibida));
                    break;
                case "quick":  //Quick por edad
                    Logging.log("info","Realizando quicksort por edad");
                    listaAEnviar = Sorter.quickSort(mineToYours(listaRecibida));
                    break;
                case "binario":  //Binario por familia
                    Logging.log("info", "Realizando acomodo de arbol binario por familia");
                    listaAEnviar = Sorter.arbolBinario(mineToYours(listaRecibida));
                    break;
                case "avl":  //AVL por edad
                    Logging.log("info", "Realizando acomodo de arbol avl por edad");
                    listaAEnviar = Sorter.AVLTree(mineToYours(listaRecibida));
                    break;

            }
            Logging.log("info","Lista ordenada y enviada");
            send_typed_response(request, response, jaxbObjectToXML(listaAEnviar));
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        }else{
             throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public java.util.LinkedList<Dragon> mineToYours(util.LinkedList<DragonToSend> list){

        java.util.LinkedList<Dragon> newlist = new java.util.LinkedList<Dragon>();

        Nodo aux = list.getInicio();

        while(aux!=null){

            newlist.add(DragonFactory.getDragon((DragonToSend)aux.getElemento()));
            aux = aux.getSiguiente();
        }

        return newlist;

    }

    public void doInfo(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doHead(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doOptions(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    private void send_typed_response(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Object data) {


        // If client requests plain text or HTML, send it; else XML.


            send_plain(response, data);

    }

    // For simplicity, the data are stringified and then XML encoded.
    private void send_xml(HttpServletResponse response, Object data) {
        try {
            XMLEncoder enc = new XMLEncoder(response.getOutputStream());
            enc.writeObject(data.toString());
            enc.close();
        }
        catch(IOException e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    private void send_html(HttpServletResponse response, Object data) {
        String html_start =
                "<html><head><title>send_html response</title></head><body><div>";
        String html_end = "</div></body></html>";
        String html_doc = html_start + data.toString() + html_end;
        send_plain(response, html_doc);
    }

    private void send_plain(HttpServletResponse response, Object data) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(data.toString().getBytes());
            out.flush();
        }
        catch(IOException e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * XML To dragons.DragonToSend, codigo tomado de https://stackoverflow.com/questions/5458833/use-jaxb-to-create-object-from-xml-string
     * @author kevv87
     * @param xml String con el xml a convertir
     * @return dragons.DragonToSend instance
     */

    public util.LinkedList<DragonToSend> xmlToObject(String xml) throws JAXBException {

            JAXBContext jaxbContext = JAXBContext.newInstance(util.LinkedList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xml);
            util.LinkedList<DragonToSend> dragons = (util.LinkedList<DragonToSend>) unmarshaller.unmarshal(reader);

            return dragons;

        }

    private static String jaxbObjectToXML(util.LinkedList<DragonToSend> dragon)
    {
        try
        {

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(util.LinkedList.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(dragon, sw);

            //Verify XML Content
            String xmlContent = sw.toString();



            return xmlContent;

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}