import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.management.LockInfo;
import java.net.*;
import javax.swing.*;

class FieldPanel extends JPanel {

  private JTextField emailField, passwordField;

  public FieldPanel() {
    setLayout(new GridLayout(2, 2));

    JLabel emailLabel = new JLabel("Email:",SwingConstants.CENTER);
    emailField = new JTextField();
    add(emailLabel);
    add(emailField);

    JLabel yearLabel = new JLabel("Password:",SwingConstants.CENTER);
    passwordField = new JTextField();
    add(yearLabel);
    add(passwordField);
  }

  public String getEmail() {
    return emailField.getText();
  }

  public String getPassword() {
    return passwordField.getText();
  }
}

class ButtonPanel extends JPanel {

  private JButton createAccountButton, loginButton;

  public ButtonPanel() {
    setLayout(new GridLayout(1, 2));

    createAccountButton = new JButton("Create Account");
    createAccountButton.setBackground(Color.BLUE);
    add(createAccountButton);

    loginButton = new JButton("Login");
    loginButton.setBackground(Color.GREEN);
    add(loginButton);
  }

  public JButton getCreateAccountButton() {
    return createAccountButton;
  }

  public JButton getLoginButton() {
    return loginButton;
  }
}

public class LoginUI extends JFrame {

  public final String URL = "http://localhost:8100/";

  private JButton createAccountButton, loginButton;
  private FieldPanel fieldPanel;
  private ButtonPanel buttonPanel;

  public LoginUI() {
    setSize(600, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(2,2));


    fieldPanel = new FieldPanel();
    add(fieldPanel);

    buttonPanel = new ButtonPanel();
    add(buttonPanel);

    createAccountButton = buttonPanel.getCreateAccountButton();
    loginButton = buttonPanel.getLoginButton();
  

    createAccountButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        //   try {
        //     String language = fieldPanel.getEmail();
        //     String year = fieldPanel.getPassword();
        //     URL url = new URL(URL);
        //     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //     conn.setRequestMethod("POST");
        //     conn.setDoOutput(true);
        //     OutputStreamWriter out = new OutputStreamWriter(
        //       conn.getOutputStream()
        //     );
        //     out.write(language + "," + year);
        //     out.flush();
        //     out.close();
        //     BufferedReader in = new BufferedReader(
        //       new InputStreamReader(conn.getInputStream())
        //     );
        //     String response = in.readLine();
        //     in.close();
        //     JOptionPane.showMessageDialog(null, response);
        //   } catch (Exception ex) {
        //     ex.printStackTrace();
        //     JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        //   }
        }
      }
    );

    loginButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         
        }
      }
    );
    
  }

  public static void main(String[] args) {
    LoginUI serverUI = new LoginUI();
    serverUI.setVisible(true);
  }
}
