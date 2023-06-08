// reference: https://stackoverflow.com/questions/14625091/create-a-list-of-entries-and-make-each-entry-clickable
// Lab5 JListExampleApp
// https://stackoverflow.com/questions/3200846/how-to-make-a-scrollable-jlist-to-add-details-got-from-a-joptionpane
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JList;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Sets up history prompt panel on main frame of the app
 */
public class HistoryList {
    ArrayList<JSONObject> prompts;
    ArrayList<String> pastQuestions;
    ArrayList<String> pastAnswers;
    JList<String> questionList;
    JTextArea questionTextArea;
    JTextArea answerTextArea;
    DefaultListModel<String> dlm;
    JsonStorage history;


    // create a panel that contains the list, then put the panel in frame?
    JPanel historyPanel;

    public HistoryList(JsonStorage history, JTextArea answerArea, JTextArea questionArea) throws IOException {
        // read file into arraylist
        this.history = history;
        this.prompts = history.getPrompts();
        this.historyPanel = new JPanel();
        //this.questionTextArea = new JTextArea();
        this.answerTextArea = answerArea;
        this.questionTextArea = questionArea;
        this.dlm = new DefaultListModel<String>();
        this.pastQuestions = setPastQuestions(prompts);
        this.pastAnswers = setPastAnswers(prompts);
        this.questionList = setList();
        addListener();
        setHistoryPanel();
    }


    //get list of past questions
    ArrayList<String> setPastQuestions(ArrayList<JSONObject> prompts) {
        ArrayList<String> pastQuestions = new ArrayList<String>();
        for (int i = 0; i < prompts.size(); i++) {
            pastQuestions.add(history.getQuestion(i));
        }
        return pastQuestions;
    }

    //get list of past answers
    ArrayList<String> setPastAnswers(ArrayList<JSONObject> prompts) {
        ArrayList<String> pastAnswers = new ArrayList<String>();
        for (int i = 0; i < prompts.size(); i++) {
            pastAnswers.add(history.getAnswer(i));
        }
        return pastAnswers;
    }


    public JList<String> setList() {
        dlm.addAll(pastQuestions);
        // dlm.fireValueChanged(thiss, 0, dlm.getSize() - 1);
        JList<String> list = new JList<String>(dlm);
        list.setPreferredSize(new Dimension(150, 2000));
        return list;
    }


    //Set Up History Panel
    public JPanel getHistoryPanel() {
        return this.historyPanel;
    }

    public JTextArea getAnswerArea() {
        return this.answerTextArea;
    }

    public JTextArea getQuestionArea() {
        return this.questionTextArea;
    }

    public JList<String> getHistoryList() {
        return this.questionList;
    }

    public void printDlmSize() {
        System.out.println(dlm.getSize());
    }

    public void addEntry(String question, String answer) {
        dlm.addElement(question);
        pastQuestions.add(question);
        pastAnswers.add(answer);
    }
    
    public void setHistoryPanel() {
        JScrollPane scrollPane = new JScrollPane(questionList);
        scrollPane.setPreferredSize(new Dimension(200, 600)); // specify your preferred width and height
        historyPanel.add(scrollPane);
    }

    public void setAnswerArea() {
        answerTextArea.setColumns(20);
        answerTextArea.setRows(50);
        answerTextArea.setPreferredSize(new Dimension(200, 600));
    }

    public void setQuestionArea() {
        questionTextArea.setColumns(20);
        questionTextArea.setRows(50);
        answerTextArea.setPreferredSize(new Dimension(200, 400));
    }

    //Set listener for list
    public void addListener() {
        questionList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                questionListValueChanged(event);
            }
            
        });
    }

    private void questionListValueChanged(ListSelectionEvent event) {
        String question = (String) questionList.getSelectedValue();
        int queryIdx = pastQuestions.indexOf(question);
        
        if (queryIdx == -1) {
            questionTextArea.setText("");
            answerTextArea.setText("");
        } else {
            questionTextArea.setText(question);
            answerTextArea.setText(pastAnswers.get(queryIdx));
        }
    }

    public void deleteEntry() {
        int selectedIndex = questionList.getSelectedIndex();
        if (selectedIndex != -1) {
            pastQuestions.remove(selectedIndex);
            pastAnswers.remove(selectedIndex);
            dlm.remove(selectedIndex);
            questionList.repaint();
        }
    }

    public void clearHistory() {
        for (int i = 0; i < questionList.getModel().getSize(); i++) {
            pastQuestions.remove(i);
            pastAnswers.remove(i);
            dlm.remove(i);
        }
        questionList.repaint();
    }

    public void refresh() {
        prompts = history.getPrompts();
        historyPanel.validate();
        historyPanel.repaint();
    }
}