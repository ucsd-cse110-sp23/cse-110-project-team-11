import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.JSONObject;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class Question extends JPanel{
    private JLabel question;
    private JLabel answer;
    private JTextArea answerArea;

    private Color gray = new Color(218, 229, 234);
    private Color someGray = new Color(199, 199, 199);
    private Color green = new Color(188, 226, 158);
    
    //setters for question
    public void setQuestionString(String newQuestion){
        question.setText(newQuestion);
    }

    //setters for answer
    public void setAnswerString(String newAnswer){
        answer.setText(newAnswer);
    }

    //getters for question
    public JLabel getQuestion(){
        return question;
    }

    //getters for answer
    public JLabel getAnswer(){
        return answer;
    }

    //getters for answerArea
    public JTextArea getAnswerArea(){
        return answerArea;
    }

    Question(String questionText, String answerText) {
        this.setPreferredSize(new Dimension(780, 800)); // set size of task
        this.setBackground(gray); // set background color of task
        this.setLayout(new BorderLayout()); // set layout of task

        // create question label
        question = new JLabel("<html>"+questionText+ "</html>"); // create index label
        question.setPreferredSize(new Dimension(780, 50)); // set size of index label
        question.setHorizontalAlignment(JLabel.CENTER); // set alignment of index label
        question.setVerticalAlignment(JLabel.TOP);

        
        //create answer label
        answer = new JLabel("<html>"+ answerText +"<html>"); // create task name text field
        answerArea = new JTextArea(answerText, 5, 20);
        answerArea.setBounds(10, 11, 780, 1000);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);
        answerArea.setBackground(someGray);

        //set size and position
        question.setPreferredSize(new Dimension(780, 50)); // set size of index label
        answer.setHorizontalAlignment(JLabel.CENTER);
        answer.setVerticalAlignment(JLabel.TOP);
        answer.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
        answer.setBackground(gray); // set background color of text field   
    }

}


class AppFrame extends JFrame {

    // Colors
    Color gray = new Color(240, 240, 240);
    Color historyColor = new Color(100, 204, 200);
    Color someGray = new Color(60, 60, 60);
    Color deepGray = new Color(255, 255, 255);


    // INTERFACE for questions
    // input anyfile that contains questions and modify below code
    public String loadfile() {

      ArrayList<String> questions = new ArrayList<String>();
  
      try{
        BufferedReader reader = new BufferedReader(new FileReader("question.txt"));
        
        String line = reader.readLine();
        while(line != null){
              //System.out.println(line);
              questions.add(line);
              line = reader.readLine();
        }
  
        reader.close();
      }
      catch(IOException e){
          System.out.println("Reading Error: " + e.getMessage());
    
      }
      return String.join("\n", questions);
    }

    
    //MAIN UI Frame
    AppFrame() throws IOException {
      //Set the whole window
      this.setSize(1000,1000); // 1000 width and 1000 height
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible

      // Get question from text file
      // TODO: should get question from json file
      String questionText = loadfile();

      // Get answer from text file
      // TODO: modify answer Text
      String answerText = questionText;


      // create a question
      Question question = new Question(questionText, answerText);

      // // create question panel
      JPanel row1 = new JPanel();
      row1.setBackground(deepGray);
      row1.setPreferredSize(new Dimension(800, 50));
      row1.add(question.getQuestion());

      // create answer area in frame
      JTextArea answerArea = question.getAnswerArea();
      JsonStorage history = new JsonStorage("historyPrompt.json");

      // try adding to json
      JSONObject eg1 = new JSONObject();
        eg1.put("question", "What is your name?");
        eg1.put("answer", "My name is John.");
        history.addPrompt(eg1);
      //history.addPrompt();

      HistoryList list = new HistoryList(history, answerArea);
      // get newQuestionButton
      NewQuestionButton newQuestionButton = new NewQuestionButton(answerArea);
      JButton newButton = newQuestionButton.getNewQuestionButton();

      //get ClearAll button
      clearAllButton clearAllButton = new clearAllButton();
      JButton clearAll = clearAllButton.getClear();

      // Create a JPanel for the right section of the frame
      JPanel rightPanel = new JPanel(new BorderLayout());
      rightPanel.setPreferredSize(new Dimension(800, 900));

      //JTextArea answerArea = history.getAnswerArea();
      //JTextArea answerArea = question.getAnswerArea();
      // Add the label to the center of the right panel
      rightPanel.add(row1, BorderLayout.CENTER);
      //rightPanel.add(row2, BorderLayout.CENTER);
      rightPanel.add(answerArea, BorderLayout.CENTER);
      //rightPanel.add(footer, BorderLayout.SOUTH);
      //rightPanel.add(newButton, BorderLayout.SOUTH);
      JPanel buttonPanel = new JPanel();  // FlowLayout is the default
      buttonPanel.add(newButton);

      rightPanel.add(buttonPanel, BorderLayout.SOUTH);
      
      rightPanel.setBackground(gray);

      // Create a split pane with the two panels in it
      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

      //JPanel leftPanel = new JPanel();
      JPanel leftPanel = list.getHistoryPanel();

      //leftPanel.add(new JLabel("History prompt"));
      leftPanel.setPreferredSize(new Dimension(180, 1000));
      leftPanel.setBackground(historyColor);

      // Set the minimum size of the left panel
      Dimension minimumSize = new Dimension(200, 1000); 
      leftPanel.setMinimumSize(minimumSize);

      // Combine two panels into one
      JPanel nestedPanel = new JPanel(new BorderLayout());
      nestedPanel.add(rightPanel, BorderLayout.CENTER);

      // Set the divider location to divide the frame into 1/5 and 4/5
      splitPane.setDividerLocation(0.2);
      //splitPane.setOneTouchExpandable(true);

      // Add the left and nested panels to the split pane
      splitPane.setLeftComponent(leftPanel);
      splitPane.setRightComponent(nestedPanel);
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