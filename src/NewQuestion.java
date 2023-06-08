import java.io.IOException;

import javax.swing.JList;
import javax.swing.JTextArea;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

public class NewQuestion {
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();
    private JTextArea answer;
    private JTextArea question;
    private JsonStorage js;
    private HistoryList hl;
    private JList<String> list;
    private EmailStorage store;
    private String user_email;

    public NewQuestion(JTextArea answerText, JTextArea questionText, JsonStorage storage, HistoryList hl, JList<String> historyList, EmailStorage store, String user_email) {
        this.answer = answerText;
        this.question = questionText;
        this.js = storage;
        this.hl = hl;
        this.list = historyList;
        this.store = store;
        this.user_email = user_email;
    }

    public void newQuestionStart(){
        audioRecorder.startRecording();
    }

    public void newQuestionEnd(JsonStorage storage) throws JSONException, IOException, InterruptedException {
        audioRecorder.stopRecording();
        VoiceCommands vc = new VoiceCommands(answer, question, storage, hl, whisper, list, store, user_email);
        String whisperArg = "myAudio.mp3";
        String question = whisper.getTranscript(whisperArg);
        JSONObject savedQuestion = new JSONObject();

        chatGPT.chat(question);

        if(question != null && !question.equals("")) {
            String isGpt = vc.callCommands(chatGPT);
            if (isGpt.equals("chatgpt")) {
                savedQuestion.put("question",chatGPT.getQuestion());
                savedQuestion.put("answer",chatGPT.getAnswer());
            }
            else if (isGpt.equals("non-chatgpt")) {
                String answer = "Task completed!";
                savedQuestion.put("question", question);
                savedQuestion.put("answer", answer);
            }
            else if (isGpt.equals("invalid")){
                String answer = "Please try again with the following accepted commands:\nQuestion\nDelete Prompt\nClear All\nSet Up Email\nCreate Email\nSend Email";
                savedQuestion.put("question", question);
                savedQuestion.put("answer", answer);
            }
        } 
        
        else {
            String answer = "Invalid input. Please try again with the following accepted commands:\nQuestion\nDelete Prompt\nClear All\nSet Up Email\nCreate Email\nSend Email";
            question = "Invalid input";
            savedQuestion.put("question", question);
            savedQuestion.put("answer", answer);
        }
        
        storage.addPrompt(savedQuestion);

    }
}