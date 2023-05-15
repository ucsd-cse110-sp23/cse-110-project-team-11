import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonStorage {
    
    private ArrayList<JSONObject> historyPrompt;

    //constructor
    public JsonStorage(String fileName) throws IOException {
        historyPrompt = new ArrayList<JSONObject>();
        File file = new File(fileName);
        if(file.exists()){
            readJson(fileName);
        }
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
    

    public static void main(String[] args) throws IOException {
        JsonStorage js = new JsonStorage("historyPrompt.json");
        JSONObject eg1 = new JSONObject();
        eg1.put("question", "What is your name?");
        eg1.put("answer", "My name is John.");
        js.addPrompt(eg1);
        JSONObject eg2 = new JSONObject();
        eg2.put("question", "What is your age?");
        eg2.put("answer", "I am 20 years old.");
        js.addPrompt(eg2);
        // js.writeJson("historyPrompt.json");
        // System.out.println(js.getQuestion(0));
        // //System.out.println(type(js.getAnswer(0)));
        // System.out.println(js.getAnswer(0));
        // System.out.println(js.getQuestion(1));
        // System.out.println(js.getAnswer(1));
        //js.removePrompt(0);
        // System.out.println(js.getQuestion(0));
        // System.out.println(js.getAnswer(1));
        // System.out.println(js.getAnswer(2));
        // System.out.println(js.getAnswer(12));
        //js.clearPrompt();
        js.writeJson("historyPrompt.json");


    }
}
