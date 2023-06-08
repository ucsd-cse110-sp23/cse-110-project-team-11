import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;

<<<<<<< HEAD
class ChatGPT implements IChat{
=======
class ChatGPT{
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";
    
    private String question;
    private String answer;
    private int maxTokens = 150;

    /*
     * Sets question
     */
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

    /*
     * Runs question through ChatGPT API and sets the result to this.answer
     */
    public void chat(String questionText) throws IOException, InterruptedException {
        //create request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", questionText);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        //create http client
        HttpClient client = HttpClient.newHttpClient();

        //create request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(API_ENDPOINT))
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(requestBody)))
        .build();

        //send request and receive response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );

        //process response
        String responseBody = response.body();

        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");

        //set the answer!
        setQuestion(questionText);
        setAnswer(generatedText);
    }

    /*
     * Read from file, might be deleted
     */
    // public String loadfile(String fileName) {
    //     String line = "";
<<<<<<< HEAD
=======
    // public String loadfile(String fileName) {
    //     String line = "";
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8
    
    //     try{
    //       BufferedReader reader = new BufferedReader(new FileReader(fileName));
    //       line = reader.readLine();
    //       reader.close();
    //     }
    //     catch(IOException e){
    //         System.out.println("Reading Error: " + e.getMessage());
<<<<<<< HEAD
=======
    //     try{
    //       BufferedReader reader = new BufferedReader(new FileReader(fileName));
    //       line = reader.readLine();
    //       reader.close();
    //     }
    //     catch(IOException e){
    //         System.out.println("Reading Error: " + e.getMessage());
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8
      
    //     }
    //     return line;
    // }
<<<<<<< HEAD
=======
    //     }
    //     return line;
    // }
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8

    // public static void main(String[] args) throws IOException, InterruptedException {
    //     ChatGPT chatGPT = new ChatGPT();
    //     Whisper whisper = new Whisper();
    //     String file = "myAudio.mp3";

<<<<<<< HEAD
    //     /*
    //      * Sets question from .txt file (might be deleted), chat() sets the answer
    //      * and question, getAnswer() returns the result
    //      */
    //     chatGPT.setQuestion(whisper.getTranscript(file));
    //     chatGPT.chat(chatGPT.getQuestion());
    // }
}
=======
        /*
         * Sets question from .txt file (might be deleted), chat() sets the answer
         * and question, getAnswer() returns the result
         */
        chatGPT.setQuestion(whisper.getTranscript(file));
        chatGPT.chat(chatGPT.getQuestion());
    }
}
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8
