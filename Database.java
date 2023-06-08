import static com.mongodb.client.model.Filters.eq;

import java.io.File;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Updates.*;

public class Database {
    private String uri = "mongodb+srv://Cluster95779:YV50WnpyZEp9@cluster95779.lfr31so.mongodb.net/?retryWrites=true&w=majority";
    private MongoDatabase database;

    public Database() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cse110");
            this.database = database;
        }
    }
    public MongoDatabase getDatabase(){
        return database;
    }  

    // for the first time user, create a new account
    // with given email and password and default history prompt

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
                    .append("email_host", null)
                    .append("remembered_device", false);

                collection.insertOne(user);
    }

    // update the history prompt of the user
    public void updateHistoryPrompt(String email, JSONObject historyPrompt) {

            MongoCollection<Document> collection = database.getCollection("user_information");


            // update one document
            Bson filter = eq("email", email);
            Bson updateOperation = set("historyPrompt", Document.parse(historyPrompt.toString()));
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
    }

    // update the email host of the user

    //  Unfinished !!!

    public void updateEmailHost(String email, String emailHost) {
         MongoCollection<Document> collection = database.getCollection("user_information");
    }

}


