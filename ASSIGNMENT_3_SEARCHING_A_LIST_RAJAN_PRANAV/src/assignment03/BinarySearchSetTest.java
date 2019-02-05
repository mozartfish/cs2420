package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchSetTest {

  /** Initialize some BinarySearchSet objects */
  BinarySearchSet<Integer> testIntegers;

  BinarySearchSet<String> testComparator;
  BinarySearchSet<Character> testCharacter;

  ArrayList<Integer> smallSortedListOfIntegers;
  ArrayList<String> smallListOfStrings;
  ArrayList<Integer> smallSortedListOfIntegersOutOfOrder;
  ArrayList<Integer> emptyCollection;
  ArrayList<Character> characterCollection;
  Object[] testUnsortedIntegers;
  Object[] testStringObjects;

  @Before
  public void setUp() throws Exception {
    /** Test with no comparator */
    testIntegers = new BinarySearchSet<Integer>();
    testCharacter = new BinarySearchSet<Character>();

    smallSortedListOfIntegers = new ArrayList<Integer>();
    smallSortedListOfIntegers.add(3);
    smallSortedListOfIntegers.add(9);
    smallSortedListOfIntegers.add(17);
    smallSortedListOfIntegers.add(43);
    smallSortedListOfIntegers.add(65);

    smallSortedListOfIntegersOutOfOrder = new ArrayList<Integer>();
    smallSortedListOfIntegersOutOfOrder.add(65);
    smallSortedListOfIntegersOutOfOrder.add(122);
    smallSortedListOfIntegersOutOfOrder.add(363);
    smallSortedListOfIntegersOutOfOrder.add(320);
    smallSortedListOfIntegersOutOfOrder.add(554);
    smallSortedListOfIntegersOutOfOrder.add(1);
    smallSortedListOfIntegersOutOfOrder.add(42);
    smallSortedListOfIntegersOutOfOrder.add(51);

    smallListOfStrings = new ArrayList<String>();
    smallListOfStrings.add("my");
    smallListOfStrings.add("name");
    smallListOfStrings.add("is");
    smallListOfStrings.add("Pranav");

    emptyCollection = new ArrayList<Integer>();
    characterCollection = new ArrayList<Character>();
    characterCollection = new ArrayList<Character>();
    characterCollection.add('a');
    characterCollection.add('b');
    characterCollection.add('c');
    characterCollection.add('d');
    characterCollection.add('e');
    characterCollection.add('f');
    characterCollection.add('g');
    characterCollection.add('h');
    characterCollection.add('i');
    characterCollection.add('j');
    characterCollection.add('k');
    characterCollection.add('l');
    characterCollection.add('m');
    characterCollection.add('n');
    characterCollection.add('o');
    characterCollection.add('p');
    characterCollection.add('q');
    characterCollection.add('r');
    characterCollection.add('s');
    characterCollection.add('t');
    characterCollection.add('u');
    characterCollection.add('v');
    characterCollection.add('w');
    characterCollection.add('x');
    characterCollection.add('y');
    characterCollection.add('z');

    testUnsortedIntegers = new Object[] {1, 42, 51, 65, 122, 320, 363, 554};
    testStringObjects = new Object[] {"is", "my", "name", "Pranav"};
  }

  @Test
  public void testIntegersGetComparator() {
    assertEquals(null, testIntegers.getComparator());
  }

  @Test(expected = NoSuchElementException.class)
  public void testIntegersFirst() {
    assertEquals(new NoSuchElementException(), testIntegers.first());
  }

  @Test(expected = NoSuchElementException.class)
  public void testIntegersLast() {
    assertEquals(new NoSuchElementException(), testIntegers.last());
  }

  @Test
  public void testIntegersRemoveOnEmptyList() {
    assertEquals(false, testIntegers.remove(3));
  }

  @Test
  public void testIntegersSize() {
    assertEquals(0, testIntegers.size());
  }

  @Test
  public void testIntegersAddOneElement() {
    assertEquals(true, testIntegers.add(3));
  }

  @Test
  public void testIntegersAddOneElementContains() {
    assertEquals(true, testIntegers.add(3));
    assertEquals(true, testIntegers.contains(3));
  }

  @Test
  public void testIntegerAddOneElementSize() {
    assertEquals(true, testIntegers.add(3));
    assertEquals(1, testIntegers.size());
  }

  @Test
  public void testIntegersRemoveOneElement() {
    assertEquals(true, testIntegers.add(3));
    assertEquals(true, testIntegers.remove(3));
  }

  @Test
  public void testIntegerRemoveOneElementContains() {
    assertEquals(true, testIntegers.add(3));
    assertEquals(true, testIntegers.remove(3));
    assertEquals(false, testIntegers.contains(3));
  }

  @Test
  public void testIntegersRemoveOneElementSize() {
    assertEquals(true, testIntegers.add(3));
    assertEquals(true, testIntegers.remove(3));
    assertEquals(0, testIntegers.size());
  }

  @Test
  public void testIntegersClearOneElement() {
    assertEquals(true, testIntegers.add(3));
    testIntegers.clear();
    assertEquals(0, testIntegers.size());
  }

  @Test
  public void testIntegersAddAll() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
  }

  @Test
  public void testIntegersContainsAll() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(true, testIntegers.containsAll(smallSortedListOfIntegers));
  }

  @Test
  public void testIntegersAddAllSize() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(5, testIntegers.size());
  }

  @Test
  public void testIntegersRemoveAll() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(true, testIntegers.removeAll(smallSortedListOfIntegers));
  }

  @Test
  public void testIntegersRemoveAllSize() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(true, testIntegers.removeAll(smallSortedListOfIntegers));
    assertEquals(0, testIntegers.size());
  }

  @Test
  public void testIntegersCollectionClear() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(5, testIntegers.size());
    testIntegers.clear();
    assertEquals(0, testIntegers.size());
  }

  @Test
  public void testIntegersCollectionFirst() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(5, testIntegers.size());
    assertEquals(3, testIntegers.first());
  }

  @Test
  public void testIntegersCollectionLast() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegers));
    assertEquals(5, testIntegers.size());
    assertEquals(65, testIntegers.last());
  }

  @Test
  public void testIntegersCollectionUnsorted() {
    assertEquals(true, testIntegers.addAll(smallSortedListOfIntegersOutOfOrder));
    //    1, 42, 51, 65, 122, 320, 363, 554
    assertEquals(true, testIntegers.containsAll(smallSortedListOfIntegersOutOfOrder));
    assertArrayEquals(testUnsortedIntegers, testIntegers.toArray());
    assertEquals(true, testIntegers.removeAll(smallSortedListOfIntegersOutOfOrder));
    assertEquals(0, testIntegers.size());
    assertEquals(false, testIntegers.remove(1));
    assertEquals(false, testIntegers.contains(1));
  }

  @Test
  public void testStringComparator() {
    testComparator = new BinarySearchSet<String>(new comp());
    assertEquals(true, testComparator.addAll(smallListOfStrings));
    assertArrayEquals(testStringObjects, testComparator.toArray());
  }

  @Test
  public void testEmptyCollection() {
    assertEquals(false, testIntegers.addAll(emptyCollection));
    assertEquals(true, testIntegers.containsAll(emptyCollection));
  }

  @Test
  public void testAddCharacter() {
    assertEquals(true, testCharacter.add('a'));
    assertEquals(true, testCharacter.add('b'));
    assertEquals(true, testCharacter.add('c'));
    assertEquals(true, testCharacter.add('d'));
    assertEquals(true, testCharacter.add('e'));
    assertEquals(true, testCharacter.add('f'));
    assertEquals(true, testCharacter.add('g'));
    assertEquals(true, testCharacter.add('h'));
    assertEquals(true, testCharacter.add('i'));
    assertEquals(true, testCharacter.add('j'));
    assertEquals(true, testCharacter.add('k'));
    assertEquals(true, testCharacter.add('l'));
    assertEquals(true, testCharacter.add('m'));
    assertEquals(true, testCharacter.add('n'));
    assertEquals(true, testCharacter.add('o'));
    assertEquals(true, testCharacter.add('q'));
    assertEquals(true, testCharacter.add('r'));
    assertEquals(true, testCharacter.add('s'));
    assertEquals(true, testCharacter.add('t'));
    assertEquals(true, testCharacter.add('u'));
    assertEquals(true, testCharacter.add('v'));
    assertEquals(true, testCharacter.add('w'));
    assertEquals(true, testCharacter.add('x'));
    assertEquals(true, testCharacter.add('y'));
    assertEquals(true, testCharacter.add('z'));
    assertEquals(25, testCharacter.size());
    assertEquals(false, testCharacter.add('a'));
  }

  @Test
  public void testCharacterCollection() {
    assertEquals(true, testCharacter.addAll(characterCollection));
  }

  @Test
  public void addOneHundredNumbers() {
    for (int i = 0; i < 100; i++) {
      testIntegers.add(i);
    }
    for (int i = 0; i < 100; i++) {
      testIntegers.remove(i);
    }
    testIntegers.add(1);
    assertEquals(1, testIntegers.size());
  }
  
  @Test
  public void inputMismatch() {
    testIntegers.add("Hello");
  }

  /** Comparator that defines an ordering among library books using the due date. */
  protected class comp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
      if (o1.length() - o2.length() == 0) {
        return o1.compareTo(o2);
      } else {
        return o1.length() - o2.length();
      }
    }
  }
}
