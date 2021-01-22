import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//--== CS400 File Header Information ==--
//Name: Huiqian Jing
//Email: hjing6@wisc.edu
//Team: JD
//TA: Harper
//Lecturer: Florian Heimerl
//Date: 09/29/2020
/**
 * This class represent a chemical element
 * 
 * @author Huiqian Jing
 *
 */
public class Element {
  public static HashTableMap<String, Element> putNamefindElement =
      new HashTableMap<String, Element>(500);
  public static HashTableMap<Integer, Element> putNumfindElement =
      new HashTableMap<Integer, Element>(500);
  public static HashTableMap<String, Element> putSymbolfindElement =
      new HashTableMap<String, Element>(500);

  private String chemicalSymbol; // The chemical symbol to represent the element e.g. H
  private String chemicalName; // The name of chemical element e.g.Hydrogen
  private Integer atomicNumber;// The number of atomic number stored in the element

  /**
   * Constructor
   * @param symbol 
   * @param fullName
   * @param atomicNum
   * @throws FileNotFoundException
   * @throws IOException
   */
  public Element(String symbol, String fullName, int atomicNum) throws FileNotFoundException, IOException {
    this.chemicalSymbol = symbol;
    this.chemicalName = fullName;
    this.atomicNumber = atomicNum;
  }
  /**
   * Default constructor to import all data into the hashtablemap
   * @throws FileNotFoundException
   * @throws IOException
   */
  public Element() throws FileNotFoundException, IOException {
    this.loadData();

  }
  
  public String getField(String i) {
    if(i.equals("Chemical Name")) return this.getChemicalName();
    if(i.equals("Atomic Number")) return String.valueOf(this.getAtomicNumber());
    if(i.equals("Chemical Symbol")) return this.getChemicalSymbol();
    return null;
  }
  
/**
 * get the atomic number of the element
 * @return atomic number 
 */
  public Integer getAtomicNumber() {
    return atomicNumber;
  }

/**
 * set the atomic number of the element
 * @param atomicNumber atomic number
 */
  public void setAtomicNumber(Integer atomicNumber) {
    this.atomicNumber = atomicNumber;
  }
/**
 * get the chemical symbol of the element
 * @return chemical symbol
 */
  public String getChemicalSymbol() {
    return chemicalSymbol;
  }
/**
 * set the chemical symbol of the element
 * @param chemicalSymbol chemical symbol
 */
  public void setChemicalSymbol(String chemicalSymbol) {
    this.chemicalSymbol = chemicalSymbol;
  }
/**
 * get the chemical name of the element
 * @return chemical name
 */
  public String getChemicalName() {
    return chemicalName;
  }
  /**
   * set the chemical name of the element
   * @param chemicalName chemical name
   */
  public void setChemicalName(String chemicalName) {
    this.chemicalName = chemicalName;
  }
  
/**
 * This method read from a csv file to load all data into the hashtablemap
 * @throws FileNotFoundException
 * @throws IOException
 */
  public static void loadData() throws FileNotFoundException, IOException {
    BufferedReader textFile = null;
    try {
      File file = new File ("elementlist.csv");
      FileReader fr = new FileReader(file);
      textFile = new BufferedReader(fr);
      String line = "";
      while ((line = textFile.readLine()) != null) {
        String[] attributes = line.split(",");
        Element e = new Element(attributes[1].trim(), attributes[2].trim(), Integer.parseInt(attributes[0]));
        putNamefindElement.put(attributes[2].trim(), e);
        putNumfindElement.put(Integer.parseInt(attributes[0]), e);
        putSymbolfindElement.put(attributes[1].trim(), e);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    textFile.close();

  }
/**
 * if the input is a string, find the corresponding element in the 
 * putSymbolfindElement(if input has only 1 or 2 characters) or putNamefindElement hashtablemap
 * @param input
 * @return the element according to input
 */
  public static Element find(String input) {
    Character firstLetter = input.charAt(0);
    String bigFirst = firstLetter.toString().toUpperCase();
    String other = input.substring(1).toLowerCase();
    String totalInput = bigFirst + other;
    Element result;
    if (input.length() <= 2) {
      result = putSymbolfindElement.get(totalInput);
    } else {
      result = putNamefindElement.get(totalInput);
    }
    return result;
  }

/**
 * if the input is a integer, find the corresponding element in the putNumfindElement hashtablemap
 * @param input
 * @return the element according to input
 */
  public static Element find(int input) {
    try {
      Element result = putNumfindElement.get(input);
      return result;
    } catch (Exception e) {
      return null;
    }
  }
/**
 * Check if the input is a string or not
 * @param put the input 
 * @return true if put is a String
 */
  public boolean isStringNumber(String put) {
    try {
      Integer.parseInt(put);
      return true;
    } catch (Exception e) {
      return false;

    }
  }
  
  public String toString() {
    return this.getAtomicNumber() + ", " + this.getChemicalSymbol() + ", " +this.getChemicalName();
  }

}