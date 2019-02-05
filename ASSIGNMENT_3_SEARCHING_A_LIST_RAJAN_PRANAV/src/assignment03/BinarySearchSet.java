package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<Object> {

  /** Represents a collection of items */
  private E[] list;

  /** Represents the total number of elements in a collection */
  private int size;

  /** The comparator if one is used */
  private Comparator<? super E> comp;

  /** the size of a list */
  private int listSize;

  /**
   * If this constructor is used to create the sorted set, it is assumed that the elements are
   * ordered using their natural ordering (i.e., E implements Comparable<? super E>).
   */
  @SuppressWarnings("unchecked")
  public BinarySearchSet() {
    listSize = 100;
    list = (E[]) new Object[listSize];
    size = 0;
    comp = null;
  }

  /**
   * If this constructor is used to create the sorted set, it is assumed that the elements are
   * ordered using the provided comparator.
   */
  @SuppressWarnings("unchecked")
  public BinarySearchSet(Comparator<? super E> comparator) {
    listSize = 100;
    list = (E[]) new Object[listSize];
    size = 0;
    comp = comparator;
  }

  /**
   * @return The comparator used to order the elements in this set, or null if this set uses the
   *     natural ordering of its elements (i.e., uses Comparable).
   */
  @Override
  public Comparator getComparator() {
    return comp;
  }

  /**
   * @return The comparator used to order the elements in this set, or null if this set uses the
   *     natural ordering of its elements (i.e., uses Comparable).
   */
  public Iterator<E> iterator() {
    return new Iterate();
  }

  /**
   * @return the first (lowest, smallest) element currently in this set
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public Object first() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    return list[0];
  }

  /**
   * @return the last (highest, largest) element currently in this set
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public Object last() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    return list[size - 1];
  }

  /**
   * Adds the specified element to this set if it is not already present and not set to null.
   *
   * @param o -- element to be added to this set
   * @return true if this set did not already contain the specified element
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean add(Object element) {
    if (element == null) {
      throw new NullPointerException();
    }

    //check if the array is completely filled - If it is then double the size of the list
    if (size == list.length) {
      listSize = list.length;
      E[] temp = (E[]) new Object[listSize * 2];
      for (int i = 0; i < listSize; i++) {
        temp[i] = list[i];
      }
      list = temp;
      listSize = list.length;
    }

    //check if the list is empty
    if (this.isEmpty()) {
      list[0] = (E) element;
      size++;
      return true;
    }

    //If the element is not already in the list then add the element to the list
    if (!this.contains(element)) {
      
      //Find the index of the place where we want to insert the element using a binary search
      int index = binaryInsert((E) element);

      //Shift all the elements already in the list one spot over to make room for the new element
      for (int i = size - 1; i >= index; i--) {
        list[i + 1] = list[i];
      }

      //Add the element to the proper index
      list[index] = (E) element;

      size++;
      return true;
    }
    return false;
  }

  /**
   * Adds all of the elements in the specified collection to this set if they are not already
   * present and not set to null.
   *
   * @param c -- collection containing elements to be added to this set
   * @return true if this set changed as a result of the call
   */
  @Override
  public boolean addAll(Collection elements) {
    boolean flag = false;
    Iterator<? super E> c = elements.iterator();
    while (c.hasNext()) {
      Object o = c.next();
      if (!this.contains(o)) {
        this.add(o);
        flag = true;
      }
    }
    return flag;
  }

  /** Removes all of the elements from this set. The set will be empty after this call returns. */
  @Override
  public void clear() {
    @SuppressWarnings("unchecked")
    int listSize = 100;
    E[] temp = (E[]) new Object[listSize];
    list = temp;
    size = 0;
  }

  /**
   * @param o -- element whose presence in this set is to be tested
   * @return true if this set contains the specified element
   */
  @Override
  public boolean contains(Object element) {
    if (element == null) {
      throw new NullPointerException();
    }
    @SuppressWarnings("unchecked")
    int index = binarySearch((E) element);
    if (index == -1) {
      return false;
    }
    return true;
  }

  /**
   * @param c -- collection to be checked for containment in this set
   * @return true if this set contains all of the elements of the specified collection
   */
  @Override
  public boolean containsAll(Collection elements) {
    Iterator<? super E> c = elements.iterator();
    while (c.hasNext()) {
      Object o = c.next();
      if (!this.contains(o)) {
        return false;
      }
    }
    return true;
  }

  /** @return true if this set contains no elements */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Removes the specified element from this set if it is present.
   *
   * @param o -- object to be removed from this set, if present
   * @return true if this set contained the specified element
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean remove(Object element) {
    if (element == null) {
      throw new NullPointerException();
    }
    if (this.isEmpty()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    int index = binarySearch((E) element);
    if (index == -1) {
      return false;
    }
    size--;
    for (int i = index; i <= size - 1; i++) {
      list[i] = list[i + 1];
    }
    return true;
  }

  /**
   * Removes from this set all of its elements that are contained in the specified collection.
   *
   * @param c -- collection containing elements to be removed from this set
   * @return true if this set changed as a result of the call
   */
  @Override
  public boolean removeAll(Collection elements) {
    boolean flag = false;
    Iterator<? super E> c = elements.iterator();
    while (c.hasNext()) {
      Object o = c.next();
      if (this.contains(o)) {
        remove(o);
        flag = true;
      }
    }
    return flag;
  }

  /** @return the number of elements in this set */
  @Override
  public int size() {
    return size;
  }

  /** @return an array containing all of the elements in this set, in sorted (ascending) order. */
  @Override
  public Object[] toArray() {
    Object[] sortedSet = new Object[size];
    for (int i = 0; i < size; i++) {
      sortedSet[i] = list[i];
    }
    return sortedSet;
  }

  /**
   * Compares two elements in this collection, resolving whether to use Comparable or Comparator.
   *
   * @param a - first element
   * @param b - second element
   * @return a negative integer, zero, or a positive integer as the first argument is less than,
   *     equal to, or greater than the second
   * @author Erin Parker
   * @version January 25, 2018
   */
  private int compare(E a, E b) {
    if (comp == null) {

      //This warning is okay because of our contract with the user of MyList<E>.
      //If the first constructor is used, E is expected to be Comparable.
      return ((Comparable<? super E>) a).compareTo(b);
    } else {
      return comp.compare(a, b);
    }
  }

  /**
   * A binary search algorithm that uses the compare method created by Dr. Parker
   *
   * @param o The object that we are looking for in a list
   * @return the index of the location of the element we are looking for. If the element is not
   *     found -1 is returned.
   */
  private int binarySearch(E o) {
    int low = 0;
    int high = size - 1;
    int mid;

    while (low <= high) {
      mid = (low + high) / 2;
      if (compare(list[mid], o) < 0) {
        low = mid + 1;
      } else if (compare(list[mid], o) > 0) {
        high = mid - 1;
      } else {
        return mid;
      }
    }
    return -1; // Error
  }

  /** A binary search insert algorithm that uses the compare method created by Dr. Parker */
  private int binaryInsert(E o) {
    int low = 0;
    int high = size;
    while (low < high) {
      int mid = (low + high) / 2;
      if (compare(list[mid], o) < 0) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return low;
  }
  /** Iterator that defines how to iterate through collections of different of objects */
  protected class Iterate implements Iterator<E> {

    //a marker to indicate the starting element in the collection that we are looking at
    int index = 0;

    @Override
    public boolean hasNext() {
      while (index <= size - 1) {
        return true;
      }
      return false;
    }

    @Override
    public E next() {
      return list[index++];
    }
  }
}
