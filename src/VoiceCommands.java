import java.io.IOException;

import javax.swing.JList;
import javax.swing.JTextArea;

import org.json.JSONException;

public class VoiceCommands {
    private Whisper whisper = new Whisper();
    private String whisperArg = "myAudio.mp3";

    String question;
    String firstWord = "";
    String secondWord = "";
    String thirdWord = "";

    JTextArea answerArea;
    JTextArea questionArea;
    HistoryList hl;
    JsonStorage js;
    JList<String> questionList;

    /*
     * Constructor
     */
    public VoiceCommands(JTextArea answerText, JTextArea questionText, JsonStorage storage, HistoryList hl, Whisper whisper, JList<String> historyList) throws IOException {
        // question = whisper.getTranscript(whisperArg);
        
        this.answerArea = answerText;
        this.questionArea = questionText;
        this.hl = hl;
        this.js = storage;
        this.questionList = historyList;
    }

    /*
     * Set first words to be accessible
     */
    public void processTranscript(String transcript) throws IOException {
        
        transcript = whisper.getTranscript(whisperArg);
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

    /*
     * Call respective commands based on the first words of the transcript
     */
    public String callCommands(ChatGPT chatGPT) throws JSONException, IOException, InterruptedException {
        String value = "invalid";
        
        processTranscript(question);

       if (firstWord.equalsIgnoreCase("Question") || firstWord.equalsIgnoreCase("Question,") || firstWord.equalsIgnoreCase("Question.")) {
            //question();
            value = "chatgpt";
       } else if (firstWord.equalsIgnoreCase("Delete") && secondWord.equalsIgnoreCase("prompt.")) {
            value = "non-chatgpt";
            deletePrompt();
       } else if (firstWord.equalsIgnoreCase("Clear") && secondWord.equalsIgnoreCase("all.")) {
            value = "non-chatgpt";
            clearAll();
       } else if (firstWord.equalsIgnoreCase("Create") && secondWord.equalsIgnoreCase("email")) {
            value = "chatgpt";
            createEmail(chatGPT);
       } else if (firstWord.equalsIgnoreCase("Send") && secondWord.equalsIgnoreCase("email.")) {
            value = "non-chatgpt";
            sendEmail();
       } else if (firstWord.equalsIgnoreCase("Set") && secondWord.equalsIgnoreCase("up") && thirdWord.equalsIgnoreCase("email.")) {
            value = "non-chatgpt";
            setUpEmail();
        } 

        return value;
    }


    public void question() throws JSONException, IOException, InterruptedException {
        // TODO: figure out design pattern
    }

    /*
     * Delete prompt voice command to delete a prompt when selected
     */
    public void deletePrompt() {
        int selectedIndex = questionList.getSelectedIndex();
        if (selectedIndex != -1) { // if some question is selected
            hl.deleteEntry();
            
            // remove from the json storage
            js.removePrompt(selectedIndex);
            hl.refresh();
            try {
                js.writeJson("historyPrompt.json");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
     * Voice command "Clear All" clears everything in history prompt
     */
    public void clearAll() {
        hl.pastQuestions.clear();
        hl.pastAnswers.clear();
        hl.dlm.clear();
        hl.answerTextArea.setText("");
        js.clearPrompt();
        try {
            js.writeJson("historyPrompt.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setUpEmail() {  
        //TODO, not us
    }

    /*
     * Voice command "Create email" asks ChatGPT to create an email and show display name
     */
    public void createEmail(ChatGPT chatGPT) {
        String email = chatGPT.getAnswer();
        System.out.println("before: " + email);
        String displayName = "Helen Keller";
        
        // handle case-sensitive cases
        email = email.replace("[your name]", displayName);
        email = email.replace("[Your name]", displayName);
        email = email.replace("[Your Name]", displayName);
        email = email.replace("[name]", displayName);
        email = email.replace("[Name]", displayName);
        
        chatGPT.setAnswer(email);
    }

    public void sendEmail() {
        //TODO, not us
    }

    /*
     * Get first word
     */
    public String getFirstWord() {
        return firstWord;
    }

    /*
     * Get second word
     */
    public String getSecondWord() {
        return secondWord;
    }

    /*
     * Get third word
     */
    public String getThirdWord() {
        return thirdWord;
    }
}
