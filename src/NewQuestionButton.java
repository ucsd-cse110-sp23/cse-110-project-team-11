import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

class Footer extends JPanel {

    JButton NewQuestion;
  
    Color backgroundColor = new Color(240, 248, 255);
    Border emptyBorder = BorderFactory.createEmptyBorder();
  
    Footer() {
      this.setPreferredSize(new Dimension(400, 60));
      this.setBackground(backgroundColor);
  
      //GridLayout layout = new GridLayout(1, 4);
      //layout.setHgap(5); // Hertical gap
      //this.setLayout((layout));
  
      //We don't have a sidebar yet, so we don't know if it is in the middle of the screen.
      NewQuestion = new JButton("New Question"); // add task button
      NewQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10)); // set font
      this.add(NewQuestion); // add to footer
  
    }
  
    public JButton getNewButton() {
      return NewQuestion;
    }
  
  }
  
  class AppFrame extends JFrame {
  
    private Footer footer;
  
    private JButton NewQuestion;
  
    AppFrame() {
      this.setSize(400, 600); // 400 width and 600 height
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible
  
      footer = new Footer();
  
      this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
  
      NewQuestion = footer.getNewButton();
  
      addListeners();
      revalidate();
    }
  
    public void addListeners() {
        NewQuestion.addActionListener(
        (ActionEvent e) -> {
            //JButton doneButton = task.getDone();
            ImageIcon Icon = new ImageIcon("RedIcon.png");
            NewQuestion.setIcon(Icon);

            //Place holder for recording method US4
            //startrecording
        }
      );

    }
  }
  
  public class NewQuestionButton{
  
    public static void main(String args[]) {
      new AppFrame(); // Create the frame
    }
  }