package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * An implementation of the merge sort, quicksort and insertion algorithms
 *
 * @author Pranav Rajan and Abdullah Almanie
 * @version February 15, 2018
 * @param <T> The type of objects that the user would like to have sorted
 */
public class SortUtil<T> {

  /** A value used to determine when the quickSort algorithm should switch to an insertion sort */
  private static int quickThreshold = 5;

  /** A value used to determine when the mergeSort algorithm should switch to insertion sort */
  private static int mergeThreshold = 0;

  /**
   * The driver method for the mergeSort algorithm. Checks both inputs to make sure that they are
   * not null before the executing the mergeSort algorithm
   *
   * @param input - The ArrayList of elements as specified by the user. If the input is null, the
   *        mergeSort algorithm does not execute and returns nothing.
   * @param comp - The comparator as specified by the user. If the comparator is null, the mergeSort
   *        algorithm does not execute and returns nothing
   */
  public static <T> void mergesort(ArrayList<T> input, Comparator<? super T> comp) {
    
    //if any of the inputs are null return
    if (input == null || comp == null) {
      return;
    }
    
    //Create a new array to pass into the merge sort method to avoid redundant work
    @SuppressWarnings("unchecked")
    T[] result = (T[]) new Object[input.size()]; // unchecked cast noted
    
    mergeSort(input, 0, input.size() - 1, comp, result);
  }


  /**
   * The mergeSort algorithm that uses recursion to sort the elements of the array/ArrayList in
   * order. When the mergeSort algorithm reaches its worst case complexity as determined by the
   * mergeThreshold value, the algorithm switches to insertion sort to sort the remaining elements
   * as specified by the mergeThreshold value
   *
   * @param input - the ArrayList passed into the mergeSort driver method.
   * @param startIndex - the lower bound of the elements that are going to be sorted
   * @param lastIndex - the upper bound of the elements that are going to be sorted
   * @param comp - the comparator as specified by the user
   * @param result - The resulting array/ArrayList of elements that are sorted
   */
  private static <T> void mergeSort(ArrayList<T> input, int startIndex, int lastIndex,
      Comparator<? super T> comp, T[] result) {

    //if the index of the last element is less than or equal to the index of the start element return
    if (lastIndex <= startIndex) {
      return;
    }
    
    //statement that determines when the threshold  for the merge sort is reached and then switches the algorithm to insertion sort
    if (lastIndex - startIndex <= mergeThreshold) {
      insertionSort(input, startIndex, lastIndex, comp);
      return;
    }
    
    //cut the list in half and merge sort both halves
    int middleIndex = startIndex + (lastIndex - startIndex) / 2;
    
    //sort the left half of the array
    mergeSort(input, startIndex, middleIndex, comp, result);
    
    //sort the right half of the array
    mergeSort(input, middleIndex + 1, lastIndex, comp, result);
    
    //merge the two sorted halves back together
    merge(input, startIndex, middleIndex, lastIndex, comp, result);
  }

  /**
   * The merge step of the mergeSort algorithm that merges two sorted lists
   *
   * @param input - the ArrayList passed into the mergeSort method
   * @param startIndex - the lower bound of the elements that are going to be merged
   * @param middleIndex - the middle value that is already in place and which the mergeSort revolves
   *        around
   * @param lastIndex - the upper bound of the elements that are going to be merged
   * @param comp - the comparator that is specified by the user
   * @param result - the resulting array/ArrayList of elements that are sorted
   */
  private static <T> void merge(ArrayList<T> input, int startIndex, int middleIndex, int lastIndex,
      Comparator<? super T> comp, T[] result) {
    int i = startIndex;
    int j = middleIndex + 1;
    for (int k = startIndex; k <= lastIndex; k++) {
      result[k]= input.get(k);
    }
    for (int k = startIndex; k <= lastIndex; k++) {
      if (i > middleIndex) {
        input.set(k, (result[j++]));

      } else if (j > lastIndex) {
        input.set(k, (result[i++]));

      } else if (compare(comp, result[j], result[i])) {
        input.set(k, (result[j++]));

      } else {
        input.set(k, (result[i++]));
      }
    }
  }

  /**
   * The driver method for the quicksort algorithm. Checks both inputs to make sure that they are
   * not null before executing the quicksort algorithm.
   *
   * @param input - The ArrayList of elements as specified by the user. If the input is null, the
   *        quicksort algorithm does not execute and returns nothing.
   * @param comp - The comparator as specified by the user. If the comparator is null, the quicksort
   *        algorithm does not execute and returns nothing
   */
  public static <T> void quicksort(ArrayList<T> input, Comparator<? super T> comp) {
    if (input == null || comp == null) {
      return;
    }
    quickSort(input, 0, input.size() - 1, comp);
  }

  /**
   * The quicksort algorithm that sorts all of the elements in the array/ArrayList using recursion.
   * When the quicksort reaches its worst case complexity as determined by the quickThreshold value,
   * the algorithm switches to insertion sort to sort the remaining elements as specified by the
   * quickThreshold value
   *
   * @param input - the ArrayList passed into the quickSort driver method
   * @param startIndex - the lower bound of the list of elements that are going to be sorted
   * @param lastIndex - the upper bound of the list of elements that are going to be sorted
   * @param comp - the comparator that is specified by the user
   */
  private static <T> void quickSort(ArrayList<T> input, int startIndex, int lastIndex,
      Comparator<? super T> comp) {
    if (lastIndex <= startIndex + quickThreshold) {
      insertionSort(input, startIndex, lastIndex, comp);
      return;
    }
    int pivotIndex = choosePivot(input, startIndex, lastIndex, comp, 1);
    swap(input, pivotIndex, lastIndex);
    T pivot = input.get(lastIndex);
    int j = partition(input, startIndex, lastIndex, comp, pivot);
    quickSort(input, startIndex, j - 1, comp);
    quickSort(input, j + 1, lastIndex, comp);
  }

  /**
   * A helper method designed to perform the partition step of quicksort
   *
   * @param input - the ArrayList passed into the quickSort method
   * @param startIndex - the lower bound of the list of elements that are going to be partitioned
   * @param lastIndex - the upper bound of the list of elements that are going to be partitioned
   * @param comp - the comparator that is specified by the user
   * @return - the index of the pivot
   */
  private static <T> int partition(ArrayList<T> input, int lo, int hi, Comparator<? super T> comp,
      T pivot) {
    int i = lo - 1;
    int j = hi;

    while (true) {
      while (comp.compare(input.get(++i), pivot) < 0);

      while (j > 0 && comp.compare(input.get(--j), pivot) > 0);

      if (i >= j) {
        break;
      } else {
        swap(input, i, j);
      }
    }

    swap(input, i, hi);
    return i;
  }

  /**
   * An insertion sort algorithm that is executed when the quicksort or mergesort algorithms reach
   * their worst case complexity
   *
   * @param input - the ArrayList passed into the mergeSort / quickSort algorithms
   * @param startIndex - the lower bound of the list of elements to be sorted using the
   *        insertionSort algorithm
   * @param lastIndex - the upper bound of the list of elements to be sorted using the insertionSort
   *        algorithm
   * @param comp - the comparator passed into the merge and quickSort algorithms as specified by the
   *        user
   */
  private static <T> void insertionSort(ArrayList<T> input, int startIndex, int lastIndex,
      Comparator<? super T> comp) {
    for (int i = startIndex; i <= lastIndex; i++) {
      for (int j = i; j > startIndex && compare(comp, input.get(j), input.get(j - 1)); j--) {
        swap(input, j, j - 1);
      }
    }
  }

  /**
   * A helper method designed to compare to elements and determine which one is smaller of the two
   *
   * @param comp - a comparator that is passed in by the user
   * @param left - one of the elements of the array/ArrayList to be compared
   * @param right - one of the elements of the array/ArrayList to be compared
   * @return - which element is smaller than the other element as determined by the comparator
   *         passed in
   */
  private static <T> boolean compare(Comparator<? super T> comp, T left, T right) {
    return comp.compare(left, right) < 0;
  }

  /**
   * A helper method designed to swap elements in an array/ArrayList
   *
   * @param input - an ArrayList that contains elements that may contain elements that are either
   *        unsorted or sorted
   * @param i - the index of the one of the elements to be swapped
   * @param j - the index of one of the elements to be swapped
   */
  private static <T> void swap(ArrayList<T> input, int i, int j) {
    T temp = input.get(i);
    input.set(i, input.get(j));
    input.set(j, temp);
  }

  /**
   * Finds the pivot based on the option chosen (1, 2, or 3). Option 1 gets the median of 3 (low,
   * mid, high) as the pivot. Option 2 gets a random pivot. Option 3 gets the first element as the
   * pivot. Option 4 gets the last element as the pivot. Option 5 gets the middle element as the
   * pivot.
   *
   * @param input - the ArrayList passed into the mergeSort method
   * @param lo - the lower bound of the elements
   * @param hi - the higher bound of the elements
   * @param comp - a comparator that is passed in by the user
   * @param option - number to be used in the switch case
   * @return
   */
  private static <T> int choosePivot(ArrayList<T> input, int lo, int hi, Comparator<? super T> comp,
      int option) {
    int mid = (lo + hi) / 2;

    // sort the three elements (low, mid, high) by swapping them in the original list
    switch (option) {
      case 1:
        if (comp.compare(input.get(lo), input.get((lo + hi) / 2)) > 0)
          swap(input, lo, mid);
        if (comp.compare(input.get(lo), input.get(hi)) > 0)
          swap(input, lo, hi);
        if (comp.compare(input.get((lo + hi) / 2), input.get(hi)) > 0)
          swap(input, mid, lo);

        // place pivot before the last element
        swap(input, mid, hi - 1);


        return hi - 1;
      case 2:
        return lo + new Random().nextInt(hi - lo + 1);
      case 3:
        return lo;
      case 4:
        return hi;
      case 5:
        return mid;
    }
    return lo;
  }

  /**
   * Generates and returns an ArrayList of integers 1 to size in ascending order.
   *
   * @param size - size of the list to be returned
   * @return an ArrayList of integers 1 to size in ascending order
   */
  public static ArrayList<Integer> generateAscending(int size) {
    ArrayList<Integer> result = new ArrayList<Integer>();

    for (int i = 1; i <= size; i++) {
      result.add(i);
    }
    return result;
  }

  /**
   * Generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly
   * ordered).
   *
   * @param size - size of the list to be returned
   * @return an ArrayList of integers 1 to size in permuted order
   */
  public static ArrayList<Integer> generateShuffled(int size) {
    ArrayList<Integer> result = new ArrayList<Integer>();

    for (int i = 1; i <= size; i++) {
      result.add(i);
    }
    Collections.shuffle(result);
    return result;
  }

  /**
   * Generates and returns an ArrayList of integers 1 to size in descending order.
   *
   * @param size - size of the list to be returned
   * @return an ArrayList of integers 1 to size in descending order
   */
  public static ArrayList<Integer> generateDescending(int size) {
    ArrayList<Integer> result = new ArrayList<Integer>();

    for (int i = size; i >= 1; i--) {
      result.add(i);
    }
    return result;
  }
}
