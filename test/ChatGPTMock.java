public class ChatGPTMock {
    private String question;
    private String answer;

    public void setQuestion(String question) {
        this.question = question;
    }    

    /*
     * Sets answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    } 
    
    /*
     * Get question
     */
    public String getQuestion() {
        return question;
    }    

    /*
     * Get answer
     */
    public String getAnswer() {
        return answer;
    } 
}