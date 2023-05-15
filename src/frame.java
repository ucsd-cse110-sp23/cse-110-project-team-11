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
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


// class Footer extends JPanel {
//   private JButton newQuestion;
//     private boolean isIconVisible = false;
//     private ImageIcon icon;

//     Footer() {
//         // Set the size and background color of the panel.
//         setPreferredSize(new Dimension(400, 60));
//         setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

//         // Create the button.
//         newQuestion = new JButton("New Question");
//         newQuestion.setPreferredSize(new Dimension(100, 60)); // set the size of the button
//         newQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10));

//         // Add the button to the panel.
//         add(newQuestion);

//         // Add an action listener to the button.
//         newQuestion.addActionListener(e -> waitForAnswer());

//         // Create a SwingWorker to load the image in a background thread.
//         //From ChaTGPT May 3rd version. Modify the ToggleIcon and add some methods to
//         //be able to load the image in advance.
//                 new SwingWorker<ImageIcon, Void>() {
//             @Override
//             protected ImageIcon doInBackground() throws Exception {
//                 // Load the image from the URL.
//                 URL url = new URL("https://drive.google.com/uc?export=download&id=1B39XTjnEYdqXU_JIp9gRvT6naAX6uOxI");
//                 ImageIcon icon = new ImageIcon(url);
//                 Image image = icon.getImage();
                
//                 // Scale the image to the size of the button.
//                 Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);
                
//                 // Return the scaled image as an ImageIcon.
//                 return new ImageIcon(scaledImage);
//             }

//             @Override
//             protected void done() {
//                 try {
//                     // When the image has been loaded, store it in the 'icon' field.
//                     icon = get();
//                 } catch (Exception e) {
//                     // If an error occurs while loading the image, print the stack trace.
//                     e.printStackTrace();
//                 }
//             }
//         }.execute();
//     }

//     public void waitForAnswer() {
//       toggleIcon();
//       clearAnswer();
//     }

//     public void clearAnswer() {
//       // clear answerTextArea;
//       getAnswerArea();
//     }

//     private void toggleIcon() {
//         // If the icon is not currently visible, set it as the button's icon.
//         // If the icon is currently visible, remove it and set the button's text back to "New Question".
//         if (!isIconVisible) {
//             newQuestion.setIcon(icon);
//             newQuestion.setText("");
//             isIconVisible = true;
//         } else {
//             newQuestion.setIcon(null);
//             newQuestion.setText("New Question");
//             isIconVisible = false;
//         }
//     }

  // private JButton newQuestion; // button to ask new question
  // private boolean isIconVisible = false;

  // Border emptyBorder = BorderFactory.createEmptyBorder(); // create empty border

  // Footer() {
  //     setPreferredSize(new Dimension(400, 60));
  //     setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

  //     //add new question button
  //     newQuestion = new JButton("New Question");
  //     newQuestion.setPreferredSize(new Dimension(100, 60)); // set the size of the button
  //     newQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10));
  //     add(newQuestion);

  //     //add action listener to new question button
  //     newQuestion.addActionListener(e -> toggleIcon());
  //     //TODO: raise start recording action and threading
  // }

  // //Change the icon of the button while recording
  // private void toggleIcon() {
  //     if (!isIconVisible) {
  //         //if MacBook user, change to "/path/redIcon.png"
  //         //if Windows user, move the file into the same folder and change to "redIcon.png"

  //         //read image file for icon
  //         ImageIcon icon = new ImageIcon("redIcon.png");
  //         Image image = icon.getImage();
  //         Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);

  //         //set icon
  //         newQuestion.setIcon(new ImageIcon(scaledImage));
  //         newQuestion.setText("");
  //         isIconVisible = true;
  //     } else {
  //         //change back to original button
  //         newQuestion.setIcon(null);
  //         newQuestion.setText("New Question");
  //         isIconVisible = false;
  //     }
  // }
//}


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

    // Create a space for the footer
    //private Footer footer;

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
    AppFrame() {
      //Set the whole window
      this.setSize(1000,1000); // 1000 width and 1000 height
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible

      // Get question from text file
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

      // // create answer panel
      // JPanel row2 = new JPanel();
      // row2.setBackground(gray);
      // row2.setPreferredSize(new Dimension(800, 750));
      // row2.add(question.getAnswerArea());


      // initialize footer
      //footer = new Footer();

      // pass in history to delete button constructor
    // pass text area into newQuestion and history list.
      
      // history object to get panel
      JTextArea answerArea = question.getAnswerArea();
      HistoryList history = new HistoryList("/Users/hongyuan/Documents/GitHub/cse-110-project-team-11/src/question.txt", "/Users/hongyuan/Documents/GitHub/cse-110-project-team-11/src/answer.txt", answerArea);

      // get newQuestionButton
      NewQuestionButton newQuestionButton = new NewQuestionButton(answerArea);
      JButton newButton = newQuestionButton.getNewQuestionButton();

      //get ClearAll button
      clearAllButton clearAllButton = new clearAllButton();
      JButton clearAll = clearAllButton.getClear();

      // Create a JPanel for the right section of the frame
      //JPanel rightPanel = new JPanel();
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

      // if i click on the new question button, answer text field should be cleared.
      // button listener


      // Create a split pane with the two panels in it
      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

      //JPanel leftPanel = new JPanel();
      JPanel leftPanel = history.getHistoryPanel();
      //Adding clear all button to left 
      leftPanel.add(clearAll, BorderLayout.SOUTH);

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
    public static void main(String args[]) {
      new AppFrame(); // Create the frame
      
    }
    
  }