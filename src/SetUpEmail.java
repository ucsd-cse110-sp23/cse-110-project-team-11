import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.management.LockInfo;
import java.net.*;
import javax.swing.*;
import org.bson.Document;
import org.json.JSONObject;
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


class EmailFieldPanel extends JPanel {

  private JTextField firstNameField, lastNameField, displayNameField, emailField, SMTPField, TLSField;

  EmailFieldPanel() {
    setLayout(new GridLayout(2, 3));

    JLabel firstNameLabel = new JLabel("First Name:",SwingConstants.CENTER);
    firstNameField = new JTextField();
    add(firstNameLabel);
    add(firstNameField);

    JLabel lastNameLabel = new JLabel("Last Name:",SwingConstants.CENTER);
    lastNameField = new JTextField();
    add(lastNameLabel);
    add(lastNameField);

    JLabel displayNameLabel = new JLabel("Display Name:",SwingConstants.CENTER);
    displayNameField = new JTextField();
    add(displayNameLabel);
    add(displayNameField);

    JLabel emailLabel = new JLabel("E-mail Address:",SwingConstants.CENTER);
    emailField = new JTextField();
    add(emailLabel);
    add(emailField);

    JLabel SMTPLabel = new JLabel("SMTP Host:",SwingConstants.CENTER);
    SMTPField = new JTextField();
    add(SMTPLabel);
    add(SMTPField);

    JLabel TLSLabel = new JLabel("TLS Port:",SwingConstants.CENTER);
    TLSField = new JTextField();
    add(TLSLabel);
    add(TLSField);
  }

  public JPanel getPanel() {
    return this;
  }

  public String getFirstName() {
    return firstNameField.getText();
  }

  public String getLastName() {
    return lastNameField.getText();
  }

  public String getDisplayName() {
    return displayNameField.getText();
  }

  public String getEmail() {
    return emailField.getText();
  }

  public String getSMTP() {
    return SMTPField.getText();
  }

  public String getTLSPort() {
    return TLSField.getText();
  }
}

class EmailButtonPanel extends JPanel {

  private JButton saveButton, cancelButton;

  EmailButtonPanel() {
    setLayout(new GridLayout(1, 2));

    saveButton = new JButton("Save!");
    saveButton.setBackground(Color.BLUE);
    add(saveButton);

    cancelButton = new JButton("CANCEL :(((");
    cancelButton.setBackground(Color.RED);
    add(cancelButton);
  }

  public JButton getSaveButton() {
    return saveButton;
  }

  public JButton getCancelButton() {
    return cancelButton;
  }
}

class SetUpEmail extends JFrame {

  public final String URL = "http://localhost:8100/";


  private JButton saveButton, cancelButton;
  private EmailFieldPanel fieldPanel;
  private EmailButtonPanel buttonPanel;

  public SetUpEmail(EmailStorage store, String user_email) {
    setSize(600, 300);
    //setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(2,2));


    fieldPanel = new EmailFieldPanel();
    add(fieldPanel);

    buttonPanel = new EmailButtonPanel();
    add(buttonPanel);

    saveButton = buttonPanel.getSaveButton();
    cancelButton = buttonPanel.getCancelButton();
  

    saveButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            String firstName = fieldPanel.getFirstName();
            String lastName = fieldPanel.getLastName();
            String displayName = fieldPanel.getDisplayName();
            String email = fieldPanel.getEmail();
            String SMTPHost= fieldPanel.getSMTP();
            String TLSPort = fieldPanel.getTLSPort();

            if(firstName.equals("") || lastName.equals("") || displayName.equals("") || email.equals("") || SMTPHost.equals("") || TLSPort.equals("")){
              JOptionPane.showMessageDialog(null, "Cannot be empty. Please fill out required fields or press cancel.");
              return;
            }

            store.addPrompt(firstName, lastName, displayName, email, SMTPHost, TLSPort);
            
            JSONObject host = store.getEmailHost();

            JSONObject update = new JSONObject();
            update.put("email_host", host);

            
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
                conn.getOutputStream()
            );
            out.write(user_email + ',' + update.toString());
            out.flush();
            out.close();
            
            BufferedReader in = new BufferedReader(
              new InputStreamReader(conn.getInputStream())
            );
            String response = in.readLine();
            in.close();
            //JOptionPane.showMessageDialog(null, "Information saved successfully!");
            dispose();
            
          } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
          }
        }
      }
    );


    cancelButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            dispose();
          } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
          }
        }
      }
    );

}


  

  // public static void main(String[] args) {
  //   SetUpEmail setup = new SetUpEmail();
  //   setup.setVisible(true);
  // }
}