import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;

import org.bson.Document;

import java.io.File;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;


import static com.mongodb.client.model.Updates.*;


public class RequestHandler implements HttpHandler {


    private final MongoDatabase database;
   
   
    public RequestHandler(MongoDatabase database) {
      this.database = database;
    }

    // Check whether an account exist

    public Boolean searchExistence(String email) {
      MongoCollection<Document> collection = database.getCollection("user_information");

      Document user = collection.find(eq("email", email)).first();
      if (user == null) {
          return false;
      }
      return true;
    }


  //Check whether the password is correct
  public Boolean verifyPassword(String email, String password) {
      MongoCollection<Document> collection = database.getCollection("user_information");

      Document user = collection.find(eq("email", email)).first();
      if (user == null) {
          return false;
      }
      else{
          if (user.get("password").equals(password)) {
              return true;
          }
          else {
              return false;
          }
          
      }
  }

  //return the user
    public Document getUser(String email) {
        MongoCollection<Document> collection = database.getCollection("user_information");
    
        Document user = collection.find(eq("email", email)).first();
        return user;
    }


// create an account
  public void createAccount(String email, String password) {
    //mongodb+srv://Cluster95779:YV50WnpyZEp9@cluster95779.lfr31so.mongodb.net/?retryWrites=true&w=majority

    // filed = "user_information" or "remebered_device"
        MongoCollection<Document> collection = database.getCollection("user_information");


        ArrayList<String> devices = new ArrayList<String>();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        obj.put("historyPrompt", arr);


        Document user = new Document("id",devices);
            user.append("email", email)
                .append("password", password)
                .append("history_prompt", Document.parse(obj.toString()))
                .append("email_host", Document.parse(new JSONObject().toString()))
                .append("remembered_device", false);

            collection.insertOne(user);
}

// update the history prompt of the user
public void updateHistoryPrompt(String email, JSONObject historyPrompt) {

        MongoCollection<Document> collection = database.getCollection("user_information");


        // update one document
        Bson filter = eq("email", email);
        Bson updateOperation = set("history_prompt", Document.parse(historyPrompt.toString()));
        collection.updateOne(filter, updateOperation);
}

// update the email host of the user

public void updateEmailHost(String email, JSONObject emailHost) {
     MongoCollection<Document> collection = database.getCollection("user_information");

     // update one document
     Bson filter = eq("email", email);
     Bson updateOperation = set("email_host", Document.parse(emailHost.toString()));
     collection.updateOne(filter, updateOperation);
}










// handle the request from the client

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
          if (method.equals("GET")) {
            response = handleLog(httpExchange);
          } else if (method.equals("POST")) {
            response = handleCreate(httpExchange);
          } else if(method.equals("PUT")){
            handleUpdate(httpExchange);
          }
          else{
            throw new Exception("Not Valid Request Method");
          }
          } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
          }

        //Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }      


    // handle log in method

    private String handleLog(HttpExchange httpExchange) throws IOException {
        String response = "no account or incorrect password";

        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        //if (query != null) {
          String[] pairs = query.split("&");
          if(pairs.length != 2){
            return response;
          }
          String[] keyValue = pairs[0].split("=");
          if (keyValue.length == 2) {
            String email = URLDecoder.decode(keyValue[1], "UTF-8");
            String[] keyValue2 = pairs[1].split("=");
            String password = URLDecoder.decode(keyValue2[1], "UTF-8");
            if(verifyPassword(email, password) == true){
              response = "login successfully";
              Document user = getUser(email);
              response = response  + '\n' + user.toJson();
            }
          }
          
        return response;
        
      }
     
      // handle create account method
    private String handleCreate(HttpExchange httpExchange) throws IOException {
      String response = "The account has existed";
      
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postData = scanner.nextLine();
        String email = postData.substring(
          0,
          postData.indexOf(",")
        ), password = postData.substring(postData.indexOf(",") + 1);
      

        if(! searchExistence(email)){
          createAccount(email, password);
          response = "created successfully";

          // Ask the user if they want to remember this device
          RememberDeviceUI rememberDeviceUI = new RememberDeviceUI(isRemembered -> {
            // Save isRemembered value to the user's info in the database
            // ... code to save to the database ...
        });
        rememberDeviceUI.setVisible(true);
        }
        
        
  
        scanner.close();
     
     
        return response;

      }

      private String handleUpdate(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);

        String postData = scanner.nextLine();
        String email = postData.substring(
          0,
          postData.indexOf(",")
        ), obj = postData.substring(postData.indexOf(",") + 1);

          while(scanner.hasNextLine()){
            obj = obj + scanner.nextLine();
          }


        // String email = scanner.nextLine();

        // StringBuilder inputBuilder = new StringBuilder();
        // String line;
        // while (scanner.hasNextLine()) {
        //     line = scanner.nextLine();
        //     if (line.isEmpty()) {
        //         break; // Stop reading if an empty line is encountered
        //     }
        //     inputBuilder.append(line);
        // }
  
        //String jsonArrayList = inputBuilder.toString();

        // if (jsonArrayList.length() == 0){
        //   return;
        // }
        // JSONArray jsonArray = new JSONArray(jsonArrayList);

        // JSONObject obj = new JSONObject();

        // obj.put("historyPrompt", jsonArray);
        
        
        
        // JSONObject obj2 = new JSONObject(obj);
        // String history = obj2.getString("historyPrompt");
        // if(obj2.has("email_host")){
        //   String emailHost = obj2.getString("email_host");

        //   //updateEmailHost(email, emailHost);
        // }

        // JSONObject historyPrompt = new JSONObject();
        // historyPrompt.put("historyPrompt", history);

        // String response = historyPrompt.toString();

        // updateHistoryPrompt(email, historyPrompt);

        // return response;

        JSONObject update = new JSONObject(obj);


        if(update.has("email_host")){
          JSONObject emailHost = update.getJSONObject("email_host");

          updateEmailHost(email, emailHost);
        }
        if(update.has("history")){
          JSONObject history = update.getJSONObject("history");

          updateHistoryPrompt(email, history);
        }

        String response = update.toString();

        return response;
     
      }
}