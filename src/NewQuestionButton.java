import javax.swing.*;
import javax.swing.border.*;

import org.bson.json.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



class NewQuestionButton extends JPanel {

    private JButton newQuestion;
    private boolean isIconVisible = false;
    private ImageIcon icon;
    private JTextArea answer;
    private JTextArea question;
    private JsonStorage storage;
    private HistoryList list;
    private JList historyList;

    NewQuestionButton(JTextArea answerText, JTextArea questionText, JsonStorage storage, HistoryList hl, JList<String> list, String email, EmailStorage store) {
        this.answer = answerText;
        this.question = questionText;
        this.storage = storage;
        this.list = hl;
        this.historyList = list;

        setPreferredSize(new Dimension(400, 60));
        setBackground(new Color(0, 0, 0, 0)); // set background color to transparent

        // Create the button.
        newQuestion = new JButton("Start");
        newQuestion.setPreferredSize(new Dimension(100, 60)); // set the size of the button
        newQuestion.setFont(new Font("Sans-serif", Font.ITALIC, 10));

        // Add the button to the panel.
        add(newQuestion);

        // Add an action listener to the button.
        newQuestion.addActionListener(e -> {
            try {
                toggleIcon(email);

                //String history = storage.getHistoryPrompt().toString();


                // ArrayList<JSONObject> historyPrompt = storage.getHistoryPrompt();
                // JSONArray jsonArray = new JSONArray();
                // for (JSONObject obj : historyPrompt) {
                //     jsonArray.put(obj);
                // }
                // JSONObject history = new JSONObject();
                // history.put("historyPrompt", jsonArray);
                
                // URL url = new URL(URL);
                // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // conn.setRequestMethod("PUT");
                // conn.setDoOutput(true);
                // OutputStreamWriter out = new OutputStreamWriter(
                //     conn.getOutputStream()
                // );
                // out.write(email + ',' + history.toString());
                // out.flush();
                // out.close();


                // BufferedReader in = new BufferedReader(
                //     new InputStreamReader(conn.getInputStream())
                // );
                // String response = in.readLine();
                // in.close();
                // JOptionPane.showMessageDialog(null, response);
            }
            catch (JSONException | IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        );

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

    private void toggleIcon(String email) throws IOException, JSONException, InterruptedException{
        String URL = "http://localhost:8100/";
        // If the icon is not currently visible, set it as the button's icon.
        // If the icon is currently visible, remove it and set the button's text back to "New Question".
        NewQuestion newQ = new NewQuestion(answer, question, storage, list, historyList, store, user_email);
        if (!isIconVisible) {
            newQuestion.setIcon(icon);
            newQuestion.setText("");
            newQ.newQuestionStart();
            isIconVisible = true;
        } else {
            newQuestion.setIcon(null);
            newQuestion.setText("Start");
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

            ArrayList<JSONObject> historyPrompt = storage.getHistoryPrompt();
                JSONArray jsonArray = new JSONArray();
                for (JSONObject obj : historyPrompt) {
                    jsonArray.put(obj);
                }
                JSONObject history = new JSONObject();
                history.put("historyPrompt", jsonArray);
                
                URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream()
                );
                out.write(email + ',' + history.toString());
                out.flush();
                out.close();

                BufferedReader in = new BufferedReader(
              new InputStreamReader(conn.getInputStream())
            );
            String response = in.readLine();
            in.close();
            //JOptionPane.showMessageDialog(null, response);
        }
    }

    // helper method for creating a question

    public JButton getNewQuestionButton() {
        return this.newQuestion;
    }
}
