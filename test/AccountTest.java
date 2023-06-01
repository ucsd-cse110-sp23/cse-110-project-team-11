import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

class AccountTest{

    private String uri = "TODO";
    

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
        assertThrows(NullPointerException.class,  testCollection.find(new Document("username", username)).first());
    }
}