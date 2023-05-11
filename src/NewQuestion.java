import java.io.IOException;
import org.json.JSONException;

public class NewQuestion {
    private static AudioRecorder audioRecorder = new AudioRecorder();
    private static Whisper whisper = new Whisper();
    private static ChatGPT chatGPT = new ChatGPT();

    public void newQuestionStart(){
        audioRecorder.startRecording();
    }

    public void newQuestionStop() throws JSONException, IOException, InterruptedException {
        audioRecorder.stopRecording();
    }
    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        NewQuestion q = new NewQuestion();
        q.newQuestionStart();

        boolean isRecording = true;

        while (isRecording) {
            isRecording = audioRecorder.isRecording();
            Thread.sleep(100);
        }

        q.newQuestionStop();
        String whisperArg = "myAudio.mp3";
        String question = whisper.getTranscript(whisperArg);
        chatGPT.chat(question); 
    }
    
}