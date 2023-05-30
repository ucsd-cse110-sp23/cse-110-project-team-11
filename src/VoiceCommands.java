import java.io.IOException;

import javax.swing.JList;

import org.json.JSONException;

public class VoiceCommands {

    private Whisper whisper;
    private DeleteButton db;
    private String whisperArg;
    String question;
    String firstWord = "";
    String secondWord = "";
    String thirdWord = "";
    private String[] sentence;
    HistoryList historyList;
    JsonStorage jsonStorage;
    JList<String> questionList;

    public VoiceCommands() throws IOException {
        this.whisper = new Whisper();
        whisperArg = "myAudio.mp3";
        question = whisper.getTranscript(whisperArg);

        if (question.length() == 0) {
            this.question = "";
        }
        else {
            this.sentence = question.split(" ");
            if (sentence.length == 1) {
                this.firstWord = sentence[0];
            }
            else if (sentence.length == 2) {
                this.firstWord = sentence[0];
                this.secondWord = sentence[1];
            }
            else {
                this.firstWord = sentence[0];
                this.secondWord = sentence[1];
                this.thirdWord = sentence[2];
            }
        }
    }

    public String callCommands() throws JSONException, IOException, InterruptedException {
        String value = "invalid";
        System.out.println(question);
        System.out.println(sentence);
        System.out.println(firstWord);
        System.out.println(secondWord);
       if (firstWord.equals("Question") || firstWord.equals("Question,") || firstWord.equals("Question.")) {
            //question();
            value = "chatgpt";
       } else if (firstWord.equals("Delete") && secondWord.equals("prompt.")) {
            value = "non-chatgpt";
            deletePrompt();
       } else if (firstWord.equals("Clear") && secondWord.equals("all")) {
            value = "non-chatgpt";
            clearAll();
       } else if (firstWord.equals("Create") && secondWord.equals("email")) {
            //createEmail();
            value = "chatgpt";
       } else if (firstWord.equals("Send") && secondWord.equals("email")) {
            value = "non-chatgpt";
            sendEmail();
       } else if (firstWord.equals("Set") && secondWord.equals("up") && thirdWord.equals("email")) {
            value = "non-chatgpt";
            setUpEmail();
        } 

        return value;
    }


    public void question() throws JSONException, IOException, InterruptedException {
        // TODO: figure out design pattern
    }

    public void deletePrompt() {
        //TODO: call instance
        // db = new DeleteButton(historyList, jsonStorage, questionList);
        // db.delete();
    }

    public void clearAll() {
        //TODO
    }

    public void setUpEmail() {  
        //TODO, not us
    }

    public void createEmail() {
        //TODO, append best regards, etc (on doc)
    }

    public void sendEmail() {
        //TODO, not us
    }

    public static void main(String[] args) throws IOException {
        VoiceCommands vc = new VoiceCommands();
        // vc.setUp();
        System.out.println(vc.firstWord);
        System.out.println(vc.secondWord);
    }
}
