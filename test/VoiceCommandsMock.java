import java.io.IOException;

import javax.swing.JList;
import javax.swing.JTextArea;

import org.json.JSONException;

public class VoiceCommandsMock extends VoiceCommands {
    private String transcript;
    private Object JTextArea;

    public VoiceCommandsMock() throws JSONException, IOException {
        super(null, null, null, null, null, null);
        this.js = new JsonStorage("historyPromptMock.json");
        //this.hl = new HistoryList(js, answerArea, questionArea);
        // JTextArea answerText = new JTextArea();
        // JTextArea questionText = new JTextArea();
        // JsonStorage storage = new  JsonStorage("historyPromptMock");
        // HistoryList hl = new HistoryList(storage, answerArea, questionArea);
        // Whisper whisper = new Whisper();
        // JList<String> historyList = new JList<String>();
    }

    public void setHl() {
        
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
    public void question() throws JSONException, IOException, InterruptedException {
        // Mock implementation for testing
    }

    @Override
    public void deletePrompt() {
        // Mock implementation for testing
    }

    @Override
    public void clearAll() {
        // hl.pastQuestions.clear();
        // hl.pastAnswers.clear();
        // hl.dlm.clear();
        // hl.answerTextArea.setText("");
        js.clearPrompt();
        try {
            js.writeJson("historyPromptMock.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void setUpEmail() {
        // Mock implementation for testing
    }

    @Override
    public void createEmail(ChatGPT chatGPT) {
        // Mock implementation for testing
    }

    @Override
    public void sendEmail() {
        // Mock implementation for testing
    }
}
