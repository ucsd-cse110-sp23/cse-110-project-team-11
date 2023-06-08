import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.function.Consumer;

class TextPanel extends JPanel {

    public TextPanel() {
        setLayout(new GridLayout(2, 2));

        JLabel rememberLabel = new JLabel("Remember Device?", SwingConstants.CENTER);
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

    private JButton acceptButton, declineButton;
    private TextPanel textPanel;
    private YesNoPanel yesNoPanel;
    private boolean isClicked = false;
    private boolean isSaved = false;
    private Consumer<Boolean> callback;

    public RememberDeviceUI() {
        initUI();
    }

    public RememberDeviceUI(Consumer<Boolean> callback) {
        this();
        this.callback = callback;
    }

    private void initUI() {
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        textPanel = new TextPanel();
        add(textPanel);

        yesNoPanel = new YesNoPanel();
        add(yesNoPanel);

        acceptButton = yesNoPanel.getAcceptButton();
        declineButton = yesNoPanel.getDeclineButton();

        acceptButton.addActionListener(e -> {
            // The code for saving a file or whatever you do when they accept
            if (callback != null) callback.accept(true);
            isClicked = true;
            isSaved = true;
            dispose();
        });

        declineButton.addActionListener(e -> {
            // The code for when they decline
            if (callback != null) callback.accept(false);
            isClicked = true;
            dispose();
        });
    }

    public boolean isClicked() {
        return isClicked;
    }

    public boolean isSaved() {
        return isSaved;
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
