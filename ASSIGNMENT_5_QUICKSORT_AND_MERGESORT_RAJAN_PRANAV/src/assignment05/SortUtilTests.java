package assignment05;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.junit.Test;

public class SortUtilTests {

  @Test
  public void testQuickSortSmallIntegers() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    int[] test = { 5, 5, 6, 6, 4, 4, 5, 5, 4, 4, 6, 6, 5, 5 };
    for(Integer j : test) {
      list.add(j);
    }
    ArrayList<Integer> actual = new ArrayList<Integer>(list);
    ArrayList<Integer> expected = new ArrayList<Integer>(list);
    
    Comparator<Integer> comp = (lhs, rhs) -> lhs - rhs;
    SortUtil.quicksort(actual, comp);
    Collections.sort(expected, comp);

    assertEquals(expected, actual);
  }
  
  @Test
  public void testQuickSortChars() {
    ArrayList<Character> list = new ArrayList<Character>();
    char[] test = { 'w', 'w', 'V', 'c', 'v', 'h', 'g', 'Q', 'n', 'N', 'V', 'q', 's', 'w' };
    for(Character j : test) {
      list.add(j);
    }
    ArrayList<Character> actual = new ArrayList<Character>(list);
    ArrayList<Character> expected = new ArrayList<Character>(list);
    
    Comparator<Character> comp = (lhs, rhs) -> lhs.compareTo(rhs);
    SortUtil.quicksort(actual, comp);
    Collections.sort(expected, comp);

    assertEquals(expected, actual);
  }

  @Test
  public void testMergeSortIntegers() {
    ArrayList<Integer> randList = Generator.generateIntegers(100000);
    Comparator<Integer> comp = (lhs, rhs) -> lhs - rhs;

    ArrayList<Integer> actual = new ArrayList<Integer>(randList);
    ArrayList<Integer> expected = new ArrayList<Integer>(randList);

    SortUtil.mergesort(actual, comp);
    Collections.sort(expected, comp);

    assertEquals(expected, actual);
  }

  @Test
  public void testQuickSortIntegers() {
    ArrayList<Integer> randList = Generator.generateIntegers(100000);
    Comparator<Integer> comp = (lhs, rhs) -> rhs - lhs;

    ArrayList<Integer> actual = new ArrayList<Integer>(randList);
    ArrayList<Integer> expected = new ArrayList<Integer>(randList);

    SortUtil.quicksort(actual, comp);
    Collections.sort(expected, comp);

    assertEquals(expected, actual);
  }

  @Test
  public void testMergeSortStrings() {
    ArrayList<String> randList = Generator.generateStrings(100000);
    Comparator<String> comp = (lhs, rhs) -> lhs.compareTo(rhs);

    ArrayList<String> actual = new ArrayList<String>(randList);
    ArrayList<String> expected = new ArrayList<String>(randList);

    SortUtil.mergesort(actual, comp);
    Collections.sort(expected, comp);

    assertEquals(expected, actual);
  }

  @Test
  public void testQuickSortStrings() {
    ArrayList<String> randList = Generator.generateStrings(100000);
    Comparator<String> comp = (lhs, rhs) -> lhs.compareTo(rhs);

    ArrayList<String> actual = new ArrayList<String>(randList);
    ArrayList<String> expected = new ArrayList<String>(randList);

    SortUtil.quicksort(actual, comp);
    Collections.sort(expected, comp);

    assertEquals(expected, actual);
  }

}
