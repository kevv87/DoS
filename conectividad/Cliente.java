package conectividad;
import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import Logger.Logging;

import utils.LinkedList;
import utils.Nodo;

/**
 * Clase encargada de manejar las peticiones con el servidor
 * Codigo del cliente tomado de: https://www.oreilly.com/library/view/java-web-services/9780596157708/ch04s05.html
 * Codigo de XML parsing tomado de: https://howtodoinjava.com/jaxb/write-object-to-xml/
 * @author Kevin Zeledon
 * */
public class Cliente {
    private static final String url = "http://localhost:9080/Server1_war_exploded/Servlet";

    public Cliente(){

    }

    public static void main(String[] args) throws UnsupportedEncodingException, JAXBException {
        new Cliente().getDragons();
    }


    public void sendMessage(String message) throws JAXBException, UnsupportedEncodingException {

        HttpURLConnection conn = null;
        conn = get_connection(url, "POST");
        conn.setRequestProperty("accept", "text/plain");

        String payload = URLEncoder.encode("message","UTF-8") + "=" + URLEncoder.encode(message,"UTF-8");

        send_requests(payload, conn);

        get_response(conn);




    }

    public LinkedList<Dragon> getDragons() throws UnsupportedEncodingException, JAXBException {
        HttpURLConnection conn = null;
        conn = get_connection(url, "POST");
        conn.setRequestProperty("accept", "text/plain");
        String payload = URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode("getDragons","UTF-8");
        send_requests(payload, conn);
        String respuesta = get_response(conn);

        LinkedList<DragonToSend> response = xmlToObject(respuesta);

        Logging.log("info", "Nueva oleada de " + response.getTamanio() + " dragones.");


        return DragonToSend_to_Dragon(response);
    }

    public LinkedList<DragonToSend> sendList(LinkedList<Dragon> dragones, String ordenamiento){

        HttpURLConnection conn = null;
        conn = get_connection(url, "POST");
        conn.setRequestProperty("accept", "text/plain");

        LinkedList<DragonToSend> lista_enviar = new LinkedList<>();
        Nodo aux = dragones.getInicio();

        while(aux!=null){
            lista_enviar.add(DragonFactory.getDragon((Dragon)aux.getElemento()));
            aux = aux.getSiguiente();
        }

        try {
            String payload = URLEncoder.encode("dragon", "UTF-8") + "=" +
                    URLEncoder.encode(Objects.requireNonNull(jaxbObjectToXML(lista_enviar)), "UTF-8")+"&"+
                    URLEncoder.encode("orden", "UTF-8") +"="+ URLEncoder.encode(ordenamiento, "UTF-8");

            // Send the request
            send_requests(payload,conn);

            return xmlToObject(get_response(conn));

        }
        catch(IOException | JAXBException e) { System.err.println(e);return null; }

            /*
            // GET to test whether POST worked
            conn = get_connection(url+payload, "GET");  //Agarra la posicion 0 de la lista del server
            conn.addRequestProperty("accept", "text/xml");
            conn.connect();
            get_response(conn);*/


    }



    private void send_requests(String payload, HttpURLConnection conn) {
        try {
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(payload);
            out.flush();

            /*
            // GET to test whether POST worked
            conn = get_connection(url+payload, "GET");  //Agarra la posicion 0 de la lista del server
            conn.addRequestProperty("accept", "text/xml");
            conn.connect();
            get_response(conn);*/


        }

        catch(IOException | NullPointerException e) { System.err.println(e); }

    }

    private HttpURLConnection get_connection(String url_string, String verb) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(url_string);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(verb);
            conn.setDoInput(true);
            conn.setDoOutput(true);
        } catch(IOException e) { System.err.println(e); }
        return conn;
    }
    private String get_response(HttpURLConnection conn) throws JAXBException {
        try {
            String xml = "";
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            while ((next = reader.readLine()) != null){
                    xml += next;


            }
            System.out.println(xml);
            return xml;
        }
        catch(IOException e) { System.err.println(e); return null;}
    }

    private static String jaxbObjectToXML(LinkedList<DragonToSend> dragon)
    {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(LinkedList.class);

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

    public LinkedList<DragonToSend> xmlToObject(String xml) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(LinkedList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        LinkedList<DragonToSend> result = (LinkedList<DragonToSend>) unmarshaller.unmarshal(reader);

        return result;

    }

    public LinkedList<Dragon> DragonToSend_to_Dragon(LinkedList<DragonToSend> dragonsToSend){
        Nodo aux = dragonsToSend.getInicio();
        LinkedList<Dragon> result = new LinkedList<>();

        while(aux!=null){

            result.add(DragonFactory.getDragon((DragonToSend) aux.getElemento()));
            aux = aux.getSiguiente();

        }

        return result;
    }

}
