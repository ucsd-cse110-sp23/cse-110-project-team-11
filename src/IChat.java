public class IChat extends ChatGPT{
    private String question;
    private String answer;

    @Override
    public void chat(String queString){
        answer = "This is a valid answer";
    }

}