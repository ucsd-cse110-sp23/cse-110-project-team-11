import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// JPanel derived class to manage "Delete" and "Clear All" functionalities
public class DeleteButton extends JPanel {
    // Instance variables for the components and data this panel will interact with
    private HistoryList historyList;
    private JsonStorage jsonStorage;
    private JList<String> questionList;

    // Constructor that sets up the panel with two buttons "Clear All" and "Delete"
    public DeleteButton(HistoryList historyList, JsonStorage jsonStorage, JList<String> questionList) {
        // Storing the references to the list, JSON storage, and question list for future use
        this.historyList = historyList;
        this.jsonStorage = jsonStorage;
        this.questionList = questionList;
        
        // Creating two buttons for clearing and deleting history
        JButton clearAllButton = new JButton("Clear All");
        JButton deleteButton = new JButton("Delete");

        // Setting up listeners for the buttons, to handle user interaction
        clearAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Clear All" button is pressed, clear all history
                clearAll();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Delete" button is pressed, delete selected history entry
                delete();
            }
        });

        // Adding buttons to this panel
        this.add(clearAllButton);
        this.add(deleteButton);
    }

    // Method to clear all history
    private void clearAll() {
        // Clearing all questions and answers from the history
        historyList.pastQuestions.clear();
        historyList.pastAnswers.clear();
        historyList.dlm.clear();
        historyList.answerTextArea.setText(""); // Clearing the answer text area

        // Clearing all prompts from the JSON storage
        jsonStorage.clearPrompt();
        // Writing the changes to the JSON file
        try {
            jsonStorage.writeJson("historyPrompt.json");
        } catch (IOException ex) {
            // In case of any exception, print the stack trace
            ex.printStackTrace();
        }
    }

    // Method to delete a specific history entry
    private void delete() {
        // Getting the index of the selected question from the list
        int selectedIndex = questionList.getSelectedIndex();
        // Checking if some question is selected
        if (selectedIndex != -1) { 
            // remove from the history list
            // historyList.pastQuestions.remove(selectedIndex);
            // historyList.pastAnswers.remove(selectedIndex);
            // historyList.dlm.remove(selectedIndex);
            // Deleting the selected entry from the history
            historyList.deleteEntry();

            // Removing the corresponding prompt from the JSON storage
            jsonStorage.removePrompt(selectedIndex);
            // Writing the changes to the JSON file
            try {
                jsonStorage.writeJson("historyPrompt.json");
            } catch (IOException ex) {
                // In case of any exception, print the stack trace
                ex.printStackTrace();
            }
        }
    }
}
