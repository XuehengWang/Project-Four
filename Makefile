run: startReview.class test
	 java --module-path JavaFX/ --add-modules javafx.controls,javafx.fxml,javafx.web startReview

startReview.class: Menu.class
	javac --module-path JavaFX/ --add-modules javafx.controls,javafx.fxml,javafx.web startReview.java

Menu.class: CardView.class
	javac --module-path JavaFX/ --add-modules javafx.controls,javafx.fxml,javafx.web Menu.java

CardView.class: AnswerView.class
	javac --module-path JavaFX/ --add-modules javafx.controls,javafx.fxml,javafx.web CardView.java

AnswerView.class: QuitView.class
	javac --module-path JavaFX/ --add-modules javafx.controls,javafx.fxml,javafx.web AnswerView.java

QuitView.class:PlayersHistory.class
	javac --module-path JavaFX/ --add-modules javafx.controls,javafx.fxml,javafx.web QuitView.java

PlayersHistory.class:player.class
	javac PlayersHistory.java

player.class: FlashCard.class
	javac player.java

FlashCard.class: Element.class
	javac FlashCard.java

Element.class:HashTableMap.class
	javac Element.java

HashTableMap.class: MapADT.class
	javac HashTableMap.java

MapADT.class:
	javac MapADT.java

test: TestBackEnd.class
	java -jar junit5.jar --class-path . --scan-classpath --details tree

TestBackEnd.class:
	javac -cp .:junit5.jar TestBackEnd.java


clean:
	$(RM) *.class
