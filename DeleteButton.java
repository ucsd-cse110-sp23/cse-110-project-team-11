import javax.security.auth.kerberos.DelegationPermission;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteButton extends JPanel {
    private HistoryList historyList;
    private JsonStorage jsonStorage;
    private JList<String> questionList;

    public DeleteButton(HistoryList historyList, JsonStorage jsonStorage, JList<String> questionList) throws IOException {
        this.historyList = historyList;
        this.jsonStorage = jsonStorage;
        this.questionList = questionList;
        
        // JButton clearAllButton = new JButton("Clear All");
        // JButton deleteButton = new JButton("Delete");

        // VoiceCommands vc = new VoiceCommands();

        // if (vc.firstWord.equals("Clear") && vc.secondWord.equals("all")) {
        //     clearAll();
        // } else if (vc.firstWord.equals("Delete") && vc.secondWord.equals("prompt.")) {
        //     delete();
        // }

        // clearAllButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         clearAll();
        //     }
        // });

        // deleteButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         delete();
        //     }
        // });

        // this.add(clearAllButton);
        // this.add(deleteButton);
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

    // public void test(String command) throws IOException {
    //     VoiceCommands vc = new VoiceCommands();

    //     if (vc.firstWord.equals("Clear") && vc.secondWord.equals("all")) {
    //         clearAll();
    //     } else if (vc.firstWord.equals("Delete") && vc.secondWord.equals("prompt.")) {
    //         delete();
    //     }
    // }
}