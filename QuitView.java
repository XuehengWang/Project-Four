import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/*
 * This class extends VBox class, and will display the score and all 
 * questions the user have been asked
 */

public class QuitView extends BorderPane{
  private player currentPlayer;
  private PlayersHistory playerList;
  
  public QuitView(player p, PlayersHistory playerList) {
    this.currentPlayer = p;
    this.playerList=playerList;
    this.addComponents();
  }
  
  public void addComponents() {
    Label score = new Label("Score: " + currentPlayer.getScore());
    score.getStyleClass().add("title");
    VBox answerVbox = new VBox();
    VBox correctnessVbox = new VBox();
    answerVbox.getStyleClass().add("box");
    correctnessVbox.getStyleClass().add("box");
    Button restart = new Button("Restart");
    HBox combined = new HBox(answerVbox,correctnessVbox,restart);
    combined.getStyleClass().add("box");
    combined.setAlignment(Pos.CENTER);
    currentPlayer.getAllCards().forEach(card -> {
      Label cardhistory = new Label();
      cardhistory.getStyleClass().add("history");
      cardhistory.setText(card.getElement().toString()+" ");
      answerVbox.getChildren().add(cardhistory);
      Label correctness = new Label();
      correctness.getStyleClass().add("correctness");
      if(card.isCorrect()) {
        correctness.setText("correct");
        correctness.setTextFill(Color.GREEN);
      }
      else {
        correctness.setText("incorrect");
        correctness.setTextFill(Color.RED);
      }
      correctnessVbox.getChildren().add(correctness);
    });
   
    this.setTop(score);
    this.setAlignment(score, Pos.CENTER);
    this.setMargin(score, new Insets(20,30,20,30));
    this.setCenter(combined);
    this.setMargin(combined, new Insets(10,20,10,20));
    restart.setOnAction(event -> {
      Label empty = new Label();
      this.setTop(empty);
      Menu menu = new Menu(playerList);
      this.setCenter(menu);
    });
  }
  

}