import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AnswerView extends BorderPane {
  player currentPlayer;
  private PlayersHistory playerList;
  
  public AnswerView(player p, PlayersHistory playerList) {
    this.currentPlayer = p;
    this.playerList = playerList;
    this.addComponents();
  }

  public void addComponents() {
    FlashCard card = currentPlayer.getCurrentCard();
    Label answerOfElement = new Label(card.displayElement());
    answerOfElement.getStyleClass().add("question");
    Button next = new Button("Next Question");
    Button quit = new Button("Quit and Check Score");
    TextFlow textflow = new TextFlow(); 
    textflow.getStyleClass().add("question");
    if(currentPlayer.checkAnswer(card.getUserAnswer())) {
      Text text1 = new Text("Congratulations!\n");
      Text text2 = new Text("Your answer is correct!");
      text1.setFill(Color.GREEN);
      textflow.getChildren().add(text1);
      textflow.getChildren().add(text2);
    }
    else {
      Text text1 = new Text("Oops...\n");
      Text text2 = new Text("Your answer is incorrect...");
      text1.setFill(Color.RED);
      textflow.getChildren().add(text1);
      textflow.getChildren().add(text2);
    }
    
    VBox vbox = new VBox();
    vbox.getStyleClass().add("box");
    vbox.getChildren().add(textflow);
    vbox.getChildren().add(answerOfElement);
    vbox.getChildren().add(next);
    vbox.getChildren().add(quit);
    this.setCenter(vbox);
    this.setMargin(vbox, new Insets(10,20,10,20));
    vbox.setAlignment(Pos.CENTER);
    next.setOnAction(event -> {
      CardView cardView = new CardView(currentPlayer,playerList);
      this.setCenter(cardView);
      
    });
    quit.setOnAction(event2 -> {
      QuitView quitView = new QuitView(currentPlayer,playerList);
      this.setCenter(quitView);
    });
    
  }

}
