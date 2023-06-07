import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class TextPanel extends JPanel {

  public TextPanel() {
    setLayout(new GridLayout(2, 2));

    JLabel rememberLabel = new JLabel("Remember Device?",SwingConstants.CENTER);
    add(rememberLabel);
  }
}

class YesNoPanel extends JPanel {
  private JButton acceptButton, declineButton;

  public YesNoPanel() {
    setLayout(new GridLayout(1, 2));

    acceptButton = new JButton("Yes");
    acceptButton.setBackground(Color.GREEN);
    add(acceptButton);

    declineButton = new JButton("No");
    declineButton.setBackground(Color.RED);
    add(declineButton);
  }

  public JButton getAcceptButton() {
    return acceptButton;
  }

  public JButton getDeclineButton() {
    return declineButton;
  }
}

public class RememberDeviceUI extends JFrame {

  private final String URL = "http://localhost:8100/";

  private JButton acceptButton, declineButton;
  private TextPanel textPanel;
  private YesNoPanel yesNoPanel;
  private boolean isClicked = false;

  public RememberDeviceUI() {
    setSize(600, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(2,2));

    textPanel = new TextPanel();
    add(textPanel);

    yesNoPanel = new YesNoPanel();
    add(yesNoPanel);

    acceptButton = yesNoPanel.getAcceptButton();
    declineButton = yesNoPanel.getDeclineButton();
  

    acceptButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            /**
             * check to see if file exists in directory - happens before loginUI
             * in loginUI -> call this class RememberDeviceUI
             */

            String fileName = "remembered.txt";

            try{
                File file = new File(fileName);
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.close();
            } catch (IOException exception) {
                // nothing happens
            }

            isClicked = true;
            dispose();
        }
      }
    );



    declineButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //close window and go to historyprompt
                isClicked = true;
                dispose();
            }
        }
    );  
}
// public static void main(String[] args) {
//     RememberDeviceUI rd = new RememberDeviceUI();
//     rd.setVisible(true);
// }

public boolean isClicked() {
    return isClicked;
}
}
