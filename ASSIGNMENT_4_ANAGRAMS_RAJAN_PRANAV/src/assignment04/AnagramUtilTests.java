package assignment04;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.Test;

public class AnagramUtilTests {
  ArrayList<String> actual, expected;

  @Test
  public void test100000RandomStringsSort() {

    for (int i = 0; i < 100000; i++) {

      final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
      SecureRandom rnd = new SecureRandom();
      StringBuilder sb = new StringBuilder(15);
      for (int j = 0; j < 15; j++) sb.append(AB.charAt(rnd.nextInt(AB.length())));
      String a = sb.toString();

      String[] c = a.split("");
      Arrays.sort(c, String.CASE_INSENSITIVE_ORDER);
      String output = "";
      expected = new ArrayList<String>();

      for (String str : c) output = output + str;
      expected.add(output);
      actual = new ArrayList<String>();
      AnagramUtil.sort(a);
      actual.add(AnagramUtil.sort(a));
    }

    assertEquals(expected, actual);
  }

  @Test
  public void testSampleWordList() throws FileNotFoundException {
    File file = new File("src/assignment04/sample_word_list.txt");
    Scanner scn = new Scanner(file);

    assertEquals(7, AnagramUtil.getLargestAnagramGroup(scn).length);
  }

  @Test
  public void testNullScanner() {
    Scanner scn = null;
    assertEquals(0, AnagramUtil.getLargestAnagramGroup(scn).length);
  }

  @Test
  public void testEmptyScanner() throws FileNotFoundException {
    File file = new File("src/assignment04/EmptyFile.txt");
    Scanner scn = new Scanner(file);
    assertEquals(0, AnagramUtil.getLargestAnagramGroup(scn).length);
  }

  @Test
  public void testMixedScanner() throws FileNotFoundException {
    File file = new File("src/assignment04/MixedFile.txt");
    Scanner scn = new Scanner(file);

    assertEquals(4, AnagramUtil.getLargestAnagramGroup(scn).length);
  }

  @Test
  public void testNullArray() {
    String[] array = null;
    assertEquals(0, AnagramUtil.getLargestAnagramGroup(array).length);
  }

  @Test
  public void testEmptyArray() {
    String[] array = new String[0];
    assertEquals(0, AnagramUtil.getLargestAnagramGroup(array).length);
  }

  @Test
  public void testNullString() {
    assertEquals(null, AnagramUtil.sort(null));
  }

  @Test(expected = NullPointerException.class)
  public void testNullComparator() {
    String[] array = new String[] {"djffiu", "uerfhwfds", "erffr"};
    AnagramUtil.insertionSort(array, null);
  }

  @Test(expected = NullPointerException.class)
  public void testNullArrayInsertionSort() {
    AnagramUtil.insertionSort(null, String.CASE_INSENSITIVE_ORDER);
  }

  @Test
  public void testNullLhsStringAreAnagrams() {
    assertEquals(false, AnagramUtil.areAnagrams(null, "notNull"));
  }

  @Test
  public void testNullRhsStringAreAnagrams() {
    assertEquals(false, (AnagramUtil.areAnagrams("notNull", null)));
  }
}
