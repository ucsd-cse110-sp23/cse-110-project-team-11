// import org.json.JSONObject;
// import org.junit.jupiter.api.Test;
// import java.io.IOException;

// import static org.junit.jupiter.api.Assertions.*;

// class JsonStorageTest {
//     /*
//      * Test constructor
//      */
// //     @Test
// //     void testConstructor() throws IOException {
// //         JsonStorage js = new JsonStorage("historyPrompt.json");
// //         assertEquals(0, js.getHistoryPrompt().size());
// //     }

//     /*
//      * Test getQuestion()
//      */
//     @Test
//     void testGetQuestion() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();
//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         js.addPrompt(eg1);
//         js.writeJson("historyPrompt.json");

//         assertEquals("Q1", js.getQuestion(0));
//         assertEquals(1, js.getHistoryPrompt().size());
        
//         // test array index out of bounds error?
//     }

//     /*
//      * Test getAnswer()
//      */
//     @Test
//     void testGetAnswer() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();
//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         js.addPrompt(eg1);
//         js.writeJson("historyPrompt.json");

//         assertEquals("A1", js.getAnswer(0));
//         assertEquals(1, js.getHistoryPrompt().size());
        
//         // test array index out of bounds error?
//     }

//     /*
//      * Test getIndex()
//      */
//     @Test
//     void testGetIndex() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();

//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         js.addPrompt(eg1);
//         js.writeJson("historyPrompt.json");

//         assertEquals(0, js.getIndex(eg1));
//     }

//     /*
//      * Test findPrompt()
//      */
//     @Test
//     void testFindPrompt() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();

//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         js.addPrompt(eg1);
//         js.writeJson("historyPrompt.json");

//         JSONObject ans = new JSONObject();
//         ans.put("question", "Q1");
//         ans.put("answer", "A1");

//         assertEquals(ans.toString(0), (js.findPrompt(0)).toString(0));
//         assertEquals(null, js.findPrompt(2));
//     }

//     /*
//      * Test addPrompt()
//      */
//     @Test
//     void testAddPrompt() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();
//         JSONObject eg2 = new JSONObject();

//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         eg2.put("question", "Q2");
//         eg2.put("answer", "A2");

//         js.addPrompt(eg1);
//         js.addPrompt(eg2);
//         js.writeJson("historyPrompt.json");

//         assertEquals("Q1", js.getQuestion(0));
//         assertEquals("A1", js.getAnswer(0));
//         assertEquals("Q2", js.getQuestion(1));
//         assertEquals("A2", js.getAnswer(1));
        
//         assertEquals(2, js.getHistoryPrompt().size());
//     }

//     @Test
//     void testRemovePrompt() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();

//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         js.addPrompt(eg1);
//         js.writeJson("historyPrompt.json");

//         js.removePrompt(0);
//         js.writeJson("historyPrompt.json");

//         assertEquals(0, js.getHistoryPrompt().size());
//     }

//     @Test
//     void testClearPrompt() throws IOException {
//         JsonStorage js = new JsonStorage("historyPrompt.json");
//         JSONObject eg1 = new JSONObject();
//         JSONObject eg2 = new JSONObject();

//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");

//         eg1.put("question", "Q1");
//         eg1.put("answer", "A1");

//         eg2.put("question", "Q2");
//         eg2.put("answer", "A2");

//         js.addPrompt(eg1);
//         js.addPrompt(eg2);
//         js.writeJson("historyPrompt.json");

//         js.clearPrompt();
//         js.writeJson("historyPrompt.json");
//         assertEquals(0, js.getHistoryPrompt().size());
//     }
// }
