// import java.io.IOException;

// import javax.swing.JList;
// import javax.swing.JTextArea;

// import org.bson.Document;
// import org.json.JSONException;

// public class VoiceCommands {
//     private Whisper whisper = new Whisper();
//     //private String whisperArg = "/Users/peikexu/Documents/Ucsd/CSE/CSE110/cse-110-project-team-11/src/myAudio.mp3";
//     private String whisperArg = "myAudio.mp3";
    
//     String question;
//     String firstWord = "";
//     String secondWord = "";
//     String thirdWord = "";
    
//     String user_email;

//     JTextArea answerArea;
//     JTextArea questionArea;
//     HistoryList hl;
//     JsonStorage js;
//     JList<String> questionList;
//     EmailStorage store;

//     /*
//      * Constructor
//      */
//     public VoiceCommands(JTextArea answerText, JTextArea questionText, JsonStorage storage, HistoryList hl, Whisper whisper, JList<String> historyList, EmailStorage store, String user_email) throws IOException {
//         // question = whisper.getTranscript(whisperArg);
        
//         this.answerArea = answerText;
//         this.questionArea = questionText;
//         this.hl = hl;
//         this.js = storage;
//         this.questionList = historyList;
//         this.store = store;
//         this.user_email = user_email;
//     }

//     /*
//      * Set first words to be accessible
//      */
//     public void processTranscript(String transcript) throws IOException {
        
//         transcript = whisper.getTranscript(whisperArg);
//         String[] words = transcript.split(" ");

//         if (words.length >= 1) {
//             this.firstWord = words[0];
//         }
//         if (words.length >= 2) {
//             this.secondWord = words[1];
//         }
//         if (words.length >= 3) {
//             this.thirdWord = words[2];
//         }
//     }

//     /*
//      * Call respective commands based on the first words of the transcript
//      */
//     public String callCommands(ChatGPT chatGPT) throws JSONException, IOException, InterruptedException {
//         String value = "invalid";
        
//         processTranscript(question);

//        if (firstWord.equalsIgnoreCase("Question") || firstWord.equalsIgnoreCase("Question,") || firstWord.equalsIgnoreCase("Question.")) {
//             //question();
//             value = "chatgpt";
//        } else if (firstWord.equalsIgnoreCase("Delete") && secondWord.equalsIgnoreCase("prompt.")) {
//             value = "non-chatgpt";
//             deletePrompt();
//        } else if (firstWord.equalsIgnoreCase("Clear") && secondWord.equalsIgnoreCase("all.")) {
//             value = "non-chatgpt";
//             clearAll();
//        } else if (firstWord.equalsIgnoreCase("Create") && secondWord.equalsIgnoreCase("email")) {
//             value = "chatgpt";
//             createEmail(chatGPT);
//        } else if (firstWord.equalsIgnoreCase("Send") && secondWord.equalsIgnoreCase("email.")) {
//             value = "non-chatgpt";
//             sendEmail();
//        } else if (firstWord.equalsIgnoreCase("Set") && secondWord.equalsIgnoreCase("up") && thirdWord.equalsIgnoreCase("email.") || ((firstWord.equalsIgnoreCase("Setup")) && (secondWord.equalsIgnoreCase("email.")))) {
//             value = "non-chatgpt";
//             setUpEmail();
//         } 

//         return value;
//     }


//     public void question() throws JSONException, IOException, InterruptedException {
//         // TODO: figure out design pattern
//     }

//     /*
//      * Delete prompt voice command to delete a prompt when selected
//      */
//     public void deletePrompt() {
//         int selectedIndex = questionList.getSelectedIndex();
//         if (selectedIndex != -1) { // if some question is selected
//             hl.deleteEntry();
            
//             // remove from the json storage
//             js.removePrompt(selectedIndex);
//             hl.refresh();
//             try {
//                 js.writeJson("historyPrompt.json");
//             } catch (IOException ex) {
//                 ex.printStackTrace();
//             }
//         }
//     }

//     /*
//      * Voice command "Clear All" clears everything in history prompt
//      */
//     public void clearAll() {
//         hl.pastQuestions.clear();
//         hl.pastAnswers.clear();
//         hl.dlm.clear();
//         hl.answerTextArea.setText("");
//         js.clearPrompt();
//         try {
//             js.writeJson("historyPrompt.json");
//         } catch (IOException ex) {
//             ex.printStackTrace();
//         }
//     }

//     public void setUpEmail() throws IOException {  
//         SetUpEmail setup = new SetUpEmail(store, user_email);
//         setup.setVisible(true);
//     }

//     /*
//      * Voice command "Create email" asks ChatGPT to create an email and show display name
//      */
//     public void createEmail(ChatGPT chatGPT) {
//         String email = chatGPT.getAnswer();
//         System.out.println("before: " + email);
//         String displayName = "Helen Keller";
        
//         // handle case-sensitive cases
//         email = email.replace("[your name]", displayName);
//         email = email.replace("[Your name]", displayName);
//         email = email.replace("[Your Name]", displayName);
//         email = email.replace("[name]", displayName);
//         email = email.replace("[Name]", displayName);
        
//         chatGPT.setAnswer(email);
//     }

//     public void sendEmail() {
//         //TODO, not us
//     }

//     /*
//      * Get first word
//      */
//     public String getFirstWord() {
//         return firstWord;
//     }

//     /*
//      * Get second word
//      */
//     public String getSecondWord() {
//         return secondWord;
//     }

//     /*
//      * Get third word
//      */
//     public String getThirdWord() {
//         return thirdWord;
//     }
// }


// version with send email
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

public class VoiceCommands {
    private Whisper whisper = new Whisper();
    //private String whisperArg = "/Users/peikexu/Documents/Ucsd/CSE/CSE110/cse-110-project-team-11/src/myAudio.mp3";
    private String whisperArg = "myAudio.mp3";
    
    String question;
    String firstWord = "";
    String secondWord = "";
    String thirdWord = "";
    
    String user_email;

    JTextArea answerArea;
    JTextArea questionArea;
    HistoryList hl;
    JsonStorage js;
    JList<String> questionList;
    EmailStorage store;
    String name;
    String displayName;
    String to;
    String SMTP;
    String TLS;
    String text;

    String apiKey;
    String subject;

    /*
     * Constructor
     */
    public VoiceCommands(JTextArea answerText, JTextArea questionText, JsonStorage storage, HistoryList hl, Whisper whisper, JList<String> historyList, EmailStorage store, String user_email) throws IOException {
        // question = whisper.getTranscript(whisperArg);
        
        this.answerArea = answerText;
        this.questionArea = questionText;
        this.hl = hl;
        this.js = storage;
        this.questionList = historyList;
        this.store = store;
        this.user_email = user_email;

        this.apiKey = "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";
        // this.name = store.getName();
        // this.displayName = store.getDisplayName();
        // this.to = store.getEmail();
        // this.SMTP = store.getSMTP();
        // this.TLS = store.getTLS();
        this.text = "a";
        this.subject = "cse110";

    }

    /*
     * Set first words to be accessible
     */
    public void processTranscript(String transcript) throws IOException {
        
        transcript = whisper.getTranscript(whisperArg);
        String[] words = transcript.split(" ");

        if (words.length >= 1) {
            this.firstWord = words[0];
        }
        if (words.length >= 2) {
            this.secondWord = words[1];
        }
        if (words.length >= 3) {
            this.thirdWord = words[2];
        }

        // // get to email address
        // if (transcript.contains("Send email to")){
        //     String emailAddr = transcript.substring(14, transcript.length());
        //     // need to remove spaces, to lower case, replace "at" with @, replace "dot" with .
        //     emailAddr = emailAddr.replaceAll(" ", "");
        //     emailAddr.toLowerCase();
        //     emailAddr = emailAddr.replace("at", "@");
        //     emailAddr = emailAddr.replace("dot", ".");
        //     System.out.println("send email to: " + emailAddr);
        // }
    }

    /*
     * Call respective commands based on the first words of the transcript
     */
    public String callCommands(ChatGPT chatGPT) throws JSONException, IOException, InterruptedException {
        String value = "invalid";
        
        processTranscript(question);

       if (firstWord.equalsIgnoreCase("Question") || firstWord.equalsIgnoreCase("Question,") || firstWord.equalsIgnoreCase("Question.")) {
            //question();
            value = "chatgpt";
       } else if (firstWord.equalsIgnoreCase("Delete") && secondWord.equalsIgnoreCase("prompt.")) {
            value = "non-chatgpt";
            deletePrompt();
       } else if (firstWord.equalsIgnoreCase("Clear") && secondWord.equalsIgnoreCase("all.")) {
            value = "non-chatgpt";
            clearAll();
       } else if (firstWord.equalsIgnoreCase("Create") && secondWord.equalsIgnoreCase("email")) {
            value = "chatgpt";
            createEmail(chatGPT);
            //textSetter(chatGPT);
       } else if (firstWord.equalsIgnoreCase("Send") && secondWord.equalsIgnoreCase("email")) {
            value = "non-chatgpt";
            textSetter(chatGPT);
            sendEmail();
       } else if ((firstWord.equalsIgnoreCase("Set") && secondWord.equalsIgnoreCase("up") && thirdWord.equalsIgnoreCase("email.")) || (firstWord.equalsIgnoreCase("Setup") && secondWord.equalsIgnoreCase("email.") )) {
            value = "non-chatgpt";
            setUpEmail();
        } 

        return value;
    }


    public void question() throws JSONException, IOException, InterruptedException {
        // TODO: figure out design pattern
    }

    /*
     * Delete prompt voice command to delete a prompt when selected
     */
    public void deletePrompt() {
        int selectedIndex = questionList.getSelectedIndex();
        if (selectedIndex != -1) { // if some question is selected
            hl.deleteEntry();
            
            // remove from the json storage
            js.removePrompt(selectedIndex);
            hl.refresh();
            try {
                js.writeJson("historyPrompt.json");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
     * Voice command "Clear All" clears everything in history prompt
     */
    public void clearAll() {
        hl.pastQuestions.clear();
        hl.pastAnswers.clear();
        hl.dlm.clear();
        hl.answerTextArea.setText("");
        js.clearPrompt();
        try {
            js.writeJson("historyPrompt.json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setUpEmail() throws IOException {  
        SetUpEmail setup = new SetUpEmail(store, user_email);
        setup.setVisible(true);
        //store = setup.getEmailH
    }

    public void textSetter(ChatGPT chatGPT) throws IOException{
        createEmail(chatGPT);
        this.text = chatGPT.getAnswer();
        System.out.println("email content: " + text);
        this.subject = chatGPT.getQuestion();
    }

    /*
     * Voice command "Create email" asks ChatGPT to create an email and show display name
     */
    public void createEmail(ChatGPT chatGPT) throws IOException {
        String email = chatGPT.getAnswer();
        
        //System.out.println("before: " + email);
        //String displayName = "Helen Keller";

        if(store.getEmailHost() == new JSONObject()) {
            this.displayName = "myName";
        }
        else {
            this.name = store.getName();
            this.displayName = store.getDisplayName();
        }
        // this.name = store.getName();
        // this.displayName = store.getDisplayName();
        
        // handle case-sensitive cases
        email = email.replace("[your name]", displayName);
        email = email.replace("[Your name]", displayName);
        email = email.replace("[Your Name]", displayName);
        email = email.replace("[name]", displayName);
        email = email.replace("[Name]", displayName);
        
        chatGPT.setAnswer(email);

        System.out.println("email content: " + email);

        //textSetter(chatGPT);

        
    }

    public void sendEmail() {

        
        
         if (store.empty()) {
            JOptionPane.showMessageDialog(null, "Empty host");
            return;
         }

         System.out.println(store.getTLS());
         try{
            if(store.getTLS().equals("587")){ 
                this.to = store.getEmail();
                this.SMTP = store.getSMTP();
                this.TLS = store.getTLS();
        
            String sampleEmail = "renahu2020@gmail.com";
            //EmailSender sender = new EmailSender(SMTP, TLS, sampleEmail, to, text);

            //sender.send();
                    EmailServiceImpl host = new EmailServiceImpl(apiKey);
                    host.sendEmail(to,subject,text);
            }
            else{
                throw new Exception("invalid port number");
            }
            
         } catch (Exception e) {
            EmailErrorHandlingUI errorUI = new EmailErrorHandlingUI();
            errorUI.displayError(e);
         }
    }

    /*
     * Get first word
     */
    public String getFirstWord() {
        return firstWord;
    }

    /*
     * Get second word
     */
    public String getSecondWord() {
        return secondWord;
    }

    /*
     * Get third word
     */
    public String getThirdWord() {
        return thirdWord;
    }

}
