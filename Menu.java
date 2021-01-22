// --== CS400 File Header Information ==--
// Name: Xueheng Wang
// Email: xwang2395@wisc.edu
// Team: JD
// Role: test engineer 1 
// TA: Harper
// Lecturer: Gary Dahl
// Notes to Grader: This class is originally written by Huiqian Jing, and I added the field of
// playerList and its function to keep track of the history scores of multiple players and added
// some style information.

import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/*
 * This class serves as the menu
 */
public class Menu extends BorderPane {
  private PlayersHistory playerList;

  public Menu(PlayersHistory playerList) {
    this.getStyleClass().add("borderPane");
    this.playerList = playerList;
    addComponents();
  }

  public void addComponents() {
    VBox vbox = new VBox();
    vbox.getStyleClass().add("box");
    Button start = new Button("Start");
    Button playersHistory = new Button("History scores");
    playersHistory.getStyleClass().add("historyBotton");
    Button exit = new Button("Exit");
    Label title = new Label("Chemical Element Flash Card");
    title.getStyleClass().add("title");
    TextField textField = new TextField();
    Label name = new Label("Type your name and start!");
    name.getStyleClass().add("question");
    vbox.getChildren().add(title);
    vbox.getChildren().add(name);
    vbox.getChildren().add(textField);
    vbox.getChildren().add(start);
    vbox.getChildren().add(exit);
    vbox.getChildren().add(playersHistory);
    player p = new player();
    start.setOnAction(event -> {
      String userName = textField.getText();
      p.setName(userName);
      playerList.addPlayer(p);
      CardView cardView = new CardView(p, playerList);
      this.setCenter(cardView);
      this.getStylesheets().add("style.css");
      Label empty = new Label();
      this.setBottom(empty);
    });
    playersHistory.setOnMousePressed(event -> {
      playersHistory.setTextFill(Color.BLUE);
    });
    playersHistory.setOnAction(event -> {
      String playerHistory = playerList.printPlayerHistory();
      Label history = new Label(playerHistory);
      history.getStyleClass().add("history");
      this.setBottom(history);
      this.setMargin(history, new Insets(10, 20, 10, 20));
      this.setAlignment(history, Pos.CENTER);
    });
    exit.setOnAction(event -> {
      Platform.exit();
    });
    vbox.setAlignment(Pos.CENTER);
    this.setCenter(vbox);
    this.setMargin(vbox, new Insets(10, 20, 10, 20));


  }



}
