//--== CS400 File Header Information ==--
//Name: Huiqian Jing
//Email: hjing6@wisc.edu
//Team: JD
//TA: Harper
//Lecturer: Florian Heimerl
//Date: 12/01/2020
//userName field added by XuehengWang
import java.util.LinkedList;
/**
 * This class serves as back end, it can generate a new card,
 * and can keep track of all questions the user answered and the current score
 * @author Huiqian Jing
 *
 */
public class player {
  private LinkedList<FlashCard> cardsHistory; //store all the cards the user have answered
  private FlashCard card; // the current card
  private int score;// store the current score of the user
  private String userName;//name of the player
  
  public player() {
    cardsHistory = new LinkedList<FlashCard> ();
    score = 0;   
  }
  
  public void setName(String userName) {
    this.userName = userName;
  }
  
  public String getName() {
    return userName;
  }
  
  public int getScore() {
    return score;
  }
  
  public FlashCard generateCard() {
    card = new FlashCard();
    return card;
  }
  
  public FlashCard getCurrentCard() {
    return card;
  }
  
  //display it if the user wish to check
  public LinkedList<FlashCard> getAllCards(){
    return cardsHistory;
  }
  
  //check the answer, and update the score and the cards history
  public boolean checkAnswer(String userInput) {
    cardsHistory.add(card);
    if(card.getCorrectAnswer().equalsIgnoreCase(userInput)) {
      score+=1;
      card.setCorrect(true);
      return true;
    }
    else {
      score-=1;
      card.setCorrect(false);
      return false;
      }
  }
  
  

}