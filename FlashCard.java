//--== CS400 File Header Information ==--
//Name: Huiqian Jing
//Email: hjing6@wisc.edu
//Team: JD
//TA: Harper
//Lecturer: Florian Heimerl
//Date: 11/24/2020
import java.util.Random;

/*
 * This class represent a Flash card, which will randomly generate questions
 */
public class FlashCard {

    private String question;//randomly generated based on fields and element
    private String correctAnswer; //randomly generated based on fields and element
    private String userAnswer;
    private boolean correct;
    private Element element; //randomly generated
    private String[] fields = {"Chemical Symbol","Chemical Name","Atomic Number"};
    private int randomField;
    
    public FlashCard(){
      Random rand = new Random();
      int randomA = rand.nextInt(118) + 1;
      element = Element.find(randomA);
      randomField = rand.nextInt(fields.length);   
      setQuestion(randomField);
      setCorrectAnswer(randomField);
    }

    public Element getElement() {
      return element;
    }
    
    public String getCorrectAnswer(){
      return correctAnswer;
    }

    public String getQuestion(){
      return question;
    }

    public void setCorrectAnswer(int r){
      correctAnswer = element.getField(fields[r]);
    }
    
    public int getRandomField() {
      return randomField;
    }
    
    public void setQuestion(int r){
      String elementInfo = "";
      
      for(int i =0; i < fields.length; i++) {
        if(i==r) {
          elementInfo = elementInfo + fields[i] + ":???" + "\n";
        }
        else {
          elementInfo = elementInfo + fields[i] + ":" + element.getField(fields[i])+ "\n";
        }
      }
          
      question = elementInfo + "What is this element's " + fields[r] + " ?";
    }
    
    public String displayElement() {
      String elementInfo = "";
      for(int i =0; i < fields.length; i++) {
        elementInfo = elementInfo + fields[i] + ":" + element.getField(fields[i])+ "\n";
      }
      return elementInfo;
    }

    public String getUserAnswer() {
      return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
      this.userAnswer = userAnswer;
    }

    public boolean isCorrect() {
      return correct;
    }

    public void setCorrect(boolean correct) {
      this.correct = correct;
    }

   
    
    
    

}
