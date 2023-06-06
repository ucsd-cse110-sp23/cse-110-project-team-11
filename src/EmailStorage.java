import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.*; 
//import org.json.simple.parser.*;
import org.json.*;


public class EmailStorage {
    
    JSONObject user; //genral
    //private JSONObject setup; //individualsetp


    //constructor
    public EmailStorage(String fileName) throws IOException {
        user = new JSONObject();

        File file = new File(fileName);
        if(file.exists()){
            readJson(fileName);
        }
    }

    //get general users information
    public JSONObject getUser() {
        return user;
    }

    //read json file
    public void readJson(String fileName) throws IOException {
        JSONArray info = (JSONArray) parser.parse(new FileReader(filename));
        for(Object person: info){
            JSONObject p = (JSONObject) person;
            
            String firstNameField = (String)p.get("firstName");
            String lastNameField =(String)p.get("lastName");
            String displayNameField = (String) p.get(" displayName");
            String emailField = (String) p.get("email");
            String SMTPField = (String) p.get("SMTP");
            String TLSField = (String) p.get("TLS");

            user.put(emailField, p);
        }
    }

    //write json file
    public void writeJson(String fileName) throws IOException {
        //write json object to file
        String json = user.toString();
        java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
        fileWriter.write(json);
        fileWriter.close();
    }

    //add prompt to historyPrompt
    public void addPrompt(JSONObject obj, String firstName, String lastName, String display, String email, String SMTP, String TLS) {
        JSONObject setup = new JSONObject();
        setup.put("firstName", firstName);
        setup.put("lastName", lastName);
        setup.put("displayName", display);
        setup.put("email", email);
        setup.put("SMTP", SMTP);
        setup.put("TLS", TLS);

        obj.put(email, setup);
    }

    //get historyPrompt
    public JSONObject getUsers() {
        return user;
    }
}