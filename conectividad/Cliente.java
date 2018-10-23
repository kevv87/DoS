package conectividad;
import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import Actors.factories.dragons.DragonToSend;

import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Clase encargada de manejar las peticiones con el servidor
 * Codigo del cliente tomado de: https://www.oreilly.com/library/view/java-web-services/9780596157708/ch04s05.html
 * Codigo de XML parsing tomado de: https://howtodoinjava.com/jaxb/write-object-to-xml/
 * @author Kevin Zeledon
 * */
class Cliente {
    private static final String url = "http://localhost:9080/Server1_war_exploded/Servlet";

    public static void main(String[ ] args) {
        String dragon_string = jaxbObjectToXML(DragonFactory.getDragon(DragonFactory.getDragon("A",0,0,"nom",null)));
        new Cliente().send_requests(dragon_string);
    }

    private void send_requests(String data) {
        try {
            HttpURLConnection conn = null;

            String payload = URLEncoder.encode("dragon", "UTF-8") + "=" +
                    URLEncoder.encode(data, "UTF-8");


            // POST, en caso de ocuparlo

            // Send the request
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/xml");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(payload);
            out.flush();
            get_response(conn);

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
    private void get_response(HttpURLConnection conn) {
        try {
            String xml = "";
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            while ((next = reader.readLine()) != null)
                xml += next;
            System.out.println("The response:\n" + xml);
        }
        catch(IOException e) { System.err.println(e); }
    }

    private static String jaxbObjectToXML(DragonToSend dragon)
    {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(DragonToSend.class);

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
