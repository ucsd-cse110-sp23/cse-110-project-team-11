import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JTextArea;

import static org.junit.jupiter.api.Assertions.*;

public class VoiceCommandsTest {
    private VoiceCommands voiceCommands;
    private JTextArea answerText;
    private JTextArea questionText;
    private JsonStorage storage;
    private HistoryList hl;
    private Whisper whisper;
    private JList<String> historyList;

    @BeforeEach
    public void setup() throws IOException {
        answerText = new JTextArea();
        questionText = new JTextArea();
        whisper = new Whisper();
        historyList = new JList<>();
        this.storage = storage;
        this.hl = hl;

        voiceCommands = new VoiceCommands(answerText, questionText, storage, hl, whisper, historyList);
    }
    // private VoiceCommands vc;
    // private ChatGPT chatgpt;
    // private Whisper whisper;
    // JTextArea answerArea;
    // JTextArea questionArea;
    // HistoryList hl;
    // JsonStorage js;
    // JList<String> questionList;

    // @BeforeEach
    // public void setUp() throws IOException{
    //     whisper = new Whisper();
    //     vc = new VoiceCommands(answerArea, questionArea, js, hl, whisper, questionList);
    //     chatgpt = new ChatGPT();
    // }

    @Test
    public void testProcessTranscript() {
        String transcript = "This is a test transcript";

        voiceCommands.processTranscript(transcript);

        assertEquals("This", voiceCommands.getFirstWord());
        assertEquals("is", voiceCommands.getSecondWord());
        assertEquals("a", voiceCommands.getThirdWord());
    }

    @Test
    public void testProcessTranscriptQuestion() throws JSONException, IOException, InterruptedException {
        vc.processTranscript("Question who is the current president?");
        String r = vc.callCommands(chatgpt, );
        assertEquals("chatgpt", r);
        assertNotEquals("non-chatgpt", r);
        assertNotEquals("invalid", r);
    }

    @Test
    public void testProcessTranscriptDeletePrompt() {
        String[] invalidResult = vc.processTranscript("Question who is the current president?");
        
        assertEquals("chatgpt", invalidResult);

        String r = vc.callCommands("Delete prompt.");
        
        assertEquals("non-chatgpt", r);
    }

    @Test
    public void testProcessTranscriptClearAll() {
        String invalidResult = vc.callCommands("Question who is the current president?");
        
        assertEquals("chatgpt", invalidResult);

        String r = vc.callCommands("Clear all");
        
        assertEquals("non-chatgpt", r);
    }

    @Test
    public void testProcessTranscriptCreateEmail() {
        String r = vc.callCommands("Create email");
        assertEquals("chatgpt", r);
    }

    @Test
    public void testProcessTranscriptSendEmail() {
        String r = vc.callCommands("Send email");
        assertEquals("non-chatgpt", r);
    }

    @Test
    public void testProcessTranscriptSetUp() {
        String r = vc.callCommands("Set up email");
        assertEquals("non-chatgpt", r);
    }

    @Test
    public void testGetWordsNoTranscript() {
        String r1 = vc.getFirstWord();
        String r2 = vc.getSecondWord();
        String r3 = vc.getThirdWord();
        assertEquals("", r1);
        assertEquals("", r2);
        assertEquals("", r3);
    }
    
    @Test
    public void testGetFirstWordSingleWord() {
        vc.callCommands("Question");
        String r = vc.getFirstWord();
        assertEquals("Question", r);
    }

    @Test
    public void testGetFirstWordMultipleWords() {
        vc.callCommands("Set up email");
        String r = vc.getFirstWord();
        assertEquals("Set", r);
    }
    
    @Test
    public void testGetSecondWordSingleWord() {
        vc.callCommands("Question");
        String r1 = vc.getFirstWord();
        String r2 = vc.getSecondWord();
        assertEquals("Question", r1);
        assertEquals("", r2);
    }

    @Test
    public void testGetSecondWordMultipleWords() {
        vc.callCommands("Set up email");
        String r1 = vc.getFirstWord();
        String r2 = vc.getSecondWord();
        assertEquals("Set", r1);
        assertEquals("up", r2);
    }

    @Test
    public void testGetThirdWordSingleWord() {
        vc.callCommands("Question");
        String r1 = vc.getFirstWord();
        String r3 = vc.getThirdWord();
        assertEquals("Question", r1);
        assertEquals("", r3);
    }

    @Test
    public void testGetThirdWordMultipleWords() {
        vc.callCommands("Set up email");
        String r1 = vc.getFirstWord();
        String r2 = vc.getSecondWord();
        String r3 = vc.getThirdWord();
        assertEquals("Set", r1);
        assertEquals("up", r2);
        assertEquals("email", r3);
    }
}
