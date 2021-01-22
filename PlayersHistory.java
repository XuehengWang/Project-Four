// --== CS400 File Header Information ==--
// Name: Xueheng Wang
// Email: xwang2395@wisc.edu
// Team: JD
// Role: test engineer 1 
// TA: Harper
// Lecturer: Gary Dahl
// Date: 12/01/2020

import java.util.LinkedList;

/**
 * 
 * @author Xueheng Wang 
 * 
 * This class is to store all the players who have played the review card game,
 * and all of the player history this class returns includes the player's name and their
 * final score.
 *
 */
public class PlayersHistory {

  
  public LinkedList<player> players;//Linked list storing all the players
  
  /**
   * Constructor of playersHistory
   */
  public PlayersHistory() {
    this.players = new LinkedList<player>();
  }

  /**
   * add a player to the players list
   * @param p player
   */
  public void addPlayer(player p) {
    players.add(p);
  }

  /**
   * get the score of the player in index i
   * @param i index
   * @return score of this player
   */
  private int getScore(int i) {
    return players.get(i).getScore();
  }

  /**
   * get the name of the player in index i
   * @param i index
   * @return name of this player
   */
  private String getName(int i) {
    return players.get(i).getName();
  }

  /**
   *  
   * @return a string representing all the players' information (name & score)
   */
  public String printPlayerHistory() {
    String history = "";
    history += "There are " + getNumOfPlayers() + " player(s).\n";
    for (int i = 0; i < players.size(); i++) {
      if (getScore(i) <= 0) {
        history += "Score of " + getName(i) + ": " + getScore(i) + ". More efforts needed!\n";
      } else {
        history += getName(i) + ": " + getScore(i) + ". Good work!\n";
      }
    }
    return history;
  }

  /**
   * 
   * @return the number of players in this player list
   */
  private int getNumOfPlayers() {
    return players.size();
  }

}
