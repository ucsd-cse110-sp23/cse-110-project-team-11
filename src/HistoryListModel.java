import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a model for maintaining the history of questions and answers.
 * It uses JSON objects to store the questions and their corresponding answers.
 */
public class HistoryListModel {
    // Instance variables for holding the data
    private ArrayList<JSONObject> prompts;  // List of prompts
    private ArrayList<String> pastQuestions;  // List of past questions
    private ArrayList<String> pastAnswers;  // List of past answers
    private JsonStorage history;  // The JSON storage containing the history

    /**
     * Constructor for creating a new HistoryListModel.
     * It initializes the instance variables and populates the past questions and answers lists.
     */
    public HistoryListModel(JsonStorage history) throws IOException {
        this.history = history;
        this.prompts = history.getPrompts();
        this.pastQuestions = setPastQuestions(prompts);
        this.pastAnswers = setPastAnswers(prompts);
    }

    /**
     * This method populates the list of past questions from the JSON objects.
     */
    ArrayList<String> setPastQuestions(ArrayList<JSONObject> prompts) {
        ArrayList<String> pastQuestions = new ArrayList<String>();
        for (int i = 0; i < prompts.size(); i++) {
            pastQuestions.add(history.getQuestion(i));
        }
        return pastQuestions;
    }

    /**
     * This method populates the list of past answers from the JSON objects.
     */
    ArrayList<String> setPastAnswers(ArrayList<JSONObject> prompts) {
        ArrayList<String> pastAnswers = new ArrayList<String>();
        for (int i = 0; i < prompts.size(); i++) {
            pastAnswers.add(history.getAnswer(i));
        }
        return pastAnswers;
    }

    /**
     * Getter method for the list of past questions.
     */
    public ArrayList<String> getPastQuestions() {
        return this.pastQuestions;
    }

    /**
     * Getter method for the list of past answers.
     */
    public ArrayList<String> getPastAnswers() {
        return this.pastAnswers;
    }

    /**
     * This method deletes a specific entry from the history.
     */
    public void deleteEntry(int index) {
        if (index != -1) {
            pastQuestions.remove(index);
            pastAnswers.remove(index);
        }
    }

    /**
     * This method clears the history of past questions and answers.
     */
    public void clearHistory() {
        pastQuestions.clear();
        pastAnswers.clear();
    }

    /**
     * This method refreshes the list of prompts from the JSON storage.
     */
    public void refresh() {
        this.prompts = history.getPrompts();
    }
}
