import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class NewQuestion {
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();

    public void newQuestionStart(){
        audioRecorder.startRecording();
    }

    public void newQuestionEnd(JsonStorage storage) throws JSONException, IOException, InterruptedException {
        audioRecorder.stopRecording();
        VoiceCommands vc = new VoiceCommands();
        String whisperArg = "myAudio.mp3";
        String question = whisper.getTranscript(whisperArg);
        JSONObject savedQuestion = new JSONObject();

        if(question.length() != 0) {
            if (vc.callCommands().equals("chatgpt")) {
                chatGPT.chat(question);
                savedQuestion.put("question",chatGPT.getQuestion());
                savedQuestion.put("answer",chatGPT.getAnswer());
            }
            else if (vc.callCommands().equals("non-chatgpt")) {
                String answer = "Task completed!";
                savedQuestion.put("question", question);
                savedQuestion.put("answer", answer);
            }
            else if (vc.callCommands().equals("invalid")){
                String answer = "Please try again with the following accepted commands:\nQuestion\nDelete Prompt\nClear All\nSet Up Email\nCreate Email\nSend Email";
                String newQ = question;
                savedQuestion.put("question", newQ);
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