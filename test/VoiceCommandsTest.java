// <<<<<<< MS2-US2

// =======
// import org.json.JSONException;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import java.io.IOException;
// import static org.junit.jupiter.api.Assertions.*;

// public class VoiceCommandsTest {
//     private VoiceCommandsMock vcm;

//     @BeforeEach
//     public void setup() throws IOException {
//         vcm = new VoiceCommandsMock();
//     }

//     @Test
//     public void testProcessTranscript() throws IOException {
//         String t = "This is a test transcript";

//         vcm.processTranscript(t);

//         assertEquals("This", vcm.getFirstWord());
//         assertEquals("is", vcm.getSecondWord());
//         assertEquals("a", vcm.getThirdWord());
//     }

//     @Test
//     public void testCallCommandsQuestion() throws JSONException, IOException, InterruptedException {
//         String t = "Question who is the current president?";
//         String r = vcm.callCommands(t);

//         assertEquals("chatgpt", r);
//         assertNotEquals("non-chatgpt", r);
//         assertNotEquals("invalid", r);
//     }

//     @Test
//     public void testCallCommandsDeletePrompt() throws IOException, JSONException, InterruptedException {
//         String t = "Question who is the current president?";
//         // vcm.processTranscript(t);
//         String invalidResult = vcm.callCommands(t);

//         assertEquals("chatgpt", invalidResult);

//         String s = "Delete prompt.";
//         // vcm.processTranscript(s);
//         String r = vcm.callCommands(s);
//         assertEquals("non-chatgpt", r);
//     }

//     @Test
//     public void testCallCommandsClearAll() throws JSONException, IOException, InterruptedException {
//         String r = vcm.callCommands("Clear all.");
//         assertEquals("non-chatgpt", r);
//     }

//     @Test
//     public void testCallCommandsCreateEmail() throws JSONException, IOException, InterruptedException {
//         String r = vcm.callCommands("Create email");
//         assertEquals("chatgpt", r);
//     }

//     @Test
//     public void testCallCommandsSendEmail() throws JSONException, IOException, InterruptedException {
//         String r = vcm.callCommands("Send email.");
//         assertEquals("non-chatgpt", r);
//     }

//     @Test
//     public void testCallCommandsSetUp() throws JSONException, IOException, InterruptedException {
//         String r = vcm.callCommands("Set up email.");
//         assertEquals("non-chatgpt", r);
//     }

//     @Test
//     public void testGetWordsNoTranscript() {
//         String r1 = vcm.getFirstWord();
//         String r2 = vcm.getSecondWord();
//         String r3 = vcm.getThirdWord();
//         assertEquals("", r1);
//         assertEquals("", r2);
//         assertEquals("", r3);
//     }
    
//     @Test
//     public void testGetFirstWordSingleWord() throws JSONException, IOException, InterruptedException {
//         vcm.callCommands("Question");
//         String r = vcm.getFirstWord();
//         assertEquals("Question", r);
//     }

//     @Test
//     public void testGetFirstWordMultipleWords() throws JSONException, IOException, InterruptedException {
//         vcm.callCommands("Set up email");
//         String r = vcm.getFirstWord();
//         assertEquals("Set", r);
//     }
    
//     @Test
//     public void testGetSecondWordSingleWord() throws JSONException, IOException, InterruptedException {
//         vcm.callCommands("Question");
//         String r1 = vcm.getFirstWord();
//         String r2 = vcm.getSecondWord();
//         assertEquals("Question", r1);
//         assertEquals("", r2);
//     }

//     @Test
//     public void testGetSecondWordMultipleWords() throws JSONException, IOException, InterruptedException {
//         vcm.callCommands("Set up email");
//         String r1 = vcm.getFirstWord();
//         String r2 = vcm.getSecondWord();
//         assertEquals("Set", r1);
//         assertEquals("up", r2);
//     }

//     @Test
//     public void testGetThirdWordSingleWord() throws JSONException, IOException, InterruptedException {
//         vcm.callCommands("Question");
//         String r1 = vcm.getFirstWord();
//         String r3 = vcm.getThirdWord();
//         assertEquals("Question", r1);
//         assertEquals("", r3);
//     }

//     @Test
//     public void testGetThirdWordMultipleWords() throws JSONException, IOException, InterruptedException {
//         vcm.callCommands("Set up email");
//         String r1 = vcm.getFirstWord();
//         String r2 = vcm.getSecondWord();
//         String r3 = vcm.getThirdWord();
//         assertEquals("Set", r1);
//         assertEquals("up", r2);
//         assertEquals("email", r3);
//     }
// }
// >>>>>>> main
