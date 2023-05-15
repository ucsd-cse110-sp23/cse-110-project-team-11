// reference: https://stackoverflow.com/questions/14625091/create-a-list-of-entries-and-make-each-entry-clickable
// Lab5 JListExampleApp
// https://stackoverflow.com/questions/3200846/how-to-make-a-scrollable-jlist-to-add-details-got-from-a-joptionpane
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

    // transition in UI screens. For example, after i asked a new question, and the 
    // question and answer are displayed. but then i want to click on a past question
    // and the page should refresh and give the new result

    // addd clearPage() function that erases all the current content on the panel.

    public HistoryList(JsonStorage history, JTextArea answerArea) throws IOException {
        // read file into arraylist
        this.history = history;
        this.prompts = history.getPrompts();
        this.historyPanel = new JPanel();
        this.questionTextArea = new JTextArea();
        this.answerTextArea = answerArea;
        this.pastQuestions = setPastQuestions(prompts);
        this.pastAnswers = setPastAnswers(prompts);
        this.questionList = setList();
        this.dlm = new DefaultListModel<String>();
        addListener();
        setHistoryPanel();

        JButton clearAllButton = new JButton("Clear All");
        JButton deleteButton = new JButton("Delete");

        historyPanel.add(clearAllButton);
        historyPanel.add(deleteButton);

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
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        dlm.addAll(pastQuestions);
        JList<String> list = new JList<String>(dlm);
        return list;
    }


    //Set Up History Panel
    public JPanel getHistoryPanel() {
        return this.historyPanel;
    }

    public JTextArea getAnswerArea() {
        return this.answerTextArea;
    }

    public JList getHistoryList() {
        return this.questionList;
    }
    
    public void setHistoryPanel() {
        // sets up the jlist on a panel
        historyPanel.add(new JScrollPane(questionList));
        historyPanel.add(answerTextArea);
        setAnswerArea();
    }

    public void setAnswerArea() {
        answerTextArea.setColumns(20);
        answerTextArea.setRows(5);
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

        // how to set this in main frame?
        answerTextArea.setText(pastAnswers.get(queryIdx));
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

    // public static void main(String args[]) throws IOException {
    //     JFrame f = new JFrame();
    //     JsonStorage history = new JsonStorage("historyPrompt.json");
    //     HistoryList list = new HistoryList(history, new JTextArea());
    //     f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //     f.add(list.getHistoryPanel());
    //     f.pack();
    //     f.setVisible(true);
    // }
    
}
