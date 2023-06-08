import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BDDTest {
    private VoiceCommandsMock vcm;
    private ArrayList<JSONObject> hpm;
    private JsonStorageMock jsm;
    private ChatGPTMock cgptm;

    @BeforeEach
    public void setup() throws IOException {
        vcm = new VoiceCommandsMock();
        hpm = new ArrayList<JSONObject>();
        jsm = new JsonStorageMock("historyPromptMock.json");
        cgptm = new ChatGPTMock();
    }

    @Test
    /**
     * Tests BDD Scenario 3 from US4
     */
    public void testUS4() throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("question", "answer");
        hpm.add(jo);

        jsm.setHistoryPrompt(hpm);

        jsm.writeJson("historyPromptMock.json");

        // vcm.clearAll();

        assertEquals(1, hpm.size());
        
        /**
         * create a new historyprompt list
         * * add JSONObject 
         * call clear all from voice commands (mock version)
         * check to see that list size == 1
         */
    }
    
    @Test
    public void testUS8() throws IOException, JSONException, InterruptedException {
        cgptm.setQuestion("Create email to Rena about the upcoming holiday.");
        cgptm.setAnswer("Dear Rena,\nHolidays coming soon!\nBest regards,\nHelen Keller");

        String q = cgptm.getQuestion();
        String a = cgptm.getAnswer();
        String displayName = "Helen Keller";

        //test voicecommands
        assertEquals("chatgpt", vcm.callCommands(q));

        //test email is created by last line is display name (Helen Keller)
        assertTrue(a.endsWith(displayName));
    }

    @Test
    public void testUS3() {
        File file = new File("remembered.txt");

        if (file.exists()) {
            assertTrue(file.exists());

            try {
                Automatic automatic = new Automatic();
            } catch (Exception e) {
                fail("Class not called");
            }
        } else {
            assertFalse(file.exists());

            try {
                LoginUI serverUI = new LoginUI();
            } catch (Exception e) {
                fail("Class not called");
            }
        }
    }
}