package assignment10;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is implemented as a min heap.
 * The min heap is implemented implicitly as an array.
 *
 * @author Dr. Erin Parker and Pranav Rajan
 * @version April 4, 2018
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

  private int currentSize;

  private AnyType[] array;

  private Comparator<? super AnyType> cmp;

  /**
   * Constructs an empty priority queue. Orders elements according to their natural ordering (i.e.,
   * AnyType is expected to be Comparable) AnyType is not forced to be Comparable.
   */
  public PriorityQueue() {
    currentSize = 0;
    cmp = null;
    array = (AnyType[]) new Object[10]; // safe to ignore warning
  }

  /**
   * Construct an empty priority queue with a specified comparator. Orders elements according to the
   * input Comparator (i.e., AnyType need not be Comparable).
   */
  public PriorityQueue(Comparator<? super AnyType> c) {
    currentSize = 0;
    cmp = c;
    array = (AnyType[]) new Object[10]; // safe to ignore warning
  }

  /** @return the number of items in this priority queue. */
  public int size() {
    return currentSize;
  }

  /** Makes this priority queue empty. */
  public void clear() {
    currentSize = 0;
  }

  /**
   * @return the minimum item in this priority queue.
   * @throws NoSuchElementException if this priority queue is empty.
   *     <p>(Runs in constant time.)
   */
  public AnyType findMin() throws NoSuchElementException {
    // FILL IN -- do not return null
    if (currentSize == 0) {
      throw new NoSuchElementException();
    }
    return array[0];
  }

  /**
   * Removes and returns the minimum item in this priority queue.
   *
   * @return the minimum item in this priority queue.
   * @throws NoSuchElementException if this priority queue is empty.
   *     <p>(Runs in logarithmic time.)
   */
  public AnyType deleteMin() throws NoSuchElementException {
    // FILL IN -- do not return null
    // if the heap is empty, throw a NoSuchElementException
    if (currentSize == 0) {
      throw new NoSuchElementException();
    }

    // store the minimum item so that it may be returned at the end
    AnyType minItem = this.findMin();

    //replace the item at minIndex with the last item in the tree
    array[0] = array[currentSize - 1];

    // update size
    currentSize--;

    // percolate the item at minIndex down the tree until the binary heap order is restored
    // It is STRONGLY recommended that you write a percolateDown helper method!
    percolateDown();

    // return the minimum item that was stored
    return minItem;
  }

  /**
   * A helper method that restructures the Binary Heap such that it satisfies the structure property
   * of the Binary Heap PercolateDown deals with reordering all the elements left in the Binary Heap
   * such that the structure property of the Binary Heap is maintained
   */
  private void percolateDown() {
    //initialize the location of the minimum value in the heap (this is where the percolation begins)
    int minIndex = 0;

    //initialize the index of the left child of the current minIndex Value
    int leftChildIndex = this.getLeftChild(minIndex);

    //initialize the index of the right child of the current minIndex value
    int rightChildIndex = this.getRightChild(minIndex);

    //initialize a variable that keeps track of the index of whichever element is smaller after comparing the elements located at the left and right child indexes
    int smallerElementIndex = 0;

    //check to see if the index of the leftChild of the current minIndex is within the bounds of the array
    while (leftChildIndex < currentSize) {

      //temporarily define the smallerIndex as the leftChildIndex which is what we first check for in the array
      smallerElementIndex = leftChildIndex;

      //check to see if the index of the rightChild of the current minIndex is within the bounds of the array
      if (rightChildIndex < currentSize) {

        //compare the elements located at the leftChildIndex and the rightChildIndex
        //depending on which element is smaller, store the smaller element's index in the smallerIndex variable
        if (this.compare(array[rightChildIndex], array[leftChildIndex]) < 0) {
          smallerElementIndex = rightChildIndex;
        }
      }

      //if the element located at the minIndex is less than the element located at the smallerIndex, we have satisfied the structure property of the Binary Heap
      //return to exit the while loop
      if (this.compare(array[minIndex], array[smallerElementIndex]) < 0) {
        return;
      }
      //if the structure property of the Binary Heap is not satisfied then continue swapping the smallest element with the element located at the minIndex
      //until the Binary Heap structure property is achieved
      else {
        //create a temporary variable to store the data located at the minIndex
        AnyType temp = array[minIndex];

        //store the element located at the smallerIndex
        //into the minIndex spot
        array[minIndex] = array[smallerElementIndex];

        //store the element located at the minIndex in the array
        //into the  smallerElementIndex spot
        array[smallerElementIndex] = temp;
      }
      //set the value of the minIndex to the smallerElementIndex to reflect
      //the swap of the element located at the minIndex with the element located at the smallerElementIndex
      //since we have swapped the element located at the minIndex with the
      //element at the smallerElementIndex we want to compare the element that we inserted into the
      //smallerElementIndex with the children of the element we just inserted into the minIndex
      minIndex = smallerElementIndex;

      //find the index of the leftChild of the root
      leftChildIndex = this.getLeftChild(minIndex);

      //find the index of the rightChild of the root
      rightChildIndex = this.getRightChild(minIndex);
    }
  }
      
      
  /**
   * Adds an item to this priority queue.
   *
   * <p>(Runs in logarithmic time.) Can sometimes terminate early.
   *
   * @param item - the item to be inserted
   */
  public void add(AnyType item) {

    // if the array is full, double its capacity
    if (currentSize == array.length) {

      //create a temporary array to store all the elements in the original array
      AnyType[] tempArray = array;

      //double the size of the array
      array = (AnyType[]) new Object[2 * array.length];

      //fill the newly resized array with data
      for (int i = 0; i < tempArray.length; i++) {
        array[i] = tempArray[i];
      }
    }

    // add the new item to the next available node in the tree, so that
    // complete tree structure is maintained
    array[currentSize] = item;

    //update size
    currentSize++;

    // percolate the new item up the levels of the tree until heap order is restored
    // It is STRONGLY recommended that you write a percolateUp helper method!
    percolateUp();
  }
  /**
   * A helper method that restructures the Binary Heap such that it satisfies the structure property
   * of the Binary Heap PercolateUp deals with reordering a newly added element to the Binary Heap
   * such that the structure property of the Binary Heap is maintained
   */
  private void percolateUp() {

    //store the location of the index where we inserted the item (this is the index where the percolation begins)
    int startIndex = currentSize - 1;

    //find the index of the parent of the element that was inserted into the Binary Heap
    int parentIndex = this.getParent(startIndex);

    //compare the parent with the child
    //if the element located at the child index is less than the parent index
    //swap it with the element located at the parent index such that
    //the method returns a tree that satisfies the definition of a complete tree
    //make sure that the parentIndex exists so that there are no null pointer exceptions thrown
    while (parentIndex >= 0 && this.compare(array[parentIndex], array[startIndex]) > 0) {

      //create a temporary variable to store the data located at the hole
      AnyType temp = array[startIndex];

      //store the element located at the parent index in the startIndex
      //into the startIndex spot
      array[startIndex] = array[parentIndex];

      //store the element located at the startIndex in the array
      //into the parentIndex spot
      array[parentIndex] = temp;

      //set the value of the start index to the parent index to reflect
      //the swap of the element located at the start index with the element located at the parentIndex
      //since we have swapped the element located at the startIndex with the
      //element at the parentIndex we want to compare the element that we inserted into the
      //parent index with the parent of the element we just inserted into the startIndex
      startIndex = parentIndex;

      //since the startIndex points to the parent index
      //we want to assign the parentIndex to the index of the parent of the element located at StartIndex
      parentIndex = this.getParent(startIndex);
    }
    return;
  }

  /**
   * Generates a string for visualizing the binary heap.
   *
   * @return DOT format string to enter at http://www.webgraphviz.com
   */
  public String generateDot() {
    String result = "digraph Heap {\n\tnode [shape=record]\n";

    for (int i = 0; i < currentSize; i++) {
      result += "\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]\n";
      if (((i * 2) + 1) < currentSize)
        result += "\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1\n";
      if (((i * 2) + 2) < currentSize)
        result += "\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1\n";
    }
    return result + "}\n";
  }

  /**
   * Internal method for comparing lhs and rhs using Comparator if provided by the user at
   * construction time, or Comparable, if no Comparator was provided.
   *
   * @param lhs - the left-hand-side item being compared
   * @param rhs - the right-hand-side item being compared
   * @return a negative integer if lhs < rhs, 0 if lhs == rhs, a positive integer if lhs > rhs
   */
  private int compare(AnyType lhs, AnyType rhs) {
    if (cmp == null) {
      return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
    }
    // We won't test your code on non-Comparable types if we didn't supply a Comparator

    return cmp.compare(lhs, rhs);
  }

  //LEAVE IN for grading purposes
  public Object[] toArray() {
    Object[] ret = new Object[currentSize];
    for (int i = 0; i < currentSize; i++) {
      ret[i] = array[i];
    }
    return ret;
  }
  /**
   * A set of helper methods to traverse the Binary Heap to find the parent node, left child node,
   * and right child node
   */
  private int getParent(int index) {
    return (index - 1) / 2;
  }

  private int getLeftChild(int index) {
    return (2 * index) + 1;
  }

  private int getRightChild(int index) {
    return (2 * index) + 2;
  }
}
