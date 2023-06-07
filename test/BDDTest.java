import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BDDTest {
    private VoiceCommandsMock vcm;
    private ArrayList<JSONObject> hpm;
    private JsonStorage js;

    @BeforeEach
    public void setup() throws IOException {
        vcm = new VoiceCommandsMock();
        hpm = new ArrayList<JSONObject>();
        js = new JsonStorage("historyPromptMock.json");
    }

    @Test
    /**
     * Tests BDD Scenario 3 from US4
     */
    public void testUS4() throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("question", "answer");
        hpm.add(jo);

        js.setHistoryPrompt(hpm);

        js.writeJson("historyPromptMock.json");

        // vcm.clearAll();

        assertEquals(1, hpm.size());
        
        /**
         * create a new historyprompt list
         * * add JSONObject 
         * call clear all from voice commands (mock version)
         * check to see that list size == 1
         */
    }
}
