package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A set of methods to determine whether two words are anagrams and to find the largest number of
 * anagrams in a list of words.
 *
 * @author Pranav Rajan and Abdullah Almanie
 * @version February 7, 2018
 */
public class AnagramUtil {

  /**
   * This method returns the sorted version of the input string. The sorting must be accomplished
   * using an insertion sort.
   *
   * @param word - a string that can be of any length and can contain any characters
   * @return - a string with all the characters sorted lexicographically if the string passed in is
   *     null, return null
   */
  public static String sort(String word) {

    //if the string passed in is null return null
    if (word == null) {
      return null;
    }

    //take the very long string and split that string into individual words and store those words in an array
    String[] wordArray = word.split("");

    //create a new comparator object that ignores capitalization and compares strings lexicographically
    Comparator<String> comp = (rhs, lhs) -> rhs.compareToIgnoreCase(lhs);

    //call the insertion sort and pass it the comparator and the word array to sort all the words in the word array
    insertionSort(wordArray, comp);

    //create a new string builder object to re-assemble the words in the now sorted word array back into a string
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < wordArray.length; i++) {
      sb.append(wordArray[i]);
    }
    return sb.toString();
  }

  /**
   * An insertion sort that sorts all the unsorted elements based on the order specified
   *
   * @param array - an unsorted list of items
   * @param comp - a comparator that determines the order of how to sort the elements in the array
   *     based on the order specified by the user
   * @throws - a null pointer exception if a null argument is passed
   */
  public static <T> void insertionSort(T[] array, Comparator<? super T> comp) {

    //if either the array or comparator are null throw a null pointer
    if (array == null || comp == null) {
      throw new NullPointerException();
    }

    //the insertion sort algorithm
    for (int start = 1; start < array.length; start++) {
      T key = array[start];
      int index = start - 1;
      while (index >= 0 && comp.compare(array[index], key) > 0) {
        array[index + 1] = array[index];
        index--;
        array[index + 1] = key;
      }
    }
  }

  /**
   * This method returns true if the two input strings are anagrams of each other, otherwise returns
   * false. If either argument is null, the method should return false.
   *
   * @param lhs - one of the words being compared to another word to check if they are anagrams
   *     using the sort method to sort the words characters lexicographically
   * @param rhs -one of the words being compared to another word to check if they are anagrams using
   *     the sort method to sort the words characters lexicographically
   * @return - a boolean that states whether two words are anagrams of one another
   */
  public static boolean areAnagrams(String lhs, String rhs) {

    //if either of the two strings passed into areAnagrams are null return false
    if (lhs == null || rhs == null) {
      return false;
    }

    //return a boolean that states whether the two sorted strings are anagrams of one another
    return sort(lhs).equalsIgnoreCase((sort(rhs)));
  }

  /**
   * This method returns the largest group of anagrams in the input array of words, in no particular
   * order. It returns an empty array if there are no anagrams in the input array, or if a null
   * argument is passed in.
   *
   * @param array - an unsorted list of words
   * @return - the largest group of anagrams in the list of words
   */
  public static String[] getLargestAnagramGroup(String[] array) {

    //if the array passed in is null or the size of the array passed in is 0, return a new string array of size 0
    if (array == null || array.length == 0) {
      return new String[0];
    }

    //sort all the words in the the array passed into groups of anagrams
    insertionSort(
        array,
        (lhs, rhs) -> {
          return sort(lhs).compareToIgnoreCase(sort(rhs));
        });

    //create an array list to store a group of anagrams
    ArrayList<String> group = new ArrayList<String>();

    //whichever anagram group has the largest number of anagrams store those anagrams in a new array list
    ArrayList<String> result = new ArrayList<String>();

    //create a temporary string to start off the comparisons and grouping of anagrams
    String temp = array[0];

    //loop through all the groups of anagrams in the array to find the largest group of anagrams in the set
    for (int i = 1; i < array.length; i++) {
      if (areAnagrams(temp, array[i])) {
        if (!group.contains(temp)) {
          group.add(temp);
        }
        group.add(array[i]);

        if (group.size() > result.size()) {
          result.clear();
          result.addAll(group);
        }
      } else {
        group.clear();
        temp = array[i];
      }
    }
    return result.toArray(new String[result.size()]);
  }

  /**
   * Behaves the same as the previous method, but reads the list of words from a file using a Java
   * Scanner (Links to an external site.)Links to an external site.. It is assumed that the file
   * contains one word per line. If the file does not exist or is empty, the method returns an empty
   * array because there are no anagrams.
   *
   * @param scanner - a scanner object that contains the file
   * @return - the largest group of anagrams in the list of words
   */
  public static String[] getLargestAnagramGroup(Scanner scanner) {
    if (scanner == null) {
      return new String[0];
    }
    ArrayList<String> arr = new ArrayList<>();

    while (scanner.hasNextLine()) {
      arr.add(scanner.nextLine());
    }
    return getLargestAnagramGroup(arr.toArray(new String[arr.size()]));
  }
}
