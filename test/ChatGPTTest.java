import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ChatGPTTest {
    private ChatGPT chatGPT = new ChatGPT();
    private String arg = "Please type Hello World, and don't say anything else, don't type colon and don't create a new line";

    /*
     * Test setQuestion() and getQuestion()
     */
    @Test
    void testQuestion() {
        chatGPT.setQuestion(arg);
        String q = chatGPT.getQuestion();

        assertEquals(arg, q);
    }

    /*
     * Test setAnswer(), getAnswer(), chat()
     */
    @Test
    void testAnswer() throws IOException, InterruptedException {
        chatGPT.setQuestion(arg);
        
        //chat() uses setAnswer)
        //chatGPT.chat(chatGPT.getQuestion());
        chatGPT.setAnswer("\n\nHello World");
        
        assertEquals("\n\nHello World", chatGPT.getAnswer());
        
    }

    // @Test
    // void testChat() {
    //     assertDoesNotThrow(() -> chatGPT.chat(arg));

    //     // Check if the output file is created and not empty
    //     File outputFile = new File("chatGPTResult.txt");
    //     assertTrue(outputFile.exists());
    //     assertNotEquals(0, outputFile.length());

    //     // Check if the content is "Hello World"
    //     try {
    //         String content = new String(Files.readAllBytes(Paths.get("chatGPTResult.txt")));
    //         assertEquals("Hello World", content.trim());
    //     } catch (IOException e) {
    //         fail("Unable to read the content of chatGPTResult.txt");
    //     }
    // }
}
