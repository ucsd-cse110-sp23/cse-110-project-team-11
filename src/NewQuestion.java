//javac -cp ../lib/json-20230227.jar:. NewQuestion.java
//java -cp ../lib/json-20230227.jar:. NewQuestion

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NewQuestion {
    AudioRecorder audioRecorder = new AudioRecorder();
    Whisper whisper = new Whisper();
    ChatGPT chatGPT = new ChatGPT();

    public void newQuestionStart(){
        audioRecorder.startRecording();
    }

    //public void newQuestionEnd() throws IOException, InterruptedException{
    public void newQuestionEnd() {
        audioRecorder.stopRecording();

        String[] argsForWhisper = {"myAudio.mp3"}; //output file of audioRecorder
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
    
}