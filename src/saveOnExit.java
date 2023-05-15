
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat.Style;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class saveOnExit {

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