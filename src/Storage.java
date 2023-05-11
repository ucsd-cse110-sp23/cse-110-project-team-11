import java.util.Enumeration;
import java.lang.Object;
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

     public void setQnA(String Question, String Answer) {
        /* put value in table
         * use currentSize to index ArrayList.
         */
        if (Question == null || Answer == null) {
            return;
        }
        Integer currentIdx = new Integer(currentSize);
        questions.put(Question, currentIdx);
        answers.add(Answer);
        currentSize++;
     }

     public String getQnA (String Question) {
        /* return a particular answer, given a
         * past query.
         */
        Integer index = questions.get(Question);
        String answer = (index == null) ? "Error: no answer for question stored." : answers.get(index.intValue());
        return answer; 
     }

     // Return size of ArrayList( number of Questions w/ answers)
     public int getSize () {
        return currentSize;
     }
}