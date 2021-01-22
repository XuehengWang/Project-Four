import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class CardView extends BorderPane {
  private player currentPlayer;
  private PlayersHistory playerList;

  public CardView(player p, PlayersHistory playerList) {
    this.currentPlayer = p;
    this.playerList = playerList;
    this.addComponents();
  }

  public void addComponents() {
    Button checkAnswer = new Button("Check Answer");
    Button hint = new Button ("Hint");
    hint.getStyleClass().add("hintButton");
    FlashCard card = currentPlayer.generateCard();
    TextField textField = new TextField();
    String question = card.getQuestion();
    Label questionLabel = new Label(question);
    questionLabel.getStyleClass().add("question");
    VBox vbox = new VBox();
    vbox.getStyleClass().add("box");
    vbox.getChildren().add(questionLabel);
    vbox.getChildren().add(textField);
    vbox.getChildren().add(checkAnswer);
    vbox.getChildren().add(hint);
    this.setCenter(vbox);
    this.setMargin(vbox, new Insets(10,20,10,20));
    vbox.setAlignment(Pos.CENTER);
    checkAnswer.setOnAction(event -> {
      Label empty = new Label();
      this.setBottom(empty);
      String userInput = textField.getText();
      card.setUserAnswer(userInput);
      AnswerView answerView = new AnswerView(currentPlayer, playerList);
      this.setCenter(answerView);
      
    });
    hint.setOnAction(event ->{
      WebView webview = new WebView();
      WebEngine engine = webview.getEngine();
      engine.load("http://pages.cs.wisc.edu/~xueheng/elementInfo.html");
      this.setBottom(webview);
      this.setAlignment(webview, Pos.CENTER);
    });
    

  }

}
