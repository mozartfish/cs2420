package assignment10;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

public class PriorityQueueTest {

  @Test
  public void testSizeIntQueue() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    for (int i = 0; i < 500_000; i++) {
      intQueue.add(i);
    }
    assertEquals(500_000, intQueue.size());
  }

  @Test
  public void testSizeStringQueue() {
    PriorityQueue<String> stringQueue = new PriorityQueue<String>();
    stringQueue.add("A");
    stringQueue.add("B");
    stringQueue.add("C");
    stringQueue.add("D");
    stringQueue.add("E");
    stringQueue.add("F");
    stringQueue.add("G");
    stringQueue.add("H");
    stringQueue.add("I");
    stringQueue.add("J");
    stringQueue.add("K");
    stringQueue.add("L");
    stringQueue.add("M");
    stringQueue.add("N");
    stringQueue.add("O");
    stringQueue.add("P");
    stringQueue.add("Q");
    stringQueue.add("R");
    stringQueue.add("S");
    stringQueue.add("T");
    stringQueue.add("U");
    stringQueue.add("V");
    stringQueue.add("W");
    stringQueue.add("X");
    stringQueue.add("Y");
    stringQueue.add("Z");

    assertEquals(26, stringQueue.size());
  }

  @Test
  public void testSizeCharacterQueue() {
    PriorityQueue<Character> charQueue = new PriorityQueue<Character>();

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < 26; i++) {
      charQueue.add(alphabet.charAt(i));
    }
    assertEquals(26, charQueue.size());
  }

  @Test
  public void testClear() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    for (int i = 0; i < 500_000; i++) {
      intQueue.add(i);
    }
    
    intQueue.clear();
    assertEquals(0, intQueue.size());
  }

  @Test
  public void testFindMin() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    for (int i = 0; i < 500_000; i++) {
      intQueue.add(i);
    }
   assertEquals(true, intQueue.findMin().equals(0));
  }

  @Test
  public void testDeleteClassExample() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    intQueue.add(3);
    intQueue.add(22);
    intQueue.add(16);
    intQueue.add(31);
    intQueue.add(43);
    intQueue.add(17);
    intQueue.add(92);
    intQueue.add(100);
    intQueue.add(56);
    intQueue.add(45);
    intQueue.add(14);

    System.out.println(intQueue.generateDot());

    //remove the minItem in the tree
    intQueue.deleteMin();
    System.out.println(intQueue.generateDot());
  }

  @Test
  public void testAdd() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    intQueue.add(3);
    intQueue.add(22);
    intQueue.add(6);
    intQueue.add(31);
    intQueue.add(43);
    intQueue.add(17);
    intQueue.add(92);
    intQueue.add(100);
    intQueue.add(56);
    intQueue.add(45);

    //add the eleventh element into the queue
    intQueue.add(14);
    //System.out.println(intQueue.generateDot());
  }
  
  @Test
  public void testAddToArray() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    intQueue.add(3);
    intQueue.add(22);
    intQueue.add(6);
    intQueue.add(31);
    intQueue.add(43);
    intQueue.add(17);
    intQueue.add(92);
    intQueue.add(100);
    intQueue.add(56);
    intQueue.add(45);

    //add the eleventh element into the queue
    intQueue.add(14);
    
    System.out.println(intQueue.toArray()); 
    //System.out.println(intQueue.generateDot());
  }
  @Test
  public void testAddWithComparator() {
    
    //create a new comparator object that ignores capitalization and compares strings lexicographically
    Comparator<String> comp = (rhs, lhs) -> rhs.length() - lhs.length();

    PriorityQueue<String> stringQueue = new PriorityQueue<String>(comp);
    stringQueue.add("hello");
    stringQueue.add("Mumbai");
    stringQueue.add("Rajasthan");
    stringQueue.add("Berlin");
    stringQueue.add("Budapest");
    stringQueue.add("London");
    stringQueue.add("Prague");
    stringQueue.add("Bombay");
    stringQueue.add("Buenos Aires");

    System.out.println(stringQueue.generateDot());
  }
  @Test(expected = NoSuchElementException.class)
  public void testFindMinNoElements() {
    
    //create a new comparator object that ignores capitalization and compares strings lexicographically
    Comparator<String> comp = (rhs, lhs) -> rhs.length() - lhs.length();

    PriorityQueue<String> stringQueue = new PriorityQueue<String>(comp);
    stringQueue.findMin();
   
  }
  
  @Test(expected = NoSuchElementException.class)
  public void testDeleteMin() {
    
    //create a new comparator object that ignores capitalization and compares strings lexicographically
    Comparator<String> comp = (rhs, lhs) -> rhs.length() - lhs.length();

    PriorityQueue<String> stringQueue = new PriorityQueue<String>(comp);
    stringQueue.deleteMin();
  }

  @Test(expected = NullPointerException.class)
  public void testDeleteMinMultipleTimes() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    intQueue.add(1);
    intQueue.add(2);
    intQueue.add(3);
    intQueue.add(null);

    System.out.println(intQueue.generateDot());

    intQueue.deleteMin();

    System.out.println(intQueue.generateDot());
  }
  @Test
  public void testAddAndDeleteRoot() {
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    intQueue.add(1);
    System.out.println(intQueue.generateDot());

    intQueue.deleteMin();

    System.out.println(intQueue.generateDot());
  }
  @Test
  public void testAddRandom() {
    
    Random rnd = new Random(5);
    PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
    
    for (int i = 0; i < 10; i++) {
      intQueue.add(Math.abs(rnd.nextInt() % 5));
    }
    System.out.println(intQueue.generateDot());

    intQueue.deleteMin();

    System.out.println(intQueue.generateDot());
  }
  
  
}
