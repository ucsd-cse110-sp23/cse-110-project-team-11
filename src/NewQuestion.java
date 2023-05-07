//javac -cp ../lib/json-20230227.jar:. NewQuestion.java
//java -cp ../lib/json-20230227.jar:. NewQuestion

import java.io.IOException;

public class NewQuestion {
    Whisper whisper = new Whisper();
    ChatGPT chatGPT = new ChatGPT();
    AudioRecorder audioRecorder = new AudioRecorder();

    public void newQuestionStart(){
        audioRecorder.startRecording();
    }

    public void newQuestionEnd() throws IOException, InterruptedException{
        audioRecorder.stopRecording();

        String[] argsForWhisper = {"recording.wav"}; //output file of audioRecorder
        whisper.main(argsForWhisper);

        String[] argsForChatGPT = {"100", "whisperResult.txt"}; //transcript output file of Whisper
        chatGPT.main(argsForChatGPT);
    }
}
//test to commit