import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class WisperTest {
    Whisper whisper = new IWhisper();

    @Test
    void raisetest() throws IOException {
        String transcript = whisper.getTranscript(null);
        assertEquals("This is a test transcript",transcript);
    }
}
