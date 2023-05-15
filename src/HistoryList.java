
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
/**
 * HistoryList class is used to display past prompts and their corresponding answers.
 * It includes functions to create and manage a list of past prompts, and to display the
 * corresponding answer when a prompt is selected.
 */

public class HistoryList {
    // Instance variables for holding the data and GUI components
    ArrayList<JSONObject> prompts;  // List of prompts
    ArrayList<String> pastQuestions;  // List of past questions
    ArrayList<String> pastAnswers;  // List of past answers
    JList<String> questionList;  // GUI component for displaying the questions
    JTextArea questionTextArea;  // GUI component for displaying the selected question
    JTextArea answerTextArea;  // GUI component for displaying the answer to the selected question
    DefaultListModel<String> dlm;  // Default list model for managing the list data
    JsonStorage history;  // The JSON storage containing the history

    // GUI components for the panel displaying the history
    JPanel historyPanel;  // The main panel

    // Constructor
    public HistoryList(JsonStorage history, JTextArea answerArea, JTextArea questionArea) throws IOException {
        // Initialize instance variables
        this.history = history;
        this.prompts = history.getPrompts();
        this.historyPanel = new JPanel();
        this.questionTextArea = new JTextArea();
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

    public JList getHistoryList() {
        return this.questionList;
    }

    public void printDlmSize() {
        System.out.println(dlm.getSize());
    }
    
    public void setHistoryPanel() {
        JScrollPane scrollPane = new JScrollPane(questionList);
        scrollPane.setPreferredSize(new Dimension(200, 600)); // specify your preferred width and height
        // sets up the jlist on a panel
        historyPanel.add(scrollPane);
        historyPanel.add(answerTextArea);
        setAnswerArea();
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
        System.out.println("queryIdx: " + queryIdx);

        // how to set this in main frame?
        if (queryIdx == -1) {
            answerTextArea.setText("");
        } else {
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
