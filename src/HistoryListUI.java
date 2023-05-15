import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class represents the user interface for the History List application.
 * It uses a JList to display the past questions and a JTextArea to display
 * the corresponding answer when a question is selected.
 */
public class HistoryListUI {
    // Instance variables for holding the model and GUI components
    private HistoryListModel model; 
    private JList<String> questionList;  
    private JTextArea questionTextArea;  
    private JTextArea answerTextArea;  
    private DefaultListModel<String> dlm;
    private JPanel historyPanel;  

    /**
     * Constructor for creating a new HistoryListUI.
     * It initializes the instance variables and sets up the GUI components.
     */
    public HistoryListUI(HistoryListModel model, JTextArea answerArea, JTextArea questionArea) throws IOException {
        this.model = model;
        this.dlm = new DefaultListModel<String>();
        this.dlm.addAll(model.getPastQuestions());
        this.questionList = setList();
        addListener();
        this.historyPanel = new JPanel();
        this.answerTextArea = answerArea;
        this.questionTextArea = questionArea;
        setHistoryPanel();
    }

    /**
     * This method sets up the JList for displaying the past questions.
     */
    public JList<String> setList() {
        JList<String> list = new JList<String>(dlm);
        list.setPreferredSize(new Dimension(150, 2000));
        return list;
    }

    /**
     * This method adds a listener to the JList for handling selection events.
     */
    public void addListener() {
        questionList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                questionListValueChanged(event);
            }
            
        });
    }

    /**
     * This method sets up the main panel for displaying the history.
     */
    public void setHistoryPanel() {
        JScrollPane scrollPane = new JScrollPane(questionList);
        scrollPane.setPreferredSize(new Dimension(200, 600)); 
        historyPanel.add(scrollPane);
        historyPanel.add(answerTextArea);
        setAnswerArea();
    }

    /**
     * This method sets up the JTextArea for displaying the answer.
     */
    public void setAnswerArea() {
        answerTextArea.setColumns(20);
        answerTextArea.setRows(50);
        answerTextArea.setPreferredSize(new Dimension(200, 600));
    }

    /**
     * This method sets up the JTextArea for displaying the question.
     */
    public void setQuestionArea() {
        questionTextArea.setColumns(20);
        questionTextArea.setRows(50);
        answerTextArea.setPreferredSize(new Dimension(200, 400));
    }

    /**
     * This method handles the selection event for the JList.
     * It displays the corresponding answer when a question is selected.
     */
    private void questionListValueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            String question = questionList.getSelectedValue();
            int queryIdx = model.getPastQuestions().indexOf(question);
            System.out.println("queryIdx: " + queryIdx);

            if (queryIdx == -1) {
                answerTextArea.setText("");
            } else {
                answerTextArea.setText(model.getPastAnswers().get(queryIdx));
            }
        }
    }

    /**
     * This method deletes a specific entry from the history.
     */
    public void deleteEntry() {
        int selectedIndex = questionList.getSelectedIndex();
        if (selectedIndex != -1) {
            model.deleteEntry(selectedIndex);
            dlm.remove(selectedIndex);
            questionList.repaint();
        }
    }

    /**
     * This method clears all the history.
     */
    public void clearHistory() {
        model.clearHistory();
        for (int i = 0; i < questionList.getModel().getSize(); i++) {
            dlm.remove(i);
        }
        questionList.repaint();
    }

    /**
     * This method refreshes the history panel.
     */
    public void refresh() {
        model.refresh();
        historyPanel.validate();
        historyPanel.repaint();
    }

    // Uncomment the following lines to run this program standalone
    /*
    public static void main(String args[]) throws IOException {
        JFrame f = new JFrame();
        JsonStorage history = new JsonStorage("historyPrompt.json");
        HistoryListModel model = new HistoryListModel(history);
        HistoryListUI list = new HistoryListUI(model, new JTextArea(), new JTextArea());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(list.getHistoryPanel());
        f.pack();
        f.setVisible(true);
    }
    */
}

