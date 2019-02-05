package assignment07;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeJUnit {

  BinarySearchTree<Integer> emptyTree;
  BinarySearchTree<Integer> singleElementTree;
  BinarySearchTree<Integer> integerTree;
  ArrayList<Integer> collectionOfItems;

  @Before
  public void setUp() throws Exception {
    emptyTree = new BinarySearchTree<Integer>();
    singleElementTree = new BinarySearchTree<Integer>();
    singleElementTree.add(7);
    integerTree = new BinarySearchTree<Integer>();
    integerTree.add(10);
    integerTree.add(5);
    integerTree.add(15);
    integerTree.add(20);
    integerTree.add(25);
    integerTree.add(19);
    integerTree.add(18);
    integerTree.add(1);
    integerTree.add(14);
    collectionOfItems = new ArrayList<Integer>();
    collectionOfItems.add(1);
    collectionOfItems.add(3);
    collectionOfItems.add(10);
    collectionOfItems.add(14);
    collectionOfItems.add(15);
    collectionOfItems.add(21);
    collectionOfItems.add(25);
    collectionOfItems.add(20);
    collectionOfItems.add(-6);
  }

  /* Tests for First */
  @Test
  public void testFirstOnIntegerTree() {
    int result = integerTree.first();
    assertEquals(1, result);
  }

  @Test(expected = NoSuchElementException.class)
  public void testFirstOnEmptyTree() {
    emptyTree.first();
  }

  @Test
  public void testFirstOnSingleElementTree() {
    int result = singleElementTree.first();
    assertEquals(7, result);
  }

  /* Tests for Last */
  @Test
  public void testLastOnIntegerTree() {
    int result = integerTree.last();
    assertEquals(25, result);
  }

  @Test(expected = NoSuchElementException.class)
  public void testLastOnEmptyTree() {
    emptyTree.last();
  }

  @Test
  public void testLastOnSingleElementTree() {
    int result = singleElementTree.last();
    assertEquals(7, result);
  }

  /* Tests for add */
  @Test
  public void testsIfAddWillIncrementSizeProperlyForEmptyTree() {
    assertEquals(0, emptyTree.size());
    assertTrue(emptyTree.add(1));
    assertEquals(1, emptyTree.size());
  }

  @Test
  public void testsIfAddWillIncrementSizeProperlyForIntegerTree() {
    assertEquals(9, integerTree.size());
    assertTrue(integerTree.add(2));
    assertEquals(10, integerTree.size());
  }

  @Test
  public void testsAddAllWithEmptyList() {
    ArrayList<Integer> emptyList = new ArrayList<Integer>();
    assertFalse(integerTree.addAll(emptyList));
  }

  @Test
  public void testAddWhenSettingTheRoot() {
    int[] expectedArray = new int[] {5};
    assertTrue(emptyTree.add(5));
    Object[] resultingArray = emptyTree.toArray();
    assertEquals(expectedArray[0], resultingArray[0]);
  }

  @Test
  public void testAdding26ToIntegerTree() {
    int[] expectedArray = new int[] {1, 5, 10, 14, 15, 18, 19, 20, 25, 26};
    assertTrue(integerTree.add(26));
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 10; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }

  @Test
  public void testAdding13ToIntegerTree() {
    int[] expectedArray = new int[] {1, 5, 10, 13, 14, 15, 18, 19, 20, 25};
    assertTrue(integerTree.add(13));
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 10; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }

  @Test
  public void testAddingAnIntegerToATreeThatAlreadyExists() {
    assertFalse(integerTree.add(18));
  }

  /* Tests for Contains */
  @Test
  public void testContainsOnEmptyTree() {
    assertFalse(emptyTree.contains(0));
  }

  @Test
  public void testContainsOnIntegerTreeWhenElementExists() {
    assertTrue(integerTree.contains(5));
  }

  @Test
  public void testContainsOneIntegerTreeWhenElementDoesNotExist() {
    assertFalse(integerTree.contains(9));
  }

  /* Tests for isEmpty */
  @Test
  public void testIsEmptyOnEmptyTree() {
    assertTrue(emptyTree.isEmpty());
  }

  @Test
  public void testIsEmptyOnSingleElementTree() {
    assertFalse(singleElementTree.isEmpty());
  }

  @Test
  public void testIsEmptyOnIntegerTree() {
    assertFalse(integerTree.isEmpty());
  }

  /* Tests for remove */
  @Test
  public void testRemoveOnEmptyTree() {
    assertFalse(emptyTree.remove(1));
  }

  @Test
  public void testRemoveOnRootWithNoChildren() {
    singleElementTree.remove(7);
    assertEquals(0, singleElementTree.size());
  }

  @Test
  public void testRemoveOnSingleElementTree() {
    assertEquals(1, singleElementTree.size());
    assertTrue(singleElementTree.remove(7));
    assertEquals(0, singleElementTree.size());
  }

  @Test
  public void testRemoveOnALeafNode() {
    int[] expectedArray = new int[] {1, 5, 10, 14, 15, 19, 20, 25};
    assertEquals(9, integerTree.size());
    integerTree.remove(18);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 8; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
    assertEquals(8, integerTree.size());
  }

  @Test
  public void testRemoveOnASingleChildNode() {
    int[] expectedArray = new int[] {1, 10, 14, 15, 18, 19, 20, 25};
    assertEquals(9, integerTree.size());
    integerTree.remove(5);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 8; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
    assertEquals(8, integerTree.size());
  }

  @Test
  public void testRemoveOnADoubleChildNode() {
    int[] expectedArray = new int[] {1, 5, 10, 14, 18, 19, 20, 25};
    assertEquals(9, integerTree.size());
    integerTree.remove(15);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 8; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
    assertEquals(8, integerTree.size());
  }

  @Test
  public void testRemoveOnTwoChildRoot() {
    int[] expectedArray = new int[] {1, 5, 14, 15, 18, 19, 20, 25};
    assertEquals(9, integerTree.size());
    integerTree.remove(10);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 8; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
    assertEquals(8, integerTree.size());
  }

  @Test
  public void testRemoveOnSingleChildRoot() {
    int[] expectedArray = new int[] {8, 9, 10, 11};
    singleElementTree.add(8);
    singleElementTree.add(9);
    singleElementTree.add(10);
    singleElementTree.add(11);
    singleElementTree.remove(7);
    Object[] resultingArray = singleElementTree.toArray();
    for (int i = 0; i < 4; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }

  @Test
  public void testRemovingTheLeftNodeToTheRootOnARightHeavyTree() {
    int[] expectedArray = new int[] {10, 14, 15, 18, 19, 20, 25};
    integerTree.remove(1);
    integerTree.remove(5);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 7; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }

  @Test
  public void testRemoveOnAnElementThatDoesntExist() {
    assertFalse(integerTree.remove(65));
  }

  /* Tests for removeAll */
  @Test
  public void testRemoveAllElementsAndCheckSize() {
    Object[] elementsToDeleteArray = integerTree.toArray();
    ArrayList<Integer> collectionOfItemsToDelete = new ArrayList<Integer>();
    for (int i = 0; i < 9; i++) {
      collectionOfItemsToDelete.add((Integer) elementsToDeleteArray[i]);
    }
    assertEquals(9, integerTree.size());
    integerTree.removeAll(collectionOfItemsToDelete);
    assertEquals(0, integerTree.size());
  }

  @Test
  public void testRemoveAllWithEmptyList() {
    ArrayList<Integer> emptyList = new ArrayList<Integer>();
    assertFalse(integerTree.removeAll(emptyList));
  }

  @Test
  public void testRemoveAllElementsAndCheckReturnStatement() {
    Object[] elementsToDeleteArray = integerTree.toArray();
    ArrayList<Integer> collectionOfItemsToDelete = new ArrayList<Integer>();
    for (int i = 0; i < 9; i++) {
      collectionOfItemsToDelete.add((Integer) elementsToDeleteArray[i]);
    }
    assertTrue(integerTree.removeAll(collectionOfItemsToDelete));
  }

  @Test
  public void testThatRemoveAllReturnsFalseWhenAllElementInCollectionAreNotFoundInTheBTS() {
    ArrayList<Integer> collectionOfItemsToDelete = new ArrayList<Integer>();
    collectionOfItemsToDelete.add(-3);
    collectionOfItemsToDelete.add(-5);
    collectionOfItemsToDelete.add(-9);
    assertFalse(integerTree.removeAll(collectionOfItemsToDelete));
  }

  @Test
  public void testRemoveAllWhenOnlySomeElementsCanBeDeletedAndCheckReturnStatement() {
    assertTrue(integerTree.removeAll(collectionOfItems));
  }

  @Test
  public void testRemoveAllWhenOnlySomeElementsCanBeDeletedAndCheckSize() {
    assertEquals(9, integerTree.size());
    integerTree.removeAll(collectionOfItems);
    assertEquals(3, integerTree.size());
  }

  @Test
  public void testRemoveAllWhenOnlySomeElementsCanBeDeletedAndCheckResultingTree() {
    int[] expectedArray = new int[] {5, 18, 19};
    integerTree.removeAll(collectionOfItems);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 3; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }
  /* Tests for size */
  @Test
  public void testSizeOnEmptyTree() {
    assertEquals(0, emptyTree.size());
  }

  @Test
  public void testSizeOnSingleElementTree() {
    assertEquals(1, singleElementTree.size());
  }

  @Test
  public void testSizeOnNineElementTree() {
    assertEquals(9, integerTree.size());
  }

  /* Tests for toArray */
  @Test
  public void testToArrayOnIntegerTree() {
    int[] expectedArray = new int[] {1, 5, 10, 14, 15, 18, 19, 20, 25};
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 9; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }

  @Test
  public void testToArrayOnSingleElementTree() {
    int[] expectedArray = new int[] {7};
    assertEquals(7, expectedArray[0]);
  }

  /* Tests for addAll */
  @Test
  public void testAddAllElementsAndCheckSize() {
    ArrayList<Integer> elementsToAdd = new ArrayList<Integer>();
    elementsToAdd.add(3);
    elementsToAdd.add(81);
    elementsToAdd.add(95);
    elementsToAdd.add(6);
    assertEquals(9, integerTree.size());
    integerTree.addAll(elementsToAdd);
    assertEquals(13, integerTree.size());
  }

  @Test
  public void testAddAllElementsAndCheckReturnStatement() {
    ArrayList<Integer> elementsToAdd = new ArrayList<Integer>();
    elementsToAdd.add(3);
    elementsToAdd.add(81);
    elementsToAdd.add(95);
    elementsToAdd.add(6);
    assertTrue(integerTree.addAll(elementsToAdd));
  }

  @Test
  public void testAddAllElementsAndCheckIfTheyWereAddedProperly() {
    ArrayList<Integer> elementsToAdd = new ArrayList<Integer>();
    int[] expectedArray = new int[] {1, 3, 5, 6, 10, 14, 15, 18, 19, 20, 25, 81, 95};
    elementsToAdd.add(3);
    elementsToAdd.add(81);
    elementsToAdd.add(95);
    elementsToAdd.add(6);
    integerTree.addAll(elementsToAdd);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 13; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }

  @Test
  public void testThatAddAllReturnsFalseWhenAllElementInCollectionAreAlreadyInTheBTS() {
    Object[] elementsToAddArray = integerTree.toArray();
    ArrayList<Integer> collectionOfItemsToAdd = new ArrayList<Integer>();
    for (int i = 0; i < 9; i++) {
      collectionOfItemsToAdd.add((Integer) elementsToAddArray[i]);
    }
    assertFalse(integerTree.addAll(collectionOfItemsToAdd));
  }

  @Test
  public void testAddAllWhenOnlySomeElementsCanBeAddedAndCheckReturnStatement() {
    assertTrue(integerTree.addAll(collectionOfItems));
  }

  @Test
  public void testAddAllWhenOnlySomeElementsCanBeAddedAndCheckSize() {
    assertEquals(9, integerTree.size());
    integerTree.addAll(collectionOfItems);
    assertEquals(12, integerTree.size());
  }

  @Test
  public void testAddAllWhenOnlySomeElementsCanBeDeletedAndCheckResultingTree() {
    int[] expectedArray = new int[] {-6, 1, 3, 5, 10, 14, 15, 18, 19, 20, 21, 25};
    integerTree.addAll(collectionOfItems);
    Object[] resultingArray = integerTree.toArray();
    for (int i = 0; i < 12; i++) {
      assertEquals(expectedArray[i], resultingArray[i]);
    }
  }
  /* Tests for clear */
  @Test
  public void testClearOnASingleElementTree() {
    assertEquals(1, singleElementTree.size());
    singleElementTree.clear();
    assertEquals(0, singleElementTree.size());
  }

  @Test
  public void testClearOnIntegerTree() {
    assertEquals(9, integerTree.size());
    integerTree.clear();
    assertEquals(0, integerTree.size());
  }

  /* Tests for containsAll */
  @Test
  public void testContainsAllWhenNoElementsAreContainedInTheTree() {
    ArrayList<Integer> elementsThatAreContained = new ArrayList<Integer>();
    elementsThatAreContained.add(0);
    elementsThatAreContained.add(3);
    elementsThatAreContained.add(71);
    elementsThatAreContained.add(72);
    assertFalse(integerTree.containsAll(elementsThatAreContained));
  }

  @Test
  public void testContainsAllWhenSomeElementsAreContainedInTheTree() {
    ArrayList<Integer> elementsThatAreContained = new ArrayList<Integer>();
    elementsThatAreContained.add(0);
    elementsThatAreContained.add(3);
    elementsThatAreContained.add(25);
    elementsThatAreContained.add(19);
    assertFalse(integerTree.containsAll(elementsThatAreContained));
  }

  @Test
  public void testContainsAllWhenAllElementsAreContainedInTheTree() {
    ArrayList<Integer> elementsThatAreContained = new ArrayList<Integer>();
    elementsThatAreContained.add(1);
    elementsThatAreContained.add(25);
    elementsThatAreContained.add(15);
    elementsThatAreContained.add(18);
    assertTrue(integerTree.containsAll(elementsThatAreContained));
  }

  @Test
  public void testContainsAllWhenPassedanEmptyCollection() {
    ArrayList<Integer> emptyList = new ArrayList<Integer>();
    assertTrue(integerTree.containsAll(emptyList));
  }
  
  //Test based on Dr. Parker and Dr. Meyer's example from lecture
  //Insert an element from the lecture example
  @Test
  public void testClassExample() throws Exception {
    BinarySearchTree<Character> classExample = new BinarySearchTree<Character>();
    classExample.add('i');
    classExample.add('d');
    classExample.add('j');
    classExample.add('b');
    classExample.add('f');
    classExample.add('a');
    classExample.add('c');
    classExample.add('e');
    classExample.add('h');
//    System.out.println(classExample.generateDot());
    assertEquals(9, classExample.size());
    Object[] resultArray = classExample.toArray();
    Object[] expectedArray = new Object[] {'a', 'b', 'c', 'd', 'e', 'f', 'h', 'i'};
    for (int i = 0; i < expectedArray.length; i++) {
      assertEquals(resultArray[i], expectedArray[i]);
    }
    classExample.add('g');
    assertEquals(10, classExample.size());
//    System.out.println(classExample.generateDot());
  }
  
  @Test
  public void testClassExampleRemoveLeafNode() throws Exception {
    BinarySearchTree<Integer> classExample = new BinarySearchTree<Integer>();
    classExample.add(20);
    classExample.add(27);
    classExample.add(9);
    classExample.add(5);
    classExample.add(16);
    classExample.add(2);
    classExample.add(6);
    classExample.add(11);
    classExample.add(19);
    classExample.add(10);
    classExample.add(17);
    
//    The Original BST before removing the leaf node containing 6
//    System.out.println(classExample.generateDot());
    classExample.remove(6);
//    System.out.println(classExample.generateDot());
  }
}
