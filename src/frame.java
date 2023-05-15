import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;


/*
 * Handle question panel
 */
class QuestionAnswerPanel extends JPanel{
   
    private String question;
    private String answer;
    private JTextArea questionTextArea;
    private JTextArea answerTextArea;
    //private JButton newQuestion;


    public QuestionAnswerPanel(String question, String answer) {
        this.question = question;
        this.answer = answer;


        // create layout for right panel
        setLayout(new GridBagLayout());




        // set question area
        questionTextArea = new JTextArea(question);
        questionTextArea.setRows(3);
        questionTextArea.setEditable(false);
        JScrollPane questionScrollPane = new JScrollPane(questionTextArea);
       


        // set answer area
        answerTextArea = new JTextArea(answer);
        answerTextArea.setRows(8);
        answerTextArea.setEditable(false);
        answerTextArea.setLineWrap(true);
        answerTextArea.setWrapStyleWord(true);
        JScrollPane answerScrollPane = new JScrollPane(answerTextArea);


        // set layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(questionScrollPane, gbc);


        gbc.gridy = 1;
        gbc.weighty = 0.3;       
        gbc.insets = new Insets(5, 10, 10, 10);
        add(answerScrollPane, gbc);


    //     addNewQuestionButton(js, list);
    //     gbc.gridy = 2;
    //     gbc.weighty = 0.3;
    //     gbc.fill = GridBagConstraints.CENTER;
    //     gbc.insets = new Insets(5, 10, 10, 10);
    //     add(this.newQuestion, gbc);
    }


    // public void setQuestion(String question) {
    //     questionTextArea.setText(question);
    // }


    // public void addNewQuestionButton(JsonStorage js, HistoryList list) {
    //   NewQuestionButton newQuestionButton = new NewQuestionButton(this.answerTextArea, js, list);
    //   this.newQuestion = newQuestionButton.getNewQuestionButton();
    // }


    public JPanel getPanel() {
      return this;
    }


    public JTextArea getQuestionArea() {
      return this.questionTextArea;
    }


    public JTextArea getAnswerArea() {
      return this.answerTextArea;
    }
}


// class ButtonPanel extends JPanel{
//     private JButton newQuestion;


//     public ButtonPanel(JTextArea answerTextArea, JsonStorage js, HistoryList list) {
//       setLayout(new GridBagLayout());
//       NewQuestionButton newQuestionButton = new NewQuestionButton(answerTextArea, js, list);
//       this.newQuestion = newQuestionButton.getNewQuestionButton();


//       // set layout
//       GridBagConstraints gbc = new GridBagConstraints();
//       gbc.gridx = 0;
//       gbc.gridy = 0;
//       gbc.fill = GridBagConstraints.RELATIVE;
//       gbc.anchor = GridBagConstraints.PAGE_END;
//       gbc.weightx = 1.0;
//       gbc.weighty = 0.3;
//       gbc.insets = new Insets(10, 10, 5, 10);
//       add(newQuestion, gbc);
//     }


// }


class AppFrame extends JFrame {


    // Colors
    Color gray = new Color(50, 50, 50);
    Color blue = new Color(100, 204, 200);
    Color lightGray = new Color (200,200,200);


   








    /*
     * Main UI Frame
     */
    AppFrame() throws IOException {


      //initailze the data structure
      JsonStorage js = new JsonStorage("historyPrompt.json");


      //adding actions to exit
      addWindowListener(new WindowAdapter() {


        @Override
        public void windowClosing(WindowEvent e){
          try {
            js.writeJson("historyPrompt.json");
          } catch (IOException e1) {
            e1.printStackTrace();
          }
            System.exit(0);
          }
      });
     
     
      //add mocking history prompts
      for (int i = 0; i < 10; i++) {
        JSONObject eg1 = new JSONObject();
        eg1.put("question", "What is your name? What is your age?" + i);
        eg1.put("answer", "My name is John." + i);
        js.addPrompt(eg1);
      }




        //set original question panel
        String newQuestion = "";
        String newAnswer = "";
   

        


        // create a question
        QuestionAnswerPanel question = new QuestionAnswerPanel(newQuestion, newAnswer);
        JPanel questionPanel = question.getPanel();


        HistoryList list = new HistoryList(js, question.getAnswerArea(), question.getQuestionArea());
        JPanel historyPanel = list.getHistoryPanel();
        JList historyList = list.getHistoryList();
        NewQuestionButton newQuestionButton = new NewQuestionButton(question.getAnswerArea(), question.getQuestionArea(), js, list);
        DeleteButton deleteButton = new DeleteButton(list, js, historyList);

        //QuestionAnswerPanel history = new QuestionAnswerPanel("History Prompts", "History area");
        //ButtonPanel buttonPanel = new ButtonPanel(question.getAnswerArea(), js, list);
        //JPanel historyPanel = history.getPanel();






      //Set the whole window
      this.setSize(1000,1000); // 1000 width and 1000 height
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible








      // //access the last question
      // int lastEntry = js.getHistoryPrompt().size() - 1;
      // String newQuestion = js.getQuestion(lastEntry);
      // String newAnswer = js.getAnswer(lastEntry);


      // Create a JPanel for the right section of the frame
      JPanel rightPanel = new JPanel(new BorderLayout());
      rightPanel.setPreferredSize(new Dimension(500, 500));


      // Add the label to the center of the right panel
      rightPanel.add(questionPanel, BorderLayout.CENTER);
      //rightPanel.add(buttonPanel);
      rightPanel.revalidate();
      rightPanel.repaint();
      //rightPanel.add(buttonPanel);
     

      rightPanel.add(newQuestionButton, BorderLayout.SOUTH);
      //rightPanel.add(buttonPanel, BorderLayout.SOUTH);
     
      rightPanel.setBackground(gray);
      rightPanel.revalidate();
      rightPanel.repaint();




      // Create a split pane with the two panels in it
      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);


      //JPanel leftPanel = new JPanel();
      JPanel leftPanel = historyPanel;


      //leftPanel.add(new JLabel("History prompt"));
      leftPanel.setPreferredSize(new Dimension(180, 1000));
      leftPanel.setBackground(blue);
      leftPanel.add(deleteButton);


      // Set the minimum size of the left panel
      Dimension minimumSize = new Dimension(200, 1000);
      leftPanel.setMinimumSize(minimumSize);


      // Combine two panels into one
      //JPanel nestedPanel = new JPanel(new BorderLayout());
      //nestedPanel.add(rightPanel, BorderLayout.CENTER);


      // Set the divider location to divide the frame into 1/5 and 4/5
      splitPane.setDividerLocation(0.2);
      //splitPane.setOneTouchExpandable(true);


      // Add the left and nested panels to the split pane
      splitPane.setLeftComponent(leftPanel);
      splitPane.setRightComponent(rightPanel);
      splitPane.setBackground(gray);


      // Add the split pane to the frame
      getContentPane().add(splitPane);




      revalidate();
    }
}




public class frame {
    public static void main(String args[]) throws IOException {
      new AppFrame(); // Create the frame
    }
   
  }

