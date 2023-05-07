import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.util.ArrayList;
import java.util.List;


class Question extends JPanel{
    JLabel question;
    JLabel answer;
    JTextArea answerArea;

    Color gray = new Color(218, 229, 234);
    Color someGray = new Color(199, 199, 199);
    Color green = new Color(188, 226, 158);

    Question(String questionText, String answerText) {
        this.setPreferredSize(new Dimension(780, 800)); // set size of task
        this.setBackground(gray); // set background color of task
        this.setLayout(new BorderLayout()); // set layout of task

        //JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        question = new JLabel("<html>"+questionText+ "</html>"); // create index label
        question.setPreferredSize(new Dimension(780, 50)); // set size of index label
        question.setHorizontalAlignment(JLabel.CENTER); // set alignment of index label
        question.setVerticalAlignment(JLabel.TOP);
        //this.add(question, BorderLayout.WEST); // add index label to task

        //panel.add(question);
        // AppFrame.getContentPane().add(panel, BorderLayout.NORTH);
        
        answer = new JLabel("<html>"+ answerText +"<html>"); // create task name text field
        answerArea = new JTextArea(answerText, 5, 20);
        answerArea.setBounds(10, 11, 780, 1000);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);
        answerArea.setBackground(someGray);

        question.setPreferredSize(new Dimension(780, 50)); // set size of index label
        answer.setHorizontalAlignment(JLabel.CENTER);
        answer.setVerticalAlignment(JLabel.TOP);
        answer.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
        answer.setBackground(gray); // set background color of text field
        
        //this.setLayout(new GridLayout(100,1));

    
        //this.add(answer, BorderLayout.CENTER);


        // submitButton = new JButton("Submit");
        // submitButton.setPreferredSize(new Dimension(80, 20));
        // submitButton.setBorder(BorderFactory.createEmptyBorder());
        // submitButton.setFocusPainted(false);

        // this.add(submitButton, BorderLayout.EAST);
    }

}


class AppFrame extends JFrame {

    Color gray = new Color(211, 211, 211);
    Color darkGray = new Color(169, 169, 169);
    Color someGray = new Color(199, 199, 199);
    Color deepGray = new Color(149, 149, 149);

    public ArrayList<String> loadfile() {
      // hint 1: use try-catch block
      // hint 2: use BufferedReader and FileReader
      // hint 3: task.taskName.setText(line) sets the text of the task
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
      return questions;
    }
  
    AppFrame() {
      this.setSize(1000,1000); // 1000 width and 1000 height
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible

      // Create a JPanel for the right section of the frame
      JPanel rightPanel = new JPanel();
      rightPanel.setPreferredSize(new Dimension(800, 1000));
      //rightPanel.setLayout(new GridLayout(20,1));

      // Get question from text file
      ArrayList<String> questions = loadfile();
      String questionText = questions.get(0);
      List<String> answerTextList = questions.subList(3,questions.size()); 

      String answerText = String.join("\n", answerTextList);

      System.out.println(answerText);
      // create a question
      Question question = new Question(questionText, answerText);

      // create question panel
      JPanel row1 = new JPanel();
      row1.setBackground(deepGray);
      row1.setPreferredSize(new Dimension(800,50));
      row1.add(question.question);

      // create answer panel
      JPanel row2 = new JPanel();
      row2.setBackground(someGray);
      row2.setPreferredSize(new Dimension(800, 1000));
      row2.add(question.answerArea);
      //row2.add(question.answer);



      // Add the label to the center of the right panel
      rightPanel.add(row1, BorderLayout.CENTER);
      rightPanel.add(row2, BorderLayout.CENTER);
      rightPanel.setBackground(gray);
      
      // // Add the label to the center of the right panel
      // rightPanel.add(question.question, BorderLayout.CENTER);
      // rightPanel.add(question.answer, BorderLayout.CENTER);
      // rightPanel.setBackground(gray);

      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

      JPanel leftPanel = new JPanel();
      leftPanel.add(new JLabel("History prompt"));
      leftPanel.setPreferredSize(new Dimension(180, 1000));
      leftPanel.setBackground(darkGray);

      JPanel nestedPanel = new JPanel(new BorderLayout());
      nestedPanel.add(rightPanel, BorderLayout.CENTER);

      // Set the divider location to divide the frame into 1/5 and 4/5
      splitPane.setDividerLocation(0.2);
      //splitPane.setDividerSize(50);

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
    //   try {
    //   Class<?> class1 = Class.forName("ChatGPT");
    //     Method method = class1.getMethod("main", String[].class);
    //     String[] arguments = args;
    //     method.invoke(null, (Object) arguments);
    //   } catch (ClassNotFoundException e) {
    //     e.printStackTrace();
    // } catch (NoSuchMethodException e) {
    //     e.printStackTrace();
    // } catch (SecurityException e) {
    //     e.printStackTrace();
    // } catch (IllegalAccessException e) {
    //     e.printStackTrace();
    // } catch (IllegalArgumentException e) {
    //     e.printStackTrace();
    // } catch (InvocationTargetException e) {
    //     e.printStackTrace();
    // }
      new AppFrame(); // Create the frame
      
    }
  }