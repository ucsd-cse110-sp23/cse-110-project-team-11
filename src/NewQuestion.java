import java.io.IOException;
import org.json.JSONException;

public class NewQuestion {
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();
    private boolean isRecording = true;

    public void start(){
        audioRecorder.startRecording();
    }

    public void stop() throws JSONException, IOException, InterruptedException {
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
    
}