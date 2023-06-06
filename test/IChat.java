import java.io.IOException;

/*
 * Interface for chatGPT and MockChat to implement
 */
interface IChat {

    public void chat(String queString) throws IOException, InterruptedException;

    public void setQuestion(String question);

    public void setAnswer(String answer);

    public String getQuestion();

    public String getAnswer();
    
}
