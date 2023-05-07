import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Footer extends JPanel {

    private JButton newQuestion;
    private boolean isIconVisible = false;

    Border emptyBorder = BorderFactory.createEmptyBorder();

    Footer() {
        setPreferredSize(new Dimension(400, 60));
        setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

        newQuestion = new JButton("New Question");
        newQuestion.setPreferredSize(new Dimension(100, 60)); // set the size of the button

        newQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10));

        add(newQuestion);

        newQuestion.addActionListener(e -> toggleIcon());
    }

    private void toggleIcon() {
        if (!isIconVisible) {
            //if MacBook user, change to "/path/redIcon.png"
            //if Windows user, move the file into the same folder and change to "redIcon.png"
            ImageIcon icon = new ImageIcon("/Users/hongyuan/Documents/GitHub/cse-110-project-team-11/src/redIcon.png");
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);
            newQuestion.setIcon(new ImageIcon(scaledImage));
            newQuestion.setText("");
            isIconVisible = true;
        } else {
            newQuestion.setIcon(null);
            newQuestion.setText("New Question");
            isIconVisible = false;
        }
    }
}

class AppFrame extends JFrame {

    private Footer footer;

    AppFrame() {
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 0, 0, 0)); // set the background color to transparent
    
        footer = new Footer();
        add(footer, BorderLayout.SOUTH);
    
        setVisible(true);
    }
    
}

public class NewQuestionButton {

    public static void main(String[] args) {
        new AppFrame();
    }
}
/*
 * The May 3rd version of chatGPT helped fix the problem of black boxes 
 * appearing after clicking the New Question button. Fixed an issue where the size 
 * of the image did not fit the button. Fixed the problem that the button is not 
 * displayed after opening the program, and the button must be zoomed in or out to 
 * display the program.
 */