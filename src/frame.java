import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.swing.*;


/*
 * Handle question panel
 */
class QuestionAnswerPanel extends JPanel{
   
    private JTextArea questionTextArea;
    private JTextArea answerTextArea;


    public QuestionAnswerPanel(String question, String answer) {
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
    }

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


class AppFrame extends JFrame {

    // Colors
    Color gray = new Color(50, 50, 50);
    Color blue = new Color(100, 204, 200);
    Color lightGray = new Color (200,200,200);

    private Document user;
    private JsonStorage js;
<<<<<<< HEAD
    private EmailStorage store;
    //private String user_email;
=======
   
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8

    //user getter
    public Document getUser() {
      return user;
    }

    //JsonStorage getter
    public JsonStorage getJsonStorage() {
      return js;
    }

    /*
     * Main UI Frame
     */
    AppFrame(Document user) throws IOException {

      //initailze the user
      this.user = user;

      //initailze the data structure
      js = new JsonStorage();
      store = new EmailStorage(user);
       
      //read user information
      js.readJson(user);

      String email = user.getString("email");

      // // //adding actions to exit
      // addWindowListener(new WindowAdapter() {


      //   @Override
      //   public void windowClosing(WindowEvent e){
      //     try {
      //       js.writeJson("historyPrompt.json");
      //     } catch (IOException e1) {
      //       e1.printStackTrace();
      //     }
      //       System.exit(0);
      //     }
      // });
     
        //set original question panel
        String newQuestion = "";
        String newAnswer = "";
   
        // create a question
        QuestionAnswerPanel question = new QuestionAnswerPanel(newQuestion, newAnswer);
        JPanel questionPanel = question.getPanel();

        HistoryList list = new HistoryList(js, question.getAnswerArea(), question.getQuestionArea());
        JPanel historyPanel = list.getHistoryPanel();
        JList<String> historyList = list.getHistoryList();
<<<<<<< HEAD
        NewQuestionButton newQuestionButton = new NewQuestionButton(question.getAnswerArea(), question.getQuestionArea(), js, list, historyList,email,store);
=======
        NewQuestionButton newQuestionButton = new NewQuestionButton(question.getAnswerArea(), question.getQuestionArea(), js, list, historyList,email);
>>>>>>> fc4db1111d673a9b47b91874c830576b194bc3c8
        //DeleteButton deleteButton = new DeleteButton(list, js, historyList);

      //Set the whole window
      this.setSize(1000,1000); // 1000 width and 1000 height
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Close on exit
      this.setVisible(true); // Make visible

      // Create a JPanel for the right section of the frame
      JPanel rightPanel = new JPanel(new BorderLayout());
      rightPanel.setPreferredSize(new Dimension(500, 500));

      // Add the label to the center of the right panel
      rightPanel.add(questionPanel, BorderLayout.CENTER);
      rightPanel.revalidate();
      rightPanel.repaint();
     
      rightPanel.add(newQuestionButton, BorderLayout.SOUTH);
      rightPanel.setBackground(gray);
      rightPanel.revalidate();
      rightPanel.repaint();

      // Create a split pane with the two panels in it
      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

      JPanel leftPanel = historyPanel;

      leftPanel.setPreferredSize(new Dimension(180, 1000));
      leftPanel.setBackground(blue);


      // Set the minimum size of the left panel
      Dimension minimumSize = new Dimension(200, 1000);
      leftPanel.setMinimumSize(minimumSize);

      // Set the divider location to divide the frame into 1/5 and 4/5
      splitPane.setDividerLocation(0.2);

      // Add the left and nested panels to the split pane
      splitPane.setLeftComponent(leftPanel);
      splitPane.setRightComponent(rightPanel);
      splitPane.setBackground(gray);


      // Add the split pane to the frame
      getContentPane().add(splitPane);

      revalidate();
    }
}




// public class frame {
//     public static void main(String args[]) throws IOException {
//       //new AppFrame(); // Create the frame
//     }
//   }

