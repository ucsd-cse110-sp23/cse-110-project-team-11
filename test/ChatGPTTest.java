import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ChatGPTTest {
    @Test
    void testGenerateText() {
        ChatGPT chatGPT = new ChatGPT();
        String[] args = new String[]{"20", "Please type Hello World, and don't say anything else, don't type colon and don't create a new line"};

        assertDoesNotThrow(() -> chatGPT.generateText(args));

        // Check if the output file is created and not empty
        File outputFile = new File("chatGPTResult.txt");
        assertTrue(outputFile.exists());
        assertNotEquals(0, outputFile.length());

        // Check if the content is "Hello World"
        try {
            String content = new String(Files.readAllBytes(Paths.get("chatGPTResult.txt")));
            assertEquals("Hello World", content.trim());
        } catch (IOException e) {
            fail("Unable to read the content of chatGPTResult.txt");
        }
    }
}
