import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.bson.Document;
//import org.json.simple.*; 
//import org.json.simple.parser.*;
import org.json.*;


public class EmailStorage {
    
    JSONObject email_host;
    //Document user; //genral
    //JSONObject setup; //individualsetp


    //constructor
    public EmailStorage(Document user) throws IOException {
        String infor = user.toJson();
        JSONObject obj = new JSONObject(infor);
        JSONObject obj2 = obj.getJSONObject("email_host");
        this.email_host = obj2;
    }


    //read json file
    public JSONObject getEmailHost() throws IOException {
        //JSONArray info = (JSONArray) new parser.parse(new FileReader(fileName));
        return email_host;
    }

    //add prompt to historyPrompt
    public void addPrompt(String firstName, String lastName, String display, String email, String SMTP, String TLS) {
        email_host.put("firstName", firstName);
        email_host.put("lastName", lastName);
        email_host.put("displayName", display);
        email_host.put("email", email);
        email_host.put("SMTP", SMTP);
        email_host.put("TLS", TLS);
    }
    
}