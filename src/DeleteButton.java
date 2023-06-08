import javax.swing.*;
import java.io.IOException;

public class DeleteButton extends JPanel {
    private HistoryList historyList;
    private JsonStorage jsonStorage;
    private JList<String> questionList;

    public DeleteButton(HistoryList historyList, JsonStorage jsonStorage, JList<String> questionList) throws IOException {
        this.historyList = historyList;
        this.jsonStorage = jsonStorage;
        this.questionList = questionList;
    }

    public void clearAll() {
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

    public void delete() {
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