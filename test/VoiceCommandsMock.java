import java.io.IOException;
import org.json.JSONException;

public class VoiceCommandsMock extends VoiceCommands {
    private String transcript;
    private Object JTextArea;
    private JsonStorageMock jsm;

    public VoiceCommandsMock() throws JSONException, IOException {
        super(null, null, null, null, null, null);
        this.jsm = new JsonStorageMock("historyPromptMock.json");
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public void processTranscript(String transcript) throws IOException {
        String[] words = transcript.split(" ");

        if (words.length >= 1) {
            this.firstWord = words[0];
        }
        if (words.length >= 2) {
            this.secondWord = words[1];
        }
        if (words.length >= 3) {
            this.thirdWord = words[2];
        }
    }

    public String callCommands(String transcript) throws JSONException, IOException, InterruptedException {
        String value = "invalid";
        
        processTranscript(transcript);

       if (firstWord.equalsIgnoreCase("Question") || firstWord.equalsIgnoreCase("Question,") || firstWord.equalsIgnoreCase("Question.")) {
            value = "chatgpt";
       } else if (firstWord.equalsIgnoreCase("Delete") && secondWord.equalsIgnoreCase("prompt.")) {
            value = "non-chatgpt";
       } else if (firstWord.equalsIgnoreCase("Clear") && secondWord.equalsIgnoreCase("all.")) {
            value = "non-chatgpt";
       } else if (firstWord.equalsIgnoreCase("Create") && secondWord.equalsIgnoreCase("email")) {
            value = "chatgpt";
       } else if (firstWord.equalsIgnoreCase("Send") && secondWord.equalsIgnoreCase("email.")) {
            value = "non-chatgpt";
       } else if (firstWord.equalsIgnoreCase("Set") && secondWord.equalsIgnoreCase("up") && thirdWord.equalsIgnoreCase("email.")) {
            value = "non-chatgpt";
        } 

        return value;
    }

    @Override
    public String getFirstWord() {
        return firstWord;
    }

    @Override
    public String getSecondWord() {
        return secondWord;
    }

    @Override
    public String getThirdWord() {
        return thirdWord;
    }

    @Override
    public void clearAll() {
        js.clearPrompt();
        try {
            js.writeJson("historyPromptMock.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
>>>>>>>>> Temporary merge branch 2
