import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
class Question extends JPanel{
    private JLabel question;
    private JLabel answer;
    private JTextArea answerArea;
    private JTextArea questionArea;

    private Color pink = new Color(223, 165, 245);
    
    /*
     * Set question
     */
    public void setQuestionString(String newQuestion){
        question.setText(newQuestion);
    }

    /*
     * Set answer
     */
    public void setAnswerString(String newAnswer){
        answer.setText(newAnswer);
    }

    /*
     * Get question
     */
    public JLabel getQuestion(){
        return question;
    }

    /*
     * Get answer
     */
    public JLabel getAnswer(){
        return answer;
    }

    /*
     * Get answer area
     */
    public JTextArea getAnswerArea(){
        return answerArea;
    }

    public JTextArea getQuestionArea() {
        return questionArea;
    }

    /*
     * Constructor for question, settings for question and answer labels (size, color)
     */
    Question(String questionText, String answerText) {
        this.setPreferredSize(new Dimension(780, 800)); // set size of task
        // this.setBackground(gray); // set background color of task
        this.setLayout(new BorderLayout()); // set layout of task

        // create question label
        question = new JLabel("<html>"+questionText+ "</html>"); // create index label
        question.setPreferredSize(new Dimension(780, 50)); // set size of index label
        question.setHorizontalAlignment(JLabel.CENTER); // set alignment of index label
        question.setVerticalAlignment(JLabel.TOP);
        
        // create answer label
        answer = new JLabel("<html>"+ answerText +"<html>"); // create task name text field
        answerArea = new JTextArea(answerText, 5, 20);
        answerArea.setBounds(10, 400, 780, 500);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);
        answerArea.setBackground(pink);

        // set size and position
        question.setPreferredSize(new Dimension(780, 400)); // set size of index label
        answer.setHorizontalAlignment(JLabel.CENTER);
        answer.setVerticalAlignment(JLabel.TOP);
        answer.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
        // answer.setBackground(gray); // set background color of text field   
    }

}


class AppFrame extends JFrame {

    // Colors
    Color gray = new Color(223, 165, 245);
    Color historyColor = new Color(100, 204, 200);
    Color someGray = new Color(223, 165, 245);
    Color deepGray = new Color(223, 165, 245);

    private NewQuestion nq = new NewQuestion();

    /*
     * TODO: DELETE
     * Interface for question
     * Input any file that contains questions and modify below code
     */
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

    
    // public void displayQuery() throws JSONException, IOException, InterruptedException {
    //   // displays result from new question on right panel
    //   String whisperArg = "myAudio.mp3";
    //   String question = whisper.getTranscript(whisperArg);
    //   chatgpt.chat(question);
    // }




    /*
     * Main UI Frame
     */
    AppFrame() throws IOException {

      JsonStorage js = new JsonStorage("historyPrompt.json");

      for (int i = 0; i < 50; i++) {
        JSONObject eg1 = new JSONObject();
        eg1.put("question", "What is your name? What is your age?" + i);
        eg1.put("answer", "My name is John." + i);
        js.addPrompt(eg1);
      }

      int lastEntry = js.getHistoryPrompt().size() - 1;
      String newQuestion = js.getQuestion(lastEntry);
      String newAnswer = js.getAnswer(lastEntry);


      // create a question
      Question question = new Question(newQuestion, newAnswer);



      //Set the whole window
      this.setSize(1000,1000); // 1000 width and 1000 height
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible

      // // Get question from text file
      // // TODO: should get question from json file
      // String questionText = loadfile();

      // // Get answer from text file
      // // TODO: modify answer Text
      // String answerText = questionText;


      // // create question panel
      JPanel row1 = new JPanel();
      row1.setBackground(deepGray);
      row1.setPreferredSize(new Dimension(800, 50));
      row1.add(question.getQuestion());

      // create answer area in frame
      JTextArea answerArea = question.getAnswerArea();
      JTextArea questionArea = question.getQuestionArea();
      //JsonStorage history = new JsonStorage("historyPrompt.json");

    
      HistoryList list = new HistoryList(js, answerArea, questionArea);

      // get newQuestionButton
      NewQuestionButton newQuestionButton = new NewQuestionButton(answerArea, js, list);
      JButton newButton = newQuestionButton.getNewQuestionButton();
      JList historyList = list.getHistoryList();
      list.printDlmSize();
      
      DeleteButton deleteButton = new DeleteButton(list, js, historyList);

      // Create a JPanel for the right section of the frame
      JPanel rightPanel = new JPanel(new BorderLayout());
      rightPanel.setPreferredSize(new Dimension(800, 900));

      //JTextArea answerArea = history.getAnswerArea();
      //JTextArea answerArea = question.getAnswerArea();
      // Add the label to the center of the right panel
      rightPanel.add(row1, BorderLayout.NORTH);
      //rightPanel.add(row2, BorderLayout.CENTER);
      rightPanel.add(answerArea, BorderLayout.SOUTH);
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
      leftPanel.add(deleteButton);

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