import java.io.IOException;
import org.json.JSONException;

public class NewQuestion {
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();
    private boolean isRecording = true;

    /*
     * Start recording on AudioRecorder object
     */
    public void start(){
        audioRecorder.startRecording();
    }

    /*
     * Make sure AudioRecorder object is not recording anymore and
     * stop recording on AudioRecorder object
     */
    public void stop() throws JSONException, IOException, InterruptedException {
        while (isRecording) {
            isRecording = audioRecorder.isRecording();
            Thread.sleep(25);
        }
        audioRecorder.stopRecording();
    }

    /*
     * Display ChatGPT result with chat()
     */
    public void display() throws JSONException, IOException, InterruptedException {
        String whisperArg = "myAudio.mp3";
        String question = whisper.getTranscript(whisperArg);
        chatGPT.chat(question); 
    }

    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        NewQuestion q = new NewQuestion();
        q.start();
        q.stop();
        q.display();
    }
}