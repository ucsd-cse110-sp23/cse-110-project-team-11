import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.management.LockInfo;
import java.net.*;
import javax.swing.*;
import javax.swing.text.Document;


class FieldPanel extends JPanel {

  private JTextField firstNameField, lastNameField, displayNameField, emailField, SMTPField, TLSField;

  public FieldPanel() {
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

class ButtonPanel extends JPanel {

  private JButton saveButton, cancelButton;

  public ButtonPanel() {
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
  private FieldPanel fieldPanel;
  private ButtonPanel buttonPanel;

  public SetUpEmail() {
    setSize(600, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(2,2));


    fieldPanel = new FieldPanel();
    add(fieldPanel);

    buttonPanel = new ButtonPanel();
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

            JOptionPane.showMessageDialog(null, "Information saved successfully!");
            
            
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


  

  public static void main(String[] args) {
    SetUpEmail setup = new SetUpEmail();
    setup.setVisible(true);
  }
}