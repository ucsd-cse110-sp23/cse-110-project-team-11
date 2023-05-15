package mocks;

public interface IMockC{
    public String getQuestion();
    public String getResult(String question);
}

/* 
class MockChat  implements IMockC{
    public String getQuestion(){
        return "What is day is today?";
    } 

    public String getResult(String question){
        return "Valid answer";
    }
}
*/

/*
 * Syntax to create mock object 
 * 
 * void somemethod(IMockC obj){
 *  //For MockChat 
 *  String q = obj.getQuestion();
 *  String ans = obj.getResult();
 * }
 */
