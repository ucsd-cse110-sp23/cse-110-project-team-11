/* 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;

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
            /* 
            ImageIcon icon = new ImageIcon("/Users/hongyuan/Documents/GitHub/cse-110-project-team-11/src/redIcon.png");
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);
            newQuestion.setIcon(new ImageIcon(scaledImage));
            newQuestion.setText("");
            isIconVisible = true;
            */
            /* 
            try {
                //1B39XTjnEYdqXU_JIp9gRvT6naAX6uOxI
                URL url = new URL("https://drive.google.com/uc?export=download&id=1B39XTjnEYdqXU_JIp9gRvT6naAX6uOxI");
                ImageIcon icon = new ImageIcon(url);
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);
                newQuestion.setIcon(new ImageIcon(scaledImage));
                newQuestion.setText("");
                isIconVisible = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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
*/
/*
 * The May 3rd version of chatGPT helped fix the problem of black boxes 
 * appearing after clicking the New Question button. Fixed an issue where the size 
 * of the image did not fit the button. Fixed the problem that the button is not 
 * displayed after opening the program, and the button must be zoomed in or out to 
 * display the program.
 * 
 */

 /* 
 import javax.swing.*;
import java.awt.*;
import java.net.URL;

class Footer extends JPanel {

    private JButton newQuestion;
    private boolean isIconVisible = false;
    private ImageIcon icon;

    Footer() {
        setPreferredSize(new Dimension(400, 60));
        setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

        newQuestion = new JButton("New Question");
        newQuestion.setPreferredSize(new Dimension(100, 60)); // set the size of the button

        newQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10));

        add(newQuestion);

        newQuestion.addActionListener(e -> toggleIcon());

        
        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                URL url = new URL("https://drive.google.com/uc?export=download&id=1B39XTjnEYdqXU_JIp9gRvT6naAX6uOxI");
                ImageIcon icon = new ImageIcon(url);
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }

            @Override
            protected void done() {
                try {
                    
                    icon = get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private void toggleIcon() {
        if (!isIconVisible) {
            newQuestion.setIcon(icon);
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
*/

import javax.swing.*;

import org.json.JSONException;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

class NewQuestionButton extends JPanel {

    private JButton newQuestion;
    private boolean isIconVisible = false;
    private ImageIcon icon;
    private JTextArea answer;
    private JsonStorage storage;
    private HistoryList list;
    //private JTextArea answerArea;

    NewQuestionButton(JTextArea answerText, JsonStorage storage, HistoryList hl) {
        this.answer = answerText;
        this.storage = storage;
        this.list = hl;
        //this.answerArea = answerArea;
        // Set the size and background color of the panel.
        setPreferredSize(new Dimension(400, 60));
        setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

        // Create the button.
        newQuestion = new JButton("New Question");
        newQuestion.setPreferredSize(new Dimension(100, 60)); // set the size of the button
        newQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10));

        // Add the button to the panel.
        add(newQuestion);

        // Add an action listener to the button.
        newQuestion.addActionListener(e -> {
            try {
                toggleAndClear();
            } catch (JSONException | IOException | InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Create a SwingWorker to load the image in a background thread.
        //From ChaTGPT May 3rd version. Modify the ToggleIcon and add some methods to
        //be able to load the image in advance.
                new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                // Load the image from the URL.
                URL url = new URL("https://drive.google.com/uc?export=download&id=1B39XTjnEYdqXU_JIp9gRvT6naAX6uOxI");
                ImageIcon icon = new ImageIcon(url);
                Image image = icon.getImage();
                
                // Scale the image to the size of the button.
                Image scaledImage = image.getScaledInstance(newQuestion.getWidth(), newQuestion.getHeight(), Image.SCALE_SMOOTH);
                
                // Return the scaled image as an ImageIcon.
                return new ImageIcon(scaledImage);
            }

            @Override
            protected void done() {
                try {
                    // When the image has been loaded, store it in the 'icon' field.
                    icon = get();
                } catch (Exception e) {
                    // If an error occurs while loading the image, print the stack trace.
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    
    public void clearAnswer() {
        answer.setText(" ");
    }

    public JTextArea getAnswerArea() {
        return this.answer;
    }

    public void toggleAndClear() throws IOException, JSONException, InterruptedException {
        toggleIcon();
        clearAnswer();
    }

    private void toggleIcon() throws IOException, JSONException, InterruptedException{
        // If the icon is not currently visible, set it as the button's icon.
        // If the icon is currently visible, remove it and set the button's text back to "New Question".
        NewQuestion newQ = new NewQuestion();
        if (!isIconVisible) {
            newQuestion.setIcon(icon);
            newQuestion.setText("");
            //NewQuestion newQ = new NewQuestion();
            newQ.newQuestionStart();
            isIconVisible = true;
        } else {
            newQuestion.setIcon(null);
            newQuestion.setText("New Question");
            newQ.newQuestionEnd(storage);
            list.refresh();
            isIconVisible = false;
        }
    }

    public JButton getNewQuestionButton() {
        return this.newQuestion;
    }
}

// class AppFrame extends JFrame {

//     private Footer footer;

//     AppFrame() {
//         // Set the size of the frame and specify that the application should exit when the frame is closed.
//         setSize(400, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//         // Set the layout manager and background color of the frame.
//         setLayout(new BorderLayout());
//         getContentPane().setBackground(new Color(0, 0, 0, 0)); // set the background color to transparent
    
//         // Create the footer panel and add it to the frame.
//         footer = new Footer();
//         add(footer, BorderLayout.SOUTH);

//         // Make the frame visible.
//         setVisible(true);
//     }
    
// }

// public class NewQuestionButton {

//     public static void main(String[] args) {
//         // Create and display the application frame.
//         new AppFrame();
//     }
// }

