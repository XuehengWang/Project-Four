// --== CS400 File Header Information ==--
// Name: Xueheng Wang
// Email: xwang2395@wisc.edu
// Team: JD
// Role: test engineer 1
// TA: Harper
// Lecturer: Gary Dahl
// Date: 11/24.2020 - 12/01/2020 after the back end developers updated

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 * This Class is to test the if the code of back end of Periodic Table Review Card application runs
 * correctly.
 * 
 * @author Xueheng Wang
 *
 */
class TestBackEnd {

  /**
   * Load the periodic table before the tests
   */
  @BeforeAll
  public static void loadElement() {
    try {
      Element.loadData();
    } catch (FileNotFoundException e1) {
      fail("Catch FileNotFoundException");
      System.out.println(e1.getStackTrace());
    } catch (IOException e2) {
      fail("Catch IOException");
      System.out.println(e2.getStackTrace());
    }
  }

  /**
   * Test if the Element class function as we expected.
   */
  @Test
  public void testElement() {

    Element element1 = Element.putNamefindElement.get("Hydrogen");// get hydrogen from hash table
                                                                  // map
    assertEquals(Element.find(element1.getAtomicNumber()), element1);// test if the find method can
                                                                     // get the correct element
                                                                     // using the atomic number
    Element element2 = Element.putSymbolfindElement.get("O");// get oxygen from hash table map
    assertEquals(Element.find(element2.getChemicalName()), element2);// test if the find method can
                                                                     // get the correct element
                                                                     // using the element name
    Element element3 = Element.putNumfindElement.get(6);// get carbon from the hash table map
    assertEquals(Element.find("C"), element3);// test if the find method can
                                              // get the correct element
                                              // using the element symbol
  }

  /**
   * Test if the FlashCard class function as we expected.
   */
  @Test
  public void testFlashCard() {
    FlashCard card = new FlashCard();
    Element element = card.getElement();
    int randomNumber = card.getRandomField();// get the random number representing the random
                                             // question
    // check if the generated answer matches one of this element's field represented by the randome
    // number
    if (randomNumber == 0) {
      assertEquals(card.getCorrectAnswer(), element.getChemicalSymbol());
    } else if (randomNumber == 1) {
      assertEquals(card.getCorrectAnswer(), element.getChemicalName());
    } else {
	assertEquals(card.getCorrectAnswer(), String.valueOf(element.getAtomicNumber()));
    }
    card.setQuestion(0);// intentionally set the question to ask the chemical symbol
    // check if the card asks the correct question about the chemical symbol
    assertEquals(card.getQuestion(),
        "Chemical Symbol:???\nChemical Name:" + element.getChemicalName() + "\nAtomic Number:"
            + element.getAtomicNumber() + "\nWhat is this element's Chemical Symbol ?");
  }

  /**
   * Test if the player class function as we expected.
   */
  @Test
  public void testPlayerClass() {
    player player = new player();
    // generate the first card
    FlashCard card1 = player.generateCard();
    String answer1 = card1.getCorrectAnswer();
    player.checkAnswer(answer1);// check with correct answer
    assertEquals(player.getScore(), 1);// check if one score is added
    assertEquals(card1.isCorrect(), true);// check if the card's correctness has been setted
    assertEquals(player.getAllCards().get(0), card1);
    // generate the second card
    FlashCard card2 = player.generateCard();
    assertEquals(player.getCurrentCard(), card2);
    player.checkAnswer("wrong answer");// check with incorrect answer
    assertEquals(player.getScore(), 0);// check if one score is deducted
    assertEquals(card2.isCorrect(), false);
    assertEquals(player.getAllCards().size(), 2);
    // generate the third card
    FlashCard card3 = player.generateCard();
    String answer3 = card3.getCorrectAnswer();
    player.checkAnswer(answer3);// check with correct answer again
    assertEquals(player.getScore(), 1);// check if one score is added
    assertEquals(card3.isCorrect(), true);
    assertEquals(player.getAllCards().get(2), card3);
  }

}
