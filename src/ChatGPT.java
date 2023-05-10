//javac -cp ../lib/json-20230227.jar:. ChatGPT.java
//java -cp ../lib/json-20230227.jar:. ChatGPT
//for windows, use ;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT{
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";

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

    public void generateText(String[] args) throws IOException, InterruptedException {
        //set request parameters
        String prompt = args[1];
        String question = loadfile(prompt);
        String questionText = question;
        int maxToxens = Integer.parseInt(args[0]);

        //create request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", questionText);
        requestBody.put("max_tokens", maxToxens);
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

        System.out.println(generatedText);

        File chatGPTResult = new File("chatGPTResult.txt");

        try {
            FileWriter myWriter = new FileWriter(chatGPTResult);
            myWriter.write(generatedText);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ChatGPT chatGPT = new ChatGPT();
        chatGPT.generateText(args);
    }
}