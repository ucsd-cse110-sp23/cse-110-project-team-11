package mocks;

public class MockChat  implements IMockC{
    public String getQuestion(){
        return "What is day is today?";
    } 

    public String getResult(String question){
        return "Valid answer";
    }
}
