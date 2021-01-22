// --== CS400 File Header Information ==--
// Name: Xueheng Wang
// Email: xwang2395@wisc.edu
// Team: JD
// Role: test engineer 1 
// TA: Harper
// Lecturer: Gary Dahl
// Date: 12/01/2020

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This class is to launch the Graphical User Interface (GUI). The start scene is a menu to allow
 * the players to start the game. The whole GUI will conform a uniform css style.
 */
public class startReview extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException, IOException {
    primaryStage.setTitle("Periodic Table Review Card");
    Element.loadData();
    PlayersHistory playerList = new PlayersHistory();
    Menu menu = new Menu(playerList);
    Scene startScene = new Scene(menu, 630, 500);
    startScene.getStylesheets().add("style.css");
    primaryStage.setScene(startScene);
    primaryStage.show();

  }



}
