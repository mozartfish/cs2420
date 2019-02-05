package assignment06;

import java.util.NoSuchElementException;

public class StackLinkedList<E> implements Stack<E> {

  /** A stack backed by a singly linked list */
  private SinglyLinkedList<E> linkedStack;

  public StackLinkedList() {
    linkedStack = new SinglyLinkedList<E>();
  }

  //  public static void main(String[] args)
  //  {
  //    StackedLinkedList<Integer> stack = new StackedLinkedList<Integer>();
  //    stack.push(1);
  //    stack.push(2);
  //    stack.peek();
  //    stack.pop();
  //  }
  /** Removes all of the elements from the stack. */
  @Override
  public void clear() {
    linkedStack.clear();
  }

  /** Returns true if the stack contains no elements; false, otherwise. */
  @Override
  public boolean isEmpty() {
    return linkedStack.isEmpty();
  }

  /**
   * Returns, but does not remove, the element at the top of the stack.
   *
   * @return the element at the top of the stack
   * @throws NoSuchElementException if the stack is empty
   */
  @Override
  public E peek() throws NoSuchElementException {
    if (linkedStack.isEmpty()) {
      throw new NoSuchElementException();
    } else {
      return linkedStack.getFirst();
    }
  }

  /**
   * Returns and removes the item at the top of the stack.
   *
   * @return the element at the top of the stack
   * @throws NoSuchElementException if the stack is empty
   */
  @Override
  public E pop() throws NoSuchElementException {
    if (linkedStack.isEmpty()) {
      throw new NoSuchElementException();
    } else {
      return linkedStack.removeFirst();
    }
  }

  /**
   * Adds a given element to the stack, putting it at the top of the stack.
   *
   * @param element - the element to be added
   */
  @Override
  public void push(E element) {
    linkedStack.addFirst(element);
  }

  /** @return the number of elements in the stack */
  @Override
  public int size() {
    return linkedStack.size();
  }
}
