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

        String whisperArg = "myAudio.mp3";
        String question = whisper.getTranscript(whisperArg);

        chatGPT.chat(question);

        JSONObject savedQuestion = new JSONObject();
        savedQuestion.put("question",chatGPT.getQuestion());
        savedQuestion.put("answer",chatGPT.getAnswer());
        storage.addPrompt(savedQuestion);
    }
}