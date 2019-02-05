package assignment06;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedListTest {

  @org.junit.Test
  public void addFirstInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addFirst(1);
    int result = list.getFirst();
    assertEquals(1, result);
    assertEquals(1, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void addFirstString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("Hello");
    String result = list.getFirst();
    assertEquals("Hello", result);
    assertEquals(1, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void addFirstCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.addFirst('A');
    char result = list.getFirst();
    assertEquals('A', result);
    assertEquals(1, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void addToSpecificIndexInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addFirst(1);
    list.add(0, 0);
    list.add(2, 2);
    list.add(3, 3);
    int result = list.getFirst();
    int secondElement = list.get(1);
    int thirdElement = list.get(2);
    int fourthElement = list.get(3);
    assertEquals(0, result);
    assertEquals(secondElement, 1);
    assertEquals(thirdElement, 2);
    assertEquals(fourthElement, 3);
    assertEquals(4, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void addToSpecificIndexIntegerException() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addFirst(1);
    list.add(0, 0);
    list.add(2, 2);
    list.add(3, 3);
    list.add(5, 5);
  }

  @org.junit.Test
  public void addToSpecificIndexString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("Be");
    list.add(0, "To");
    list.add(2, "OR");
    list.add(3, "NOT");
    list.add(4, "TO");
    list.add(5, "BE");
    String result = list.getFirst();
    String secondElement = list.get(1);
    String thirdElement = list.get(2);
    String fourthElement = list.get(3);
    String fifthElement = list.get(4);
    String sixthElement = list.get(5);
    assertEquals("To", result);
    assertEquals("Be", secondElement);
    assertEquals("OR", thirdElement);
    assertEquals("NOT", fourthElement);
    assertEquals("TO", fifthElement);
    assertEquals("BE", sixthElement);
    assertEquals(6, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void addToSpecificIndexStringException() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("Be");
    list.add(0, "To");
    list.add(2, "OR");
    list.add(3, "NOT");
    list.add(4, "TO");
    list.add(5, "BE");
    list.add(7, "THAT");
  }

  @org.junit.Test
  public void addToSpecificIndexCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.addFirst('B');
    list.add(0, 'A');
    list.add(2, 'C');
    list.add(3, 'D');
    list.add(4, 'E');
    list.add(5, 'F');
    char result = list.getFirst();
    char secondElement = list.get(1);
    char thirdElement = list.get(2);
    char fourthElement = list.get(3);
    char fifthElement = list.get(4);
    char sixthElement = list.get(5);
    assertEquals('A', result);
    assertEquals('B', secondElement);
    assertEquals('C', thirdElement);
    assertEquals('D', fourthElement);
    assertEquals('E', fifthElement);
    assertEquals('F', sixthElement);
    assertEquals(6, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void addToSpecificIndexCharacterException() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.addFirst('B');
    list.add(0, 'A');
    list.add(2, 'C');
    list.add(3, 'D');
    list.add(4, 'E');
    list.add(6, 'F');
  }

  @org.junit.Test
  public void getFirstInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addFirst(2);
    list.add(0, 1);
    int firstElement = list.getFirst();
    assertEquals(2, list.size());
    assertEquals(1, firstElement);
  }

  @org.junit.Test(expected = NoSuchElementException.class)
  public void getFirstIntegerException() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.getFirst();
  }

  @org.junit.Test
  public void getFirstString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("B");
    list.add(0, "A");
    String firstElement = list.getFirst();
    assertEquals(2, list.size());
    assertEquals("A", firstElement);
  }

  @org.junit.Test(expected = NoSuchElementException.class)
  public void getFirstStringException() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.getFirst();
  }

  @org.junit.Test
  public void getFirstCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.addFirst('B');
    list.add(0, 'A');
    char firstElement = list.getFirst();
    assertEquals(2, list.size());
    assertEquals('A', firstElement);
  }

  @org.junit.Test(expected = NoSuchElementException.class)
  public void getFirstCharacterException() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.getFirst();
  }

  @org.junit.Test
  public void getElementAtRandomIndexInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    int result = list.get(2);
    assertEquals(2, result);
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void getElementAtRandomIndexIntegerException() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    list.get(100);
  }

  @org.junit.Test
  public void getElementAtRandomIndexString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    String result = list.get(1);
    assertEquals("B", result);
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void getElementAtRandomIndexStringException() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    list.get(3);
  }

  @org.junit.Test
  public void getElementAtRandomIndexCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    char result = list.get(2);
    assertEquals('C', result);
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void getElementAtRandomIndexCharacterException() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    list.get(26);
  }

  @org.junit.Test
  public void removeFirstInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addFirst(1);
    int result = list.removeFirst();
    assertEquals(1, result);
    assertEquals(0, list.size());
  }

  @org.junit.Test(expected = NoSuchElementException.class)
  public void removeFirstIntegerException() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.removeFirst();
  }

  @org.junit.Test
  public void removeFirstString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("Hello");
    String result = list.removeFirst();
    assertEquals("Hello", result);
    assertEquals(0, list.size());
  }

  @org.junit.Test(expected = NoSuchElementException.class)
  public void removeFirstStringException() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.removeFirst();
  }

  @org.junit.Test
  public void removeFirstCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.addFirst('A');
    char result = list.removeFirst();
    assertEquals('A', result);
    assertEquals(0, list.size());
  }

  @org.junit.Test(expected = NoSuchElementException.class)
  public void removeFirstCharacterException() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.removeFirst();
  }

  @org.junit.Test
  public void removeElementAtRandomIndexInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    int result = list.remove(2);
    assertEquals(2, result);
    assertEquals(99, list.size());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void removeElementAtRandomIndexIntegerException() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    list.remove(100);
  }

  @org.junit.Test
  public void removeElementAtRandomIndexString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    String result = list.remove(1);
    assertEquals("B", result);
    assertEquals(2, list.size());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void removeElementAtRandomIndexStringException() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    list.remove(3);
  }

  @org.junit.Test
  public void removeElementAtRandomIndexCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    char result = list.remove(2);
    assertEquals('C', result);
    assertEquals(25, list.size());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void removeElementAtRandomIndexCharacterException() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    list.remove(26);
  }

  @org.junit.Test
  public void indexOfInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addFirst(1);
    int location = list.indexOf(1);
    int notInList = list.indexOf(2);
    assertEquals(0, location);
    assertEquals(-1, notInList);
  }

  @org.junit.Test
  public void indexOfString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    int location = list.indexOf("A");
    int notInList = list.indexOf("B");
    assertEquals(0, location);
    assertEquals(-1, notInList);
  }

  @org.junit.Test
  public void indexOfCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    list.addFirst('A');
    int location = list.indexOf('A');
    int notInList = list.indexOf('B');
    assertEquals(0, location);
    assertEquals(-1, notInList);
  }

  @org.junit.Test
  public void sizeInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    assertEquals(100, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void sizeString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    assertEquals(3, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void sizeCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    assertEquals(26, list.size());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void isEmptyInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    assertEquals(100, list.size());
    assertEquals(false, list.isEmpty());
    list.clear();
    assertEquals(0, list.size());
    assertEquals(true, list.isEmpty());
  }

  @org.junit.Test
  public void isEmptyString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    assertEquals(3, list.size());
    assertEquals(false, list.isEmpty());
    list.clear();
    assertEquals(0, list.size());
    assertEquals(true, list.isEmpty());
  }

  @org.junit.Test
  public void isEmptyCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    assertEquals(26, list.size());
    assertEquals(false, list.isEmpty());
    list.clear();
    assertEquals(0, list.size());
    assertEquals(true, list.isEmpty());
  }

  @org.junit.Test
  public void clearInteger() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    assertEquals(100, list.size());
    assertEquals(false, list.isEmpty());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void clearString() {
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    assertEquals(3, list.size());
    assertEquals(false, list.isEmpty());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void clearCharacter() {
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      list.add(i, c);
    }
    assertEquals(26, list.size());
    assertEquals(false, list.isEmpty());
    list.clear();
    assertEquals(0, list.size());
  }

  @org.junit.Test
  public void toArrayInteger() {
    int[] result = new int[100];
    for (int i = 0; i < result.length; i++) {
      result[i] = i;
    }
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    Object[] toArray = list.toArray();

    boolean match = false;
    for (int i = 0; i < 100; i++) {
      if (result[i] == (int) toArray[i]) {
        match = true;
      }
    }
    assertEquals(true, match);
  }

  @org.junit.Test
  public void toArrayString() {
    String[] result = {"A", "B", "C"};
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    list.addFirst("A");
    list.add(1, "B");
    list.add(2, "C");
    Object[] toArray = list.toArray();

    boolean match = false;
    for (int i = 0; i < result.length; i++) {
      if (result[i] == (String) toArray[i]) {
        match = true;
      }
    }
    assertEquals(true, match);
  }

  @org.junit.Test
  public void toArrayCharacter() {
    char[] result = new char[26];
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      result[i] = s.charAt(i);
    }
    SinglyLinkedList<Character> list = new SinglyLinkedList<>();
    String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < a.length(); i++) {
      char c = a.charAt(i);
      list.add(i, c);
    }
    Object[] toArray = list.toArray();

    boolean match = false;
    for (int i = 0; i < result.length; i++) {
      if (result[i] == (char) toArray[i]) {
        match = true;
      }
    }
    assertEquals(true, match);
  }

  @org.junit.Test
  public void iteratorIntegerPrintAllElements() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    Iterator<Integer> c = list.iterator();

    while (c.hasNext()) {
      System.out.println(c.next() + " ");
    }
  }

  @org.junit.Test
  public void iteratorIntegerRemoveOneElement() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 0; i < 100; i++) {
      list.add(i, i);
    }
    Iterator<Integer> c = list.iterator();
    c.next();
    c.remove();
    c.next();
    c.remove();
    assertEquals(98, list.size());
  }
}
