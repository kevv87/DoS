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

//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;

import utils.LinkedList;
import utils.Nodo;

/**
 * Clase encargada de manejar las peticiones con el servidor
 * Codigo del cliente tomado de: https://www.oreilly.com/library/view/java-web-services/9780596157708/ch04s05.html
 * Codigo de XML parsing tomado de: https://howtodoinjava.com/jaxb/write-object-to-xml/
 * @author Kevin Zeledon
 * */

/**


class Cliente {
    private static final String url = "http://localhost:9080/Server1_war_exploded/Servlet";

    public Cliente(){
        
    }

    public LinkedList<DragonToSend> sendList(LinkedList<Dragon> dragones){

        LinkedList<DragonToSend> lista_enviar = new LinkedList<>();



        Nodo aux = dragones.getInicio();

        while(aux!=null){
            lista_enviar.add(DragonFactory.getDragon((Dragon)aux.getElemento()));
            aux = aux.getSiguiente();
        }

        try {
            HttpURLConnection conn = null;

            String payload = URLEncoder.encode("dragon", "UTF-8") + "=" +
                    URLEncoder.encode(Objects.requireNonNull(jaxbObjectToXML(lista_enviar)), "UTF-8");


            // POST, en caso de ocuparlo

            // Send the request
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/plain");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            out.writeBytes(payload);
            out.flush();

            String xml = "";
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            while ((next = reader.readLine()) != null){
                xml += next;
            }
            return xmlToObject(xml);

        }
        catch(IOException | JAXBException e) { System.err.println(e);return null; }


        }



    private void send_requests(String data) {
        try {
            HttpURLConnection conn = null;

            String payload = URLEncoder.encode("dragon", "UTF-8") + "=" +
                    URLEncoder.encode(data, "UTF-8");


            // POST, en caso de ocuparlo

            // Send the request
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/plain");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(payload);
            out.flush();
            get_response(conn);




        }
        catch(IOException | NullPointerException | JAXBException e) { System.err.println(e); }
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
    private void get_response(HttpURLConnection conn) throws JAXBException {
        try {
            String xml = "";
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            boolean add = true;
            while ((next = reader.readLine()) != null){
                if(next.startsWith(" <string>")){
                    add = true;
                    next = next.substring(0,1);
                }else if(next.startsWith("</string>")){
                    add=true;
                }
                if(add) xml += next;
            }



        }
        catch(IOException e) { System.err.println(e); }
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

}

*/