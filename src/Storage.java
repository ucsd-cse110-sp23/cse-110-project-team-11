import java.util.Hashtable;
import java.util.ArrayList;

public class Storage {
    
    /* Use dictionary to store <string
     * , string> pairs, where K = question
     * and V = chatGPT generated response.
     */

     // 'has a' dicitonary and ArrayList.
     Hashtable<String, Integer> questions = new Hashtable<String, Integer>();
     ArrayList<String> answers = new ArrayList<String>();
     int currentSize = 0;

     public void setQnA(String question, String answer) {
        /* put value in table
         * use currentSize to index ArrayList.
         */
        if (question == null || answer == null) {
            return;
        }
        Integer currentIdx = new Integer(currentSize);
        questions.put(question, currentIdx);
        answers.add(answer);
        currentSize++;
     }

     public String getAns (String question) {
        if (question == null) {
            return "Invalid input.";
        }
        /* return a particular answer, given a
         * past query.
         */
        Integer index = questions.get(question);
        String answer = (index == null) ? "Error: no answer for question stored." : answers.get(index.intValue());
        return answer; 
     }

     // Return size of ArrayList( number of Questions w/ answers)
     public int getSize () {
        return currentSize;
     }

     //TODO: deleteQuestion, deleteAnswer
     //TODO: Test cases for deleteQ and deleteA
}