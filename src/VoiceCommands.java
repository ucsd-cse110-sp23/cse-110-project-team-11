import java.io.IOException;

import org.json.JSONException;

public class VoiceCommands {

    private String question;
    private JsonStorage storage;
    String firstWord;
    String secondWord;
    String thirdWord;
    private String[] sentence;

    public VoiceCommands() throws IOException {
        this.storage = new JsonStorage("historyPrompt.json");
        this.question = storage.getQuestion(storage.getPrompts().size() - 1);
        this.sentence = question.split(" ");
        if(sentence.length == 0) {
            
        }
        else if (sentence.length == 1) {
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

    public String callCommands() throws JSONException, IOException, InterruptedException {
        String value = "invalid";
        System.out.println(firstWord);
        System.out.println(secondWord);
       if (firstWord.equals("Question")) {
            //question();
            value = "chatgpt";
       } else if (firstWord.equals("Delete") && secondWord.equals("prompt")) {
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
        NewQuestion nq = new NewQuestion();
        nq.newQuestionStart();
        nq.newQuestionEnd(storage);
    }

    public void deletePrompt() {

    }

    public void clearAll() {

    }

    public void setUpEmail() {

    }

    public void createEmail() {

    }

    public void sendEmail() {

    }

    public static void main(String[] args) throws IOException {
        VoiceCommands vc = new VoiceCommands();
        System.out.println(vc.firstWord);
        System.out.println(vc.secondWord);
    }
}
