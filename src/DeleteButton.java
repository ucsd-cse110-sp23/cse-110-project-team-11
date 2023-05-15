import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteButton extends JPanel {
    private HistoryList historyList;
    private JsonStorage jsonStorage;
    private JList<String> questionList;

    public DeleteButton(HistoryList historyList, JsonStorage jsonStorage, JList<String> questionList) {
        this.historyList = historyList;
        this.jsonStorage = jsonStorage;
        this.questionList = questionList;
        
        JButton clearAllButton = new JButton("Clear All");
        JButton deleteButton = new JButton("Delete");

        clearAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });

        this.add(clearAllButton);
        this.add(deleteButton);
    }

    private void clearAll() {
        historyList.pastQuestions.clear();
        historyList.pastAnswers.clear();
        historyList.dlm.clear();
        historyList.answerTextArea.setText("");
        jsonStorage.clearPrompt();
        try {
            jsonStorage.writeJson("historyPrompt.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void delete() {
        // get selected question
        int selectedIndex = questionList.getSelectedIndex();
        if (selectedIndex != -1) { // if some question is selected
            historyList.deleteEntry();
            
            // remove from the json storage
            jsonStorage.removePrompt(selectedIndex);
            try {
                jsonStorage.writeJson("historyPrompt.json");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}