import java.io.IOException;

/*
 * Mock class for ChatGPT used for testing purposes
 */
public class MockChat{
    String question;
    String answer;

    public void chat(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // public void chat(String question) throws IOException, InterruptedException {
    //     //this.setQnA(question, answer);
    // }

    public void setQuestion(String question) {
        //
    }

    public void setAnswer(String answer) {
        //
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
}
