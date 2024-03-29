import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonStorageMock {
    
    private ArrayList<JSONObject> historyPrompt;

    //constructor
    public JsonStorageMock(String fileName) throws IOException {
        historyPrompt = new ArrayList<JSONObject>();
        File file = new File(fileName);
        if(file.exists()){
            readJson(fileName);
        }
    }

    public void setHistoryPrompt(ArrayList<JSONObject> arr) {
        this.historyPrompt = arr;
    }

    //get history prompt
    public ArrayList<JSONObject> getPrompts() {
        return historyPrompt;
    }

    //read json file
    public void readJson(String fileName) throws IOException {
        //read json file
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        reader.close();

        //parse json file
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("historyPrompt");
        for (int i = 0; i < arr.length(); i++) {
            historyPrompt.add(arr.getJSONObject(i));
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