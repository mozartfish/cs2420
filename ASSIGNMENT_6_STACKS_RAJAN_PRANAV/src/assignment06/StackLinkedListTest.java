package assignment06;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;

public class StackLinkedListTest {

  @Test
  public void clearInteger() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    for (int i = 1; i < 11; i++) {
      stack.push(i);
    }
    assertEquals(10, stack.size());
    stack.clear();
    assertEquals(0, stack.size());
  }

  @Test
  public void clearString() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    assertEquals(3, stack.size());
    stack.clear();
    assertEquals(0, stack.size());
  }

  @Test
  public void clearCharacter() {
    StackLinkedList<Character> stack = new StackLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      stack.push(c);
    }
    assertEquals(26, stack.size());
    stack.clear();
    assertEquals(0, stack.size());
  }

  @Test
  public void isEmptyInteger() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    for (int i = 1; i < 11; i++) {
      stack.push(i);
    }
    assertEquals(10, stack.size());
    assertEquals(false, stack.isEmpty());
    stack.clear();
    assertEquals(0, stack.size());
    assertEquals(true, stack.isEmpty());
  }

  @Test
  public void isEmptyString() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    assertEquals(3, stack.size());
    assertEquals(false, stack.isEmpty());
    stack.clear();
    assertEquals(0, stack.size());
    assertEquals(true, stack.isEmpty());
  }

  @Test
  public void isEmptyCharacter() {
    StackLinkedList<Character> stack = new StackLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      stack.push(c);
    }
    assertEquals(26, stack.size());
    assertEquals(false, stack.isEmpty());
    stack.clear();
    assertEquals(0, stack.size());
    assertEquals(true, stack.isEmpty());
  }

  @Test
  public void peekInteger() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    for (int i = 1; i < 11; i++) {
      stack.push(i);
    }
    assertEquals(10, stack.size());
    assertTrue(stack.peek().equals(10));
    stack.pop();
    assertEquals(9, stack.size());
    assertTrue(stack.peek().equals(9));
    stack.pop();
    assertEquals(8, stack.size());
    assertTrue(stack.peek().equals(8));
    stack.pop();
    assertEquals(7, stack.size());
    assertTrue(stack.peek().equals(7));
    stack.pop();
    assertEquals(6, stack.size());
    assertTrue(stack.peek().equals(6));
    stack.pop();
    assertEquals(5, stack.size());
    assertTrue(stack.peek().equals(5));
    stack.pop();
    assertEquals(4, stack.size());
    assertTrue(stack.peek().equals(4));
    stack.pop();
    assertEquals(3, stack.size());
    assertTrue(stack.peek().equals(3));
    stack.pop();
    assertEquals(2, stack.size());
    assertTrue(stack.peek().equals(2));
    stack.pop();
    assertEquals(1, stack.size());
    assertTrue(stack.peek().equals(1));
    stack.pop();
    assertEquals(0, stack.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void peekIntegerException() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    for (int i = 1; i < 11; i++) {
      stack.push(i);
    }
    assertEquals(10, stack.size());
    assertTrue(stack.peek().equals(10));
    stack.pop();
    assertEquals(9, stack.size());
    assertTrue(stack.peek().equals(9));
    stack.pop();
    assertEquals(8, stack.size());
    assertTrue(stack.peek().equals(8));
    stack.pop();
    assertEquals(7, stack.size());
    assertTrue(stack.peek().equals(7));
    stack.pop();
    assertEquals(6, stack.size());
    assertTrue(stack.peek().equals(6));
    stack.pop();
    assertEquals(5, stack.size());
    assertTrue(stack.peek().equals(5));
    stack.pop();
    assertEquals(4, stack.size());
    assertTrue(stack.peek().equals(4));
    stack.pop();
    assertEquals(3, stack.size());
    assertTrue(stack.peek().equals(3));
    stack.pop();
    assertEquals(2, stack.size());
    assertTrue(stack.peek().equals(2));
    stack.pop();
    assertEquals(1, stack.size());
    assertTrue(stack.peek().equals(1));
    stack.pop();
    assertEquals(0, stack.size());
    stack.peek();
  }

  @Test
  public void peekString() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    assertEquals(3, stack.size());
    assertTrue(stack.peek().equals("C"));
    stack.pop();
    assertEquals(2, stack.size());
    assertTrue(stack.peek().equals("B"));
    stack.pop();
    assertEquals(1, stack.size());
    assertTrue(stack.peek().equals("A"));
    stack.pop();
    assertEquals(0, stack.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void peekStringException() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    assertEquals(3, stack.size());
    assertTrue(stack.peek().equals("C"));
    stack.pop();
    assertEquals(2, stack.size());
    assertTrue(stack.peek().equals("B"));
    stack.pop();
    assertEquals(1, stack.size());
    assertTrue(stack.peek().equals("A"));
    stack.pop();
    assertEquals(0, stack.size());
    stack.peek();
  }

  @Test
  public void peekCharacter() {
    StackLinkedList<Character> stack = new StackLinkedList<>();
    stack.push('A');
    stack.push('B');
    stack.push('C');
    assertEquals(3, stack.size());
    assertTrue(stack.peek().equals('C'));
    stack.pop();
    assertEquals(2, stack.size());
    assertTrue(stack.peek().equals('B'));
    stack.pop();
    assertEquals(1, stack.size());
    assertTrue(stack.peek().equals('A'));
    stack.pop();
    assertEquals(0, stack.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void peekCharacterException() {
    StackLinkedList<Character> stack = new StackLinkedList<>();
    stack.push('A');
    stack.push('B');
    stack.push('C');
    assertEquals(3, stack.size());
    assertTrue(stack.peek().equals('C'));
    stack.pop();
    assertEquals(2, stack.size());
    assertTrue(stack.peek().equals('B'));
    stack.pop();
    assertEquals(1, stack.size());
    assertTrue(stack.peek().equals('A'));
    stack.pop();
    assertEquals(0, stack.size());
    stack.peek();
  }

  @Test
  public void popInteger() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    for (int i = 1; i < 101; i++) {
      stack.push(i);
    }
    for (int j = 1; j < 101; j++) {
      stack.pop();
    }
    assertTrue(stack.isEmpty());
  }

  @Test(expected = NoSuchElementException.class)
  public void popIntegerException() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    for (int i = 1; i < 101; i++) {
      stack.push(i);
    }
    for (int j = 1; j < 101; j++) {
      stack.pop();
    }
    assertTrue(stack.isEmpty());
    stack.pop();
  }

  @Test
  public void popString() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    stack.pop();
    stack.pop();
    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test(expected = NoSuchElementException.class)
  public void popStringException() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    stack.pop();
    stack.pop();
    stack.pop();
    assertTrue(stack.isEmpty());
    stack.pop();
  }

  @Test
  public void popCharacter() {
    StackLinkedList<Character> stack = new StackLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      stack.push(c);
    }
    for (int j = 0; j < s.length(); j++) {
      stack.pop();
    }
    assertTrue(stack.isEmpty());
  }

  @Test(expected = NoSuchElementException.class)
  public void popCharacterException() {
    StackLinkedList<Character> stack = new StackLinkedList<>();
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      stack.push(c);
    }
    for (int j = 0; j < s.length(); j++) {
      stack.pop();
    }
    assertTrue(stack.isEmpty());
    stack.pop();
  }
}
