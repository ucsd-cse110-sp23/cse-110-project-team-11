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


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Sets up history prompt panel on main frame of the app
 */
public class HistoryList {
    ArrayList<String> pastQuestions;
    ArrayList<String> pastAnswers;
    JList<String> questionList;
    JTextArea questionTextArea;
    JTextArea answerTextArea;
    DefaultListModel<String> dlm;
    JsonStorage jsonStorage;


    // create a panel that contains the list, then put the panel in frame?
    JPanel historyPanel;

    // transition in UI screens. For example, after i asked a new question, and the 
    // question and answer are displayed. but then i want to click on a past question
    // and the page should refresh and give the new result

    // addd clearPage() function that erases all the current content on the panel.

    public HistoryList(String questionFile, String answerFile, JTextArea answerArea) {
        // read file into arraylist
        this.historyPanel = new JPanel();
        this.questionTextArea = new JTextArea();
        this.answerTextArea = answerArea;
        this.pastQuestions = loadFile(questionFile);
        this.pastAnswers = loadFile(answerFile);
        this.questionList = setList();
        this.dlm = new DefaultListModel<String>();
        addListener();
        setHistoryPanel();

        JButton clearAllButton = new JButton("Clear All");
        JButton deleteButton = new JButton("Delete");

        historyPanel.add(clearAllButton);
        historyPanel.add(deleteButton);

    }



    private ArrayList<String> loadFile(String fileName) {
        ArrayList<String> list = new ArrayList<String>();
        try{
            FileReader inFile = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(inFile);
            String line;
            while((line = reader.readLine()) != null) {
                // prepend to list so questions show up from newest to oldest
                list.add(0, line);
            }
            reader.close();
        }
        catch(Exception e){
            System.out.println("loadFile() failed");
        };

        return list;
    }

    public JPanel getHistoryPanel() {
        return this.historyPanel;
    }

    public JTextArea getAnswerArea() {
        return this.answerTextArea;
    }

    public JList<String> setList() {
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        dlm.addAll(pastQuestions);
        JList<String> list = new JList<String>(dlm);
        return list;
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


    public void newEntry(String question, String answer) {
        this.pastQuestions.add(0, question);
        this.pastQuestions.add(0, answer);
    }

    // public static void main(String args[]) {
    //     JFrame f = new JFrame();
    //     HistoryList list = new HistoryList("question.txt", "answer.txt");
    //     f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //     f.add(list.getHistoryPanel());
    //     f.pack();
    //     f.setVisible(true);
    // }
    
}
