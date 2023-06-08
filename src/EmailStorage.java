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
    
    private JSONObject email_host;
    //Document user; //genral
    //JSONObject setup; //individualsetp


    //constructor
    public EmailStorage(Document user) throws IOException {
        String infor = user.toJson();
        JSONObject obj = new JSONObject(infor);
        JSONObject obj2 = obj.getJSONObject("email_host");
        this.email_host = obj2;


        // // debugging
        // String infor = user.toJson();
        // //JSONObject obj = new JSONObject(infor);
        // JSONObject obj = new JSONObject(infor);
        // System.out.println("User JSON: " + infor);
        // Object emailHost = obj.get("email_host");
        // System.out.println("Email Host: " + emailHost); // Debugging statement

        // if (emailHost instanceof JSONObject) {
        //     this.email_host = (JSONObject) emailHost;
        // } else {
        //     throw new JSONException("Invalid email_host field in the Document.");
        // }

        // if (obj.has("email_host") && obj.get("email_host") instanceof JSONObject) {
        //     JSONObject obj2 = obj.getJSONObject("email_host");
        //     this.email_host = obj2;
        //     this.email_host = obj2;
        // } else {
        //     throw new JSONException("Invalid email_host field in the Document.");
        // }
        
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


    public String getName(){
        return email_host.getString("firstName") + " "+ email_host.getString("lastName");
    }

    public String getDisplayName(){
        return email_host.getString("displayName");
    }

    public String getEmail(){
        return email_host.getString("email");
    }
    
    public String getSMTP(){
        return email_host.getString("SMTP");
    }

    public String getTLS(){
        return email_host.getString("TLS");
    }

    public boolean empty() {
        JSONObject empty = new JSONObject();
        return email_host == empty;
    }
}