import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonStorageTest {
    /*
     * Test constructor
     */
    @Test
    void testConstructor() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        assertEquals(0, jsm.getHistoryPrompt().size());
    }

    /*
     * Test getQuestion()
     */
    @Test
    void testGetQuestion() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();
        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        jsm.addPrompt(eg1);
        jsm.writeJson("historyPrompt.json");

        assertEquals("Q1", jsm.getQuestion(0));
        assertEquals(1, jsm.getHistoryPrompt().size());
    }

    /*
     * Test getAnswer()
     */
    @Test
    void testGetAnswer() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();
        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        jsm.addPrompt(eg1);
        jsm.writeJson("historyPrompt.json");

        assertEquals("A1", jsm.getAnswer(0));
        assertEquals(1, jsm.getHistoryPrompt().size());
    }

    /*
     * Test getIndex()
     */
    @Test
    void testGetIndex() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();

        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        jsm.addPrompt(eg1);
        jsm.writeJson("historyPrompt.json");

        assertEquals(0, jsm.getIndex(eg1));
    }

    /*
     * Test findPrompt()
     */
    @Test
    void testFindPrompt() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();

        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        jsm.addPrompt(eg1);
        jsm.writeJson("historyPrompt.json");

        JSONObject ans = new JSONObject();
        ans.put("question", "Q1");
        ans.put("answer", "A1");

        assertEquals(ans.toString(0), (jsm.findPrompt(0)).toString(0));
        assertEquals(null, jsm.findPrompt(2));
    }

    /*
     * Test addPrompt()
     */
    @Test
    void testAddPrompt() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();
        JSONObject eg2 = new JSONObject();

        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        eg2.put("question", "Q2");
        eg2.put("answer", "A2");

        jsm.addPrompt(eg1);
        jsm.addPrompt(eg2);
        jsm.writeJson("historyPrompt.json");

        assertEquals("Q1", jsm.getQuestion(0));
        assertEquals("A1", jsm.getAnswer(0));
        assertEquals("Q2", jsm.getQuestion(1));
        assertEquals("A2", jsm.getAnswer(1));
        
        assertEquals(2, jsm.getHistoryPrompt().size());
    }

    @Test
    void testRemovePrompt() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();

        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        jsm.addPrompt(eg1);
        jsm.writeJson("historyPrompt.json");

        jsm.removePrompt(0);
        jsm.writeJson("historyPrompt.json");

        assertEquals(0, jsm.getHistoryPrompt().size());
    }

    @Test
    void testClearPrompt() throws IOException {
        JsonStorageMock jsm = new JsonStorageMock("historyPrompt.json");
        JSONObject eg1 = new JSONObject();
        JSONObject eg2 = new JSONObject();

        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");

        eg1.put("question", "Q1");
        eg1.put("answer", "A1");

        eg2.put("question", "Q2");
        eg2.put("answer", "A2");

        jsm.addPrompt(eg1);
        jsm.addPrompt(eg2);
        jsm.writeJson("historyPrompt.json");

        jsm.clearPrompt();
        jsm.writeJson("historyPrompt.json");
        assertEquals(0, jsm.getHistoryPrompt().size());
    }
}
