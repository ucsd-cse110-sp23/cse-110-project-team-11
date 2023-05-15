<<<<<<< HEAD
//javac -cp ../lib/json-20230227.jar:. NewQuestion.java
//java -cp ../lib/json-20230227.jar:. NewQuestion

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NewQuestion {
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

    public void newQuestionStart(){
        audioRecorder.startRecording();
    }

<<<<<<< HEAD
    //public void newQuestionEnd() throws IOException, InterruptedException{
    public void newQuestionEnd() {
        audioRecorder.stopRecording();

        String[] argsForWhisper = {"recording.mp3"}; //output file of audioRecorder
        try {
            Class<?> class1 = Class.forName("Whisper");
            Method method = class1.getMethod("main", String[].class);
            String[] arguments = argsForWhisper;
            method.invoke(null, (Object) arguments);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String[] argsForChatGPT = {"100", "whisperResult.txt"}; //transcript output file of Whisper
        try {
            Class<?> class1 = Class.forName("ChatGPT");
            Method method = class1.getMethod("main", String[].class);
            String[] arguments = argsForChatGPT;
            method.invoke(null, (Object) arguments);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        NewQuestion q = new NewQuestion();
        q.newQuestionStart();
        q.newQuestionEnd();
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