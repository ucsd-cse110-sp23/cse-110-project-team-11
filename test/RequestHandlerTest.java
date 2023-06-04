import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.ConnectionString;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RequestHandlerTest {

    private static RequestHandler requestHandler;
    private static MongoDatabase database;
    private final String testEmail = "cse110Team11@gmail.com";
    private final String testPassword = "ucsdCSE110team11";

    @BeforeAll
    public static void setUp() {
        // This is my MongoDB account and password, other people may not be able to use
        // it because you need to add the ip address in my account first
        MongoClient mongoClient = MongoClients.create(new ConnectionString(
                "mongodb+srv://hongyuanjia:jia2011,,@cluster0.nhmc3wv.mongodb.net/?retryWrites=true&w=majority"));
        database = mongoClient.getDatabase("mydb");
        requestHandler = new RequestHandler(database);
    }

    @BeforeEach
    public void clearDatabase() {
        database.getCollection("user_information").deleteMany(new Document());
    }

    @Test
    public void testSearchExistence() {
        assertFalse(requestHandler.searchExistence(testEmail), "Expected user not to exist");

        requestHandler.createAccount(testEmail, testPassword);
        assertTrue(requestHandler.searchExistence(testEmail), "Expected user to exist");
    }

    @Test
    public void testVerifyPassword() {
        assertFalse(requestHandler.verifyPassword(testEmail, testPassword), "Expected password not to match");

        requestHandler.createAccount(testEmail, testPassword);
        assertTrue(requestHandler.verifyPassword(testEmail, testPassword), "Expected password to match");
    }

    @Test
    public void testCreateAccount() {
        assertFalse(requestHandler.searchExistence(testEmail), "Expected user not to exist");

        requestHandler.createAccount(testEmail, testPassword);
        assertTrue(requestHandler.searchExistence(testEmail), "Expected user to exist");
    }

    @Test
    public void testUpdateHistoryPrompt() {
        requestHandler.createAccount(testEmail, testPassword);

        JSONObject historyPrompt = new JSONObject();
        historyPrompt.put("prompt", "This is a test");

        requestHandler.updateHistoryPrompt(testEmail, historyPrompt);

        Document user = database.getCollection("user_information").find(new Document("email", testEmail)).first();
        Document actualHistoryPrompt = (Document) user.get("historyPrompt");

        // Normalize the strings before comparing
        String expected = historyPrompt.toString().replaceAll("\\s+", "");
        String actual = actualHistoryPrompt.toJson().replaceAll("\\s+", "");

        assertEquals(expected, actual, "Expected historyPrompt to match");
    }

    @AfterAll
    public static void tearDown() {
        database.getCollection("user_information").deleteMany(new Document());
    }
}
/*
 * fixed bugs by ChatGPT May 24th version, including but not limit:
 *         String expected = historyPrompt.toString().replaceAll("\\s+", "");
        String actual = actualHistoryPrompt.toJson().replaceAll("\\s+", "");
        fixed the issue that expected and actual output are not the same.
 */