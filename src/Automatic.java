import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import org.bson.Document;

public class Automatic {
    private final String URL = "http://localhost:8100/";
    public Automatic() {
        try {
            String email;
            String password;

            File file = new File("account.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            email = reader.readLine();
            password = reader.readLine();
            reader.close();
    
            
            String query = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL(URL + "?" + query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
              new InputStreamReader(conn.getInputStream())
            );
            String response = in.readLine();
            StringBuilder responseBody = new StringBuilder();
            
            if (response.equals("login successfully")) {
              String line;
              while ((line = in.readLine()) != null) {
                    responseBody.append(line);
              }
              in.close();
              new AppFrame(Document.parse(responseBody.toString()));
          } }
          catch (Exception ex) {
            //

          }
          
    }

}