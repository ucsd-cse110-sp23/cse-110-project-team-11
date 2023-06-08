// import java.awt.*;
// import java.awt.event.*;
// import java.io.*;
// import java.lang.management.LockInfo;
// import java.net.*;
// import javax.swing.*;
// import org.bson.Document;

// import com.mongodb.client.MongoCollection;

// class FieldPanel extends JPanel {

//   private JTextField emailField, passwordField;

//   public FieldPanel() {
//     setLayout(new GridLayout(2, 2));

//     JLabel emailLabel = new JLabel("Email:",SwingConstants.CENTER);
//     emailField = new JTextField();
//     add(emailLabel);
//     add(emailField);

//     JLabel yearLabel = new JLabel("Password:",SwingConstants.CENTER);
//     passwordField = new JTextField();
//     add(yearLabel);
//     add(passwordField);
//   }

//   public String getEmail() {
//     return emailField.getText();
//   }

//   public String getPassword() {
//     return passwordField.getText();
//   }
// }

// class ButtonPanel extends JPanel {

//   private JButton createAccountButton, loginButton;

//   public ButtonPanel() {
//     setLayout(new GridLayout(1, 2));

//     createAccountButton = new JButton("Create Account");
//     createAccountButton.setBackground(Color.BLUE);
//     add(createAccountButton);

//     loginButton = new JButton("Login");
//     loginButton.setBackground(Color.GREEN);
//     add(loginButton);
//   }

//   public JButton getCreateAccountButton() {
//     return createAccountButton;
//   }

//   public JButton getLoginButton() {
//     return loginButton;
//   }
// }

// public class LoginUI extends JFrame {

//   private final String URL = "http://localhost:8100/";


//   private JButton createAccountButton, loginButton;
//   private FieldPanel fieldPanel;
//   private ButtonPanel buttonPanel;

//   public LoginUI() {
//     setSize(600, 300);
//     setDefaultCloseOperation(EXIT_ON_CLOSE);
//     setLayout(new GridLayout(2,2));


//     fieldPanel = new FieldPanel();
//     add(fieldPanel);

//     buttonPanel = new ButtonPanel();
//     add(buttonPanel);

//     createAccountButton = buttonPanel.getCreateAccountButton();
//     loginButton = buttonPanel.getLoginButton();
  

//     createAccountButton.addActionListener(
//       new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//           try {
//             String email = fieldPanel.getEmail();
//             String password = fieldPanel.getPassword();

//             if(email.equals("") || password.equals("")){
//               JOptionPane.showMessageDialog(null, "Cannot be empty");
//               return;
//             }
            
//             URL url = new URL(URL);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setDoOutput(true);
//             OutputStreamWriter out = new OutputStreamWriter(
//               conn.getOutputStream()
//             );
//             out.write(email + "," + password);
//             out.flush();
//             out.close();
//             BufferedReader in = new BufferedReader(
//               new InputStreamReader(conn.getInputStream())
//             );
//             String response = in.readLine();
//             in.close();
//             JOptionPane.showMessageDialog(null, response);
//           } catch (Exception ex) {
//             ex.printStackTrace();
//             JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
//           }
//         }
//       }
//     );



//     loginButton.addActionListener(
//   new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//       try {
//         String email = fieldPanel.getEmail();
//         String password = fieldPanel.getPassword();
//         if(email.equals("") || password.equals("")){
//           JOptionPane.showMessageDialog(null, "Cannot be empty");
//           return;
//         }
//         String query = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
//         URL url = new URL(URL + "?" + query);
//         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//         conn.setRequestMethod("GET");
//         BufferedReader in = new BufferedReader(
//           new InputStreamReader(conn.getInputStream())
//         );
//         String response = in.readLine();
//         StringBuilder responseBody = new StringBuilder();
        
//         // if(wholeResponse.indexOf(',') == -1){
//         //   JOptionPane.showMessageDialog(null, wholeResponse);
//         //   return;
//         // }
//         //String response = wholeResponse.substring(0, wholeResponse.indexOf(','));
//         if(response.equals("login successfully")){
  
//           // String user = wholeResponse.substring(response.indexOf(',') + 1);
//           String line;
//           while ((line = in.readLine()) != null) {
//                 responseBody.append(line);
//           }
//           in.close();
//           dispose();
//           //new AppFrame();
//           new AppFrame(Document.parse(responseBody.toString()));
//         }
//         else{
//           JOptionPane.showMessageDialog(null, response);
//         }
//         in.close();
//       } catch (Exception ex) {
//         ex.printStackTrace();
//         JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
//       }
//     }
//   }
// );


    
// }


  

//   // public static void main(String[] args) {
//   //   LoginUI serverUI = new LoginUI();
//   //   serverUI.setVisible(true);
//   // }
// }



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import org.bson.Document;

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

  private final String URL = "http://localhost:8100/";


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
          try {
            String email = fieldPanel.getEmail();
            String password = fieldPanel.getPassword();

            if(email.equals("") || password.equals("")){
              JOptionPane.showMessageDialog(null, "Cannot be empty");
              return;
            }
            
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
              conn.getOutputStream()
            );
            out.write(email + "," + password);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(
              new InputStreamReader(conn.getInputStream())
            );
            String response = in.readLine();
            in.close();
            JOptionPane.showMessageDialog(null, response);
          } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
          }
        }
      }
    );



    loginButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      try {

        String email = fieldPanel.getEmail();
        String password = fieldPanel.getPassword();

        if(email.equals("") || password.equals("")){
          JOptionPane.showMessageDialog(null, "Cannot be empty");
          return;
        }
        String query = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
        URL url = new URL(URL + "?" + query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
          new InputStreamReader(conn.getInputStream())
        );
        String response = in.readLine();
        StringBuilder responseBody = new StringBuilder();
        
        // if(wholeResponse.indexOf(',') == -1){
        //   JOptionPane.showMessageDialog(null, wholeResponse);
        //   return;
        // }
        //String response = wholeResponse.substring(0, wholeResponse.indexOf(','));
        if(response.equals("login successfully")){
  
          // String user = wholeResponse.substring(response.indexOf(',') + 1);
          String line;
          while ((line = in.readLine()) != null) {
                responseBody.append(line);
          }
          in.close();
          dispose();
          
          //saveCredentials(email, password);

          //String filePath = "remembered.txt";
          String filePath = "account.txt";
          File file = new File(filePath);
          
          if(!file.exists()) {
            RememberDeviceUI rd = new RememberDeviceUI();
            rd.setVisible(true);
            rd.addWindowListener(new WindowAdapter() {
              @Override
              public void windowClosed(WindowEvent w) {
                if (rd.isClicked()) {
                  if (rd.isSaved()) {
                    saveCredentials(email, password);
                  }
                  dispose();
                  try {
                    new AppFrame(Document.parse(responseBody.toString()));
                  } catch (IOException e) {
                    // 
                  }
                }
              }
            });
          } else {
          new AppFrame(Document.parse(responseBody.toString()));
          }
        }
        else{
          JOptionPane.showMessageDialog(null, response);
        }
        in.close();
      } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
      }
    }
  }
);    
}

private void saveCredentials(String email, String password) {
  String fileName = "account.txt";

    try{
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(email + "\n" + password);
        writer.close();
    } catch (IOException e) {
        // nothing happens
    }
}
}
