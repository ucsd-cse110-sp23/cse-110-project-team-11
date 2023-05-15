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
    private JTextArea question;
    private JsonStorage storage;
    private HistoryList list;

    NewQuestionButton(JTextArea answerText, JTextArea questionText, JsonStorage storage, HistoryList hl) {
        this.answer = answerText;
        this.question = questionText;
        this.storage = storage;
        this.list = hl;
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
                toggleIcon();
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

    private void toggleIcon() throws IOException, JSONException, InterruptedException{
        // If the icon is not currently visible, set it as the button's icon.
        // If the icon is currently visible, remove it and set the button's text back to "New Question".
        NewQuestion newQ = new NewQuestion();
        if (!isIconVisible) {
            newQuestion.setIcon(icon);
            newQuestion.setText("");
            newQ.newQuestionStart();
            isIconVisible = true;
        } else {
            newQuestion.setIcon(null);
            newQuestion.setText("New Question");
            newQ.newQuestionEnd(storage);

            //access the last question
            int lastEntry = storage.getHistoryPrompt().size() - 1;
            String newQuestion = storage.getQuestion(lastEntry);
            String newAnswer = storage.getAnswer(lastEntry);
            list.addEntry(newQuestion, newAnswer);
            question.setText(newQuestion);
            answer.setText(newAnswer);
            answer.revalidate();
            answer.repaint();
            list.refresh();
            isIconVisible = false;
        }
    }

    public JButton getNewQuestionButton() {
        return this.newQuestion;
    }
}