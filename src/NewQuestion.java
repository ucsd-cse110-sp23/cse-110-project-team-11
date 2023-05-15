<<<<<<< HEAD
<<<<<<< HEAD
//javac -cp ../lib/json-20230227.jar:. NewQuestion.java
//java -cp ../lib/json-20230227.jar:. NewQuestion

=======
>>>>>>> 936ef38b4eaa48dbc23a08913235dd8ed065843c
import java.io.IOException;
import org.json.JSONException;

public class NewQuestion {
<<<<<<< HEAD
    Whisper whisper = new Whisper();
    ChatGPT chatGPT = new ChatGPT();
    AudioRecorder audioRecorder = new AudioRecorder();
=======
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class NewQuestion {
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();
>>>>>>> US3
=======
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();
    private boolean isRecording = true;
>>>>>>> 936ef38b4eaa48dbc23a08913235dd8ed065843c

    public void start(){
        audioRecorder.startRecording();
    }

<<<<<<< HEAD
<<<<<<< HEAD
    //public void newQuestionEnd() throws IOException, InterruptedException{
    public void newQuestionEnd() {
=======
    public void stop() throws JSONException, IOException, InterruptedException {
>>>>>>> 936ef38b4eaa48dbc23a08913235dd8ed065843c
        audioRecorder.stopRecording();
    }

    public void recordingStatus() throws InterruptedException{
        while (isRecording) {
            isRecording = audioRecorder.isRecording();
            Thread.sleep(25);
        }
    }

    public void display() throws JSONException, IOException, InterruptedException {
        String whisperArg = "myAudio.mp3";
        String question = whisper.getTranscript(whisperArg);
        chatGPT.chat(question); 
    }

    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        NewQuestion q = new NewQuestion();
        q.start();
        q.recordingStatus();
        q.display();
        q.stop();
    }
    
=======
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
>>>>>>> US3
}