import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat.Style;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT{
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";


    public static void savePrompt(String prompt, String answer) {
        // hint 1: use try-catch block
        // hint 2: use FileWriter
        // hint 3 get list of Tasks using this.getComponents()
        //ArrayList<Task> tasks = new ArrayList<Task>();
    
        try{
          FileWriter fileWriter = new FileWriter("question.txt", false);
    
            fileWriter.write(prompt+ "\n");
            fileWriter.write(answer+"\n");
    
          fileWriter.close();
        }
        catch(IOException e){
            System.out.println("Saving Error: " + e.getMessage());
      
        }
      }

    public static void main(String[] args) throws
    IOException, InterruptedException, Exception {
        //set request parameters
 
        String prompt = args[1];
        System.out.println(prompt);
        //String prompt = "What is the smallest country?";
        int maxTokens = Integer.parseInt(args[0]);
        System.out.println(maxTokens);

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        // Create the HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(API_ENDPOINT))
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();

        // Send the request and receive the response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );
        
 
        // Process the response
        String responseBody = response.body();
        
        JSONObject responseJson = new JSONObject(responseBody);

        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");

        System.out.println(generatedText);

        savePrompt(prompt, generatedText);

        
    } 
}