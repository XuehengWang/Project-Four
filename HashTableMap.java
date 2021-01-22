// --== CS400 File Header Information ==--
// Name: Xueheng Wang
// Email: xwang2395@wisc.edu
// Team: JD
// TA: Harper
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;;

/**
 * This class will create a hash table that can store the key-value pair in the position
 * corresponding to the hash code of the key. The users can remove the key-value pair or get a value
 * of the key from the hash table.
 * 
 * @author Xueheng Wang
 *
 * @param <KeyType>   a generic key that stored in the key-value pair
 * @param <ValueType> a generic value that stored in the key-value pair
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private LinkedList<KeyValuePair<KeyType, ValueType>>[] hashTable; // the hash table that store all the
                                                               // key-value pairs
  private int capacity; // capacity of the hash table
  private int size; // current size of the hash table

  /**
   * Construct a HashTable with the given capacity
   * 
   * @param capacity of the hash table
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.hashTable = new LinkedList[capacity];
    this.size = 0;

  }

  /**
   * Construct a HashTable with the capacity of 10
   */
  public HashTableMap() {
    this.capacity = 10;
    this.hashTable = new LinkedList[10];
    this.size = 0;
  }

  /**
   * @return capacity of the hash table
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * When the elements stored in the hash table reaches 80% of the capacity, double the capacity of
   * the hash table
   */
  private void doubleCapacity() {
    capacity = 2 * capacity;
    LinkedList<KeyValuePair<KeyType, ValueType>>[] temp = new LinkedList[capacity];
    for (int i = 0; i < hashTable.length; i++) {
      if (hashTable[i] == null) {
        continue;
      } else {
        temp[i] = hashTable[i];
      }
    }
    hashTable = temp;
  }

  /**
   * Count the index of the array that the key-value pair should be stored according to the hash
   * code of the key
   * 
   * @param key of the key-value pair
   * @return an index of the hash table array
   */
  private int getPosition(KeyType key) {
    return (Math.abs(key.hashCode())) % capacity;
  }

  /**
   * Check if the hash table's capacity need to be doubled
   */
  private void checkDouble() {
    if (size >= 0.8 * capacity) {
      doubleCapacity();
    }
  }

  @Override
  /**
   * put a key-value pair in to the hash table. If the position has no element, create a new
   * LinkedList and store the element at the head of the LinkedList; if there is an LinkedList in
   * the position, then add the element to the LinkedList; if the key has already exist, then do not
   * add the element.
   * 
   * @param key   of the key-value pair
   * @param value of the key-value pair
   * @return if the element has been added, and false if the element has not been added
   */
  public boolean put(KeyType key, ValueType value) {
    KeyValuePair<KeyType, ValueType> elementPut = new KeyValuePair<KeyType, ValueType>(key, value);
    int pos = getPosition(key);
    if (hashTable[pos] == null) {
      LinkedList<KeyValuePair<KeyType, ValueType>> chain = new LinkedList<KeyValuePair<KeyType, ValueType>>();
      chain.add(elementPut);
      hashTable[pos] = chain;
      size++;
      checkDouble();
      return true;
    } else if (containsKey(key)) {
      return false;
    } else {
      KeyValuePair<KeyType, ValueType> currNode = hashTable[pos].getFirst();
      while (currNode.hasNext()) {
        currNode = currNode.getNext();
      }
      currNode.setNext(elementPut);
      size++;
      checkDouble();
      return true;
    }
  }



  @Override
  /**
   * Get the value of the key-value pair given the key value to search, if the element does not
   * exist, throw NoSuchElementException.
   * 
   * @param key of the key-value pair
   * @return the value of the key-value pair to search
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key)) {
      throw new NoSuchElementException("The key-value pair does not exist.");
    } else {
      int pos = getPosition(key);
      KeyValuePair<KeyType, ValueType> currNode = hashTable[pos].getFirst();
      while (currNode != null) {
        if (key.equals(currNode.getKey())) {
          return currNode.getValue();
        }
        currNode = currNode.getNext();
      }

      throw new NoSuchElementException("The key-value pair does not exist.");
    }
  }

  @Override
  /**
   * Return the size of the hash table.
   */
  public int size() {
    return size;
  }

  @Override
  /**
   * Determine whether the key has already existed in the hash table.
   * 
   * @param key of the key-value pair
   * @return true if the key has already existed, false if the key has not
   */
  public boolean containsKey(KeyType key) {
    int pos = getPosition(key);
    if (hashTable[pos] == null) {
      return false;
    } else if (hashTable[pos].getFirst() == null) {
      return false;
    } else {
      KeyValuePair<KeyType, ValueType> currNode = hashTable[pos].getFirst();
      while (currNode != null) {
        if (key.equals(currNode.getKey())) {
          return true;
        }
        currNode = currNode.getNext();
      }
      return false;
    }
  }

  @Override
  /**
   * Remove a key-value pair given a key
   * 
   * @return the value of the moved element, return null if the key does not exist
   */
  public ValueType remove(KeyType key) {
    if (!containsKey(key)) {
      return null;
    } else {
      int pos = getPosition(key);
      KeyValuePair<KeyType, ValueType> currNode = hashTable[pos].getFirst();
      if (!(currNode.hasNext())) {
        hashTable[pos] = null;
        size--;
      } else {
        while (currNode != null) {
          if (key.equals(currNode.getKey())) {
            hashTable[pos].remove(currNode);
            size--;
            break;
          }
        }
      }
      return currNode.getValue();
    }
  }

  @Override
  /**
   * remove all the elements in the hash table
   */
  public void clear() {
    hashTable = new LinkedList[capacity];
  }
  
  /**
   * This class construct an element with key and value in generic type, and this element will be
   * stored in the LinkedList in the hash table.
   * 
   * @author Xueheng Wang
   *
   * @param <KeyType>   a generic key that user want to store in the key-value pair
   * @param <ValueType> a generic value that user want to store in the key-value pair
   */
  class KeyValuePair<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;
    private KeyValuePair<KeyType, ValueType> next = null; // the next Element in the LinkedList

    /**
     * construct an key-value pair with key and value
     * 
     * @param key
     * @param value
     */
    public KeyValuePair(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }

    /**
     * get the key of the key-value pair
     * 
     * @return key
     */
    public KeyType getKey() {
      return key;
    }

    /**
     * get the value of the key-value pair
     * 
     * @return value
     */
    public ValueType getValue() {
      return value;
    }

    /**
     * set the next key-value pair after this element
     * 
     * @param nextElement
     */
    public void setNext(KeyValuePair<KeyType, ValueType> nextElement) {
      this.next = nextElement;
    }

    /**
     * get the next key-value pair after this element
     * 
     * @param nextElement
     */
    public KeyValuePair<KeyType, ValueType> getNext() {
      return next;
    }

    /**
     * return true if this element has next element, otherwise return false.
     * 
     * @param nextElement
     */
    public boolean hasNext() {
      if (next == null) {
        return false;
      }
      return true;
    }


  }

}
