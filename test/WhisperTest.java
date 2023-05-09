import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class WhisperTest {

    @Test
    void testWhisper() {
        Whisper whisper = new Whisper();
        String[] args = new String[]{"recording.mp3"};

        File outputFile = new File("whisperResult.txt");
        assertTrue(outputFile.exists());
        
        try {
            String content = new String(Files.readAllBytes(Paths.get("whisperResult.txt")));
            assertEquals("Hello world!", content.trim());
        } catch (IOException e) {
            fail("Unable to read the content of whisperResult.txt");
        }
    }
}
