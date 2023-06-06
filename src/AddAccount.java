//add package?
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;


import static java.util.Arrays.asList;


public class AddAccount {

    static MongoDatabase acc;
    static MongoCollection<Document> collection;
    static String uri = "mongodb://emavila:<Mariel.Tijuana24>@ac-jutyc4z-shard-00-00.9o3jutl.mongodb.net:27017,ac-jutyc4z-shard-00-01.9o3jutl.mongodb.net:27017,ac-jutyc4z-shard-00-02.9o3jutl.mongodb.net:27017/?ssl=true&replicaSet=atlas-mzphgv-shard-0&authSource=admin&retryWrites=true&w=majority";
    //static String uri = "mongodb+srv://emavila%40ucsd.edu:Mariel.Tijuana24@gettingstarted.9o3jutl.mongodb.net/";
    public static void main(String[] args) {
    
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            //create database and collection in website
            MongoDatabase userAccountsDB = mongoClient.getDatabase("user_accounts"); 
            MongoCollection<Document> accountCollection = userAccountsDB.getCollection("accounts");
            acc = userAccountsDB;
            collection = accountCollection;

        }
    }

    void createAccount( String id, String password) {
        if (acc == null) {
            throw new NullPointerException("Call main to initialize database");
        }
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            if(accountExist(id, password)){
                System.out.println("Account already exists");
                //Call UI method for popup window to tell user message
                return;
            }
            Document user = new Document("username", new ObjectId());
            user.append("username", id)
                   .append("password", password);


            collection.insertOne(user);
        }
    }

    boolean accountExist(String id, String password){
        if (acc == null) {
            throw new NullPointerException("Call main to initialize database");
        }

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // find one document with new Document
            Document account1 = collection.find(new Document("username", id)).first();
            return account1.isEmpty();
        }
    }
}