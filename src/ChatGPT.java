import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT{
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";
    
    private String question;
    private String answer;
    private int maxTokens = 100;

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
        setAnswer(generatedText);
    }

    /*
     * Read from file, might be deleted
     */
    public String loadfile(String fileName) {
        String line = "";
    
        try{
          BufferedReader reader = new BufferedReader(new FileReader(fileName));
          line = reader.readLine();
          reader.close();
        }
        catch(IOException e){
            System.out.println("Reading Error: " + e.getMessage());
      
        }
        return line;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ChatGPT chatGPT = new ChatGPT();
        Whisper whisper = new Whisper();
        String file = "myAudio.mp3";
        // String prompt = args[0];

        /*
         * Sets question from .txt file (might be deleted), chat() sets the answer
         * and question, getAnswer() returns the result
         */
        // chatGPT.setQuestion(chatGPT.loadfile(prompt));
        chatGPT.setQuestion(whisper.getTranscript(file));
        chatGPT.chat(chatGPT.getQuestion());
        System.out.println(chatGPT.getAnswer());

        /*
         * Saves result to a .txt file
         */
        // File chatGPTResult = new File("chatGPTResult.txt");

        // try {
        //     FileWriter myWriter = new FileWriter(chatGPTResult);
        //     myWriter.write(chatGPT.getAnswer());
        //     myWriter.close();
        // } catch (IOException e) {
        //     System.out.println("An error occurred.");
        // }
    }
}