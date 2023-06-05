import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class SaveOnExit {

    public void saveQuestions(Hashtable<String,Integer> questions) {
        try{
            FileWriter fileWriter = new FileWriter("questions.txt", false);

            for (String s : questions.keySet()) {
              fileWriter.write(s + "\n");
            }
            fileWriter.close();
          }
          catch(IOException e){
              System.out.println("Saving Error: " + e.getMessage());

          }
    }


    public void saveAnswers(ArrayList<String> answers) {
        try{
            FileWriter fileWriter = new FileWriter("answers.txt", false);

            for (String s : answers) {
              fileWriter.write(s + "\n");
            }
            fileWriter.close();
          }
          catch(IOException e){
              System.out.println("Saving Error: " + e.getMessage());

          }

    }
}