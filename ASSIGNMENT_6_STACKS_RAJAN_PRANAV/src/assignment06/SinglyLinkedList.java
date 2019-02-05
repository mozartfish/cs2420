package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

  /** Variable used to keep track of the size of the linked list */
  private int size;

  /** The starting node in the LinkedList */
  private Node head;

  /**
   * A class that defines a node object for the Linked List class
   *
   * @author Pranav Rajan
   */
  private class Node {

    /** The data that is in our node */
    private E data;

    /** Pointer to the next node in the singly linked list */
    private Node next;

    public Node(E data, Node next) {
      this.data = data;
      this.next = next;
    }
  }

  /**
   * Constructor that sets the number of elements of the Linked List to 0 and initializes
   * the starting node to null
   */
  public SinglyLinkedList() {
    size = 0;
    head = null;
  }

//      public static void main(String[] args)
//      {
//        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
//        list.addFirst(1);
//        list.add(1, 3);
//        list.add(1, 2);
//        Iterator<Integer> c = list.iterator();
//        c.next();
//        c.remove();
//        c.next();
//        c.remove();
//        System.out.println("Hello World" ); 
////        c.next();
////        c.remove();
//    //    list.removeFirst();
////        System.out.println(list.toArray());
//      }

  /**
   * Inserts an element at the beginning of the list. O(1) for a singly-linked list.
   *
   * @param element - the element to add
   */
  @Override
  public void addFirst(E element) {
    
    //If the list does not contain any elements
    if (size == 0) {
      head = new Node(element, head);
      size++;
    }
    //If the list already contains one or more elements
    else {
      Node previousHead = head;
      head = new Node(element, previousHead);
      size++;
    }
  }

  /**
   * Inserts an element at a specific position in the list. O(N) for a singly-linked list.
   *
   * @param index - the specified position
   * @param element - the element to add
   * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
   */
  @Override
  public void add(int index, E element) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    
    //If the index is 0
    else if (index == 0) {
      addFirst(element);
    }
    
    //For all other indexes
    else {
      Node nodeAtPreviousIndex = getNode(index - 1);
      Node nodeAtCurrentIndex = getNode(index);
      Node insertNewNode = new Node(element, nodeAtCurrentIndex);

      //Move pointers to point to correct nodes
      nodeAtPreviousIndex.next = insertNewNode;
      size++;
    }
  }

  /**
   * Gets the first element in the list. O(1) for a singly-linked list.
   *
   * @throws NoSuchElementException if the list is empty
   */
  @Override
  public E getFirst() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException();
    } else {
      return head.data;
    }
  }

  /**
   * Gets the element at a specific position in the list. O(N) for a singly-linked list.
   *
   * @param index - the specified position
   * @return the element at the position
   * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
   */
  @Override
  public E get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    } else {
      return getNode(index).data;
    }
  }

  /**
   * Removes and returns the first element from the list. O(1) for a singly-linked list.
   *
   * @return the first element
   * @throws NoSuchElementException if the list is empty
   */
  @Override
  public E removeFirst() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException();
    } else {
      E data = getFirst();
      head = head.next;

      //Decrease the number of elements
      size--;
      return data;
    }
  }

  /**
   * Removes and returns the element at a specific position in the list. O(N) for a singly-linked
   * list.
   *
   * @param index - the specified position
   * @return the element at the position
   * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
   */
  @Override
  public E remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    } else if (index == 0) {
      return removeFirst();
    } else {

      Node previousNode = getNode(index - 1);
      Node removeNode = getNode(index);
      E data = removeNode.data;

      //Move all the pointers to point at the correct nodes
      previousNode.next = removeNode.next;

      //Decrease the number of elements
      size--;
      return data;
    }
  }

  /**
   * Determines the index of the first occurrence of the specified element in the list, or -1 if
   * this list does not contain the element. O(N) for a singly-linked list.
   *
   * @param element - the element to search for
   * @return the index of the first occurrence; -1 if the element is not found
   */
  @Override
  public int indexOf(E element) {
    for (int i = 0; i < size; i++) {
      if (get(i).equals(element)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * O(1) for a singly-linked list.
   *
   * @return the number of elements in this list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * O(1) for a singly-linked list.
   *
   * @return true if this collection contains no elements; false, otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /** Removes all of the elements from this list. O(1) for a singly-linked list. */
  @Override
  public void clear() {
    head = null;
    size = 0;
  }

  /**
   * Generates an array containing all of the elements in this list in proper sequence (from first
   * element to last element). O(N) for a singly-linked list.
   *
   * @return an array containing all of the elements in this list, in order
   */
  @Override
  public Object[] toArray() {
    Object[] data = new Object[size];

    Node temp = head;

    for (int i = 0; i < size; i++) {
      data[i] = get(i);
      temp = temp.next;
    }
    return data;
  }

  /**
   * @return an iterator over the elements in this list in proper sequence (from first element to
   *     last element)
   */
  @Override
  public Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements Iterator<E> {

    //The starting node in the linked list. As next() is called currentNode is updated to the next node in the linked list
    private Node currentNode;

    //The node before the starting node. When the current node is the head node, the value of this node is null since there is no
    //previous node pointing to the head node
    private Node previousNode;

    //The node before the previous node
    private Node beforePreviousNode;

    public LinkedListIterator() {
      //initialize the starting node to the first node before iterating through the list
      currentNode = head;

      //initialize the node before the starting node to null because there is no node before the head node at the beginning of a linked list
      previousNode = null;

      //initialize the node before the previous node to null because a node does not exist before null at the beginning of a linked list
      beforePreviousNode = null;
    }

    @Override
    public boolean hasNext() {

      //Check if there is another node in the linked list. If there is return true otherwise return false
      while (currentNode != null) {
        return true;
      }
      return false;
    }

    @Override
    public E next() {

      //If there is not another node in the linked list throw a no such element exception to indicate that there are no more nodes in the list
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      //If there is another node in the list then extract the data from the node and store it in a variable
      E data = (E) currentNode.data;

      //Since there is another node in the linked list, shift the pointer of the node before the previous node one spot over so that it points to the previous node
      beforePreviousNode = previousNode;

      //Since there is another node in the linked list, shift the pointer of the node before the current node one spot over so that it points to the current node
      previousNode = currentNode;

      //Since there is another node in the linked list, shift the pointer of the node of the current node one spot over so that it points to the next node in the linked list
      currentNode = currentNode.next;

      //Return the value of data
      return data;
    }

    @Override
    public void remove() {

      //If the remove method was called before the next method then throw an exception to indicate the next method has not yet been called or the remove method has already been called after the last call to the next method
      if (previousNode == null) {
        throw new IllegalStateException();
      }

      //If the remove method gets to this statement, next() has been called at least once. At this point, currentNode is the second element in the list, previous node is the first element in the list, and previous is now null again.
      //If we are removing the first node we set set the pointer pointing to the head node to point instead at the second node in the list and the java garbage collector will take care of the first node.
      if (beforePreviousNode == null) {
        head = currentNode;
      }
      //Otherwise if the remove method is called, remove the previous node that was just seen by next()
      else {
        beforePreviousNode = currentNode.next;
        previousNode = beforePreviousNode;
      }

      //Decrease the number of elements in the linked list
      size--;
    }
  }

  /**
   * Helper method to find a specific node in the Linked List
   *
   * @param index - The specific node in the Linked List requested by the user
   * @return - A node in the Linked List
   */
  private Node getNode(int index) {
    Node temp = head;
    for (int i = 0; i < index; i++) {
      temp = temp.next;
    }
    return temp;
  }
}
