import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bson.Document;
import org.bson.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonStorage {
    
    private ArrayList<JSONObject> historyPrompt;

    //constructor
    public JsonStorage() {    

    }

    //get history prompt
    public ArrayList<JSONObject> getPrompts() {
        return historyPrompt;
    }

    //read json file
    public void readJson(Document user) throws IOException {
        //get history prompt from the user account
      String infor = user.toJson();

      JSONObject obj = new JSONObject(infor);
      JSONObject obj2 = obj.getJSONObject("history_prompt");
      JSONArray arr = obj2.getJSONArray("historyPrompt");
      if(arr.length() == 0){
        historyPrompt = new ArrayList<JSONObject>();
      }
      else{
        for (int i = 0; i < arr.length(); i++) {
            historyPrompt.add(arr.getJSONObject(i));
        }
      }

       
    }

    //write json file
    public void writeJson(String fileName) throws IOException {
        //create json object
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (int i = 0; i < historyPrompt.size(); i++) {
            arr.put(historyPrompt.get(i));
        }
        obj.put("historyPrompt", arr);

        //write json object to file
        String json = obj.toString();
        java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
        fileWriter.write(json);
        fileWriter.close();
    }

    //get question from historyPrompt
    public String getQuestion(int index) {
        return historyPrompt.get(index).getString("question");
    }

    //get answer from historyPrompt
    public String getAnswer(int index) {
        if (historyPrompt == null) {
            return "Invalid input.";
        }
        if (index < 0 || index >= historyPrompt.size()) {
            return "Error: no answer for question stored.";
        }

        return historyPrompt.get(index).getString("answer");
    }

    //get index 
    public int getIndex(JSONObject prompt) {
        return historyPrompt.indexOf(prompt);
    }

    //find prompt in index
    public JSONObject findPrompt(int index) {
        if (index < 0 || index >= historyPrompt.size()) {
            return null;
        }
        return historyPrompt.get(index);
    }

    //add prompt to historyPrompt
    public void addPrompt(JSONObject prompt) {
        historyPrompt.add(prompt);
    }

    //remove prompt from historyPrompt
    public void removePrompt(int index) {
        if (index < 0 || index >= historyPrompt.size()) {
            return;
        }
        historyPrompt.remove(index);
    }

    //clear historyPrompt
    public void clearPrompt() {
        historyPrompt.clear();
    }

    //get historyPrompt
    public ArrayList<JSONObject> getHistoryPrompt() {
        return historyPrompt;
    }
}
