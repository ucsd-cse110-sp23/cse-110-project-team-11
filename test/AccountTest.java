import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

class AccountTest{

    static String uri = "mongodb://emavila:<Mariel.Tijuana24>@ac-jutyc4z-shard-00-00.9o3jutl.mongodb.net:27017,ac-jutyc4z-shard-00-01.9o3jutl.mongodb.net:27017,ac-jutyc4z-shard-00-02.9o3jutl.mongodb.net:27017/?ssl=true&replicaSet=atlas-mzphgv-shard-0&authSource=admin&retryWrites=true&w=majority";
    static AddAccount testAccount;
    static MongoCollection<Document> testCollection;

    @BeforeAll
    void setUp(){
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            //create database and collection in website
            MongoDatabase testDB = mongoClient.getDatabase("mock_accounts"); 
            MongoCollection<Document> testCollection = testDB.getCollection("mock_users");
            AddAccount testAccount = new AddAccount();
            testAccount.acc = testDB;
            testAccount.collection = testCollection;
        }
    }

    @Test
    void createValidAccountTest(){
        String username = "Jose";
        String password = "xyz";
        testAccount.createAccount(username, password);
        assertFalse(testCollection.find(new Document("username", username)).first().isEmpty());
    }

    @Test
    void createInvalidAccountTest(){
        String username = "Jose";
        String password = "xyz";
        testAccount.createAccount(username, password);
        assertNull(testCollection.find(new Document("username", username)).first());
    }
}