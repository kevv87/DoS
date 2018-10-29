import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static javax.servlet.SessionTrackingMode.URL;

class ClientRC {
    private static final String url = "http://localhost:9080/Server1_war_exploded/Servlet";

    public static void main(String[ ] args) {
        new ClientRC().send_requests();
    }

    private void send_requests() {
        try {
            HttpURLConnection conn = null;

            // POST request to create some Fibonacci numbers.
            List<Integer> nums = new ArrayList<Integer>();
            int a = 8;
            for (int i = 1; i < 15; i++) nums.add(i);
            String payload = URLEncoder.encode("nums", "UTF-8") + "=" +
                    URLEncoder.encode("Helloooo", "UTF-8");

            // Send the request
            conn = get_connection(url, "POST");  // Annade un cierto numero a la lista
            conn.setRequestProperty("accept", "text/xml");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(payload);
            out.flush();
            get_response(conn);

            // GET to test whether POST worked
            conn = get_connection(url+"?num=0", "GET");  //Agarra la posicion 0 de la lista del server
            conn.addRequestProperty("accept", "text/xml");
            conn.connect();
            get_response(conn);




        }
        catch(IOException e) { System.err.println(e); }
        catch(NullPointerException e) { System.err.println(e); }
    }

    private HttpURLConnection get_connection(String url_string, String verb) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(url_string);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(verb);
            conn.setDoInput(true);
            conn.setDoOutput(true);
        }
        catch(MalformedURLException e) { System.err.println(e); }
        catch(IOException e) { System.err.println(e); }
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
} 