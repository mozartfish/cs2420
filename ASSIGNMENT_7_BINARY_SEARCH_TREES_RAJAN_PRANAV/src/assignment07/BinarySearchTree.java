package assignment07;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<? super E>> implements SortedSet<E> {

  /** Variable used to keep track of the size of the linked list */
  private int size;

  /** The root of our Binary Search Tree */
  Node root;

  /**
   * A class that defines a node object for the Binary Search Tree class
   *
   * @author Pranav Rajan and Hank Gansert
   * @version March 6, 2018
   */
  private class Node {

    /** The generic type data stored in the node */
    private E data;

    /** The reference to the left child of the node */
    private Node left;

    /** The reference to the right child of the node */
    private Node right;

    public Node(E data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    /**
     * Generates a string containing all of the edges in the tree rooted at "this" node, in DOT
     * format. Assumes this node has member variables called data, leftChild, and
     * rightChild.
     *
     * @author Dr. Erin Parker
     * @return DOT format string to enter at http://www.webgraphviz.com
     */
    public String generateDot() {
      String ret = "  node" + data + " [label = \"<f0> |<f1> " + data + "|<f2> \"]\n";
      if (left != null)
        ret += "  node" + data + ":f0 -> node" + left.data + ":f1\n" + left.generateDot();
      if (right != null)
        ret += "  node" + data + ":f2 -> node" + right.data + ":f1\n" + right.generateDot();

      return ret;
    }
  }

  /** Constructor that initializes the starting node of the Binary Search Tree to null */
  public BinarySearchTree() {
    size = 0;
    root = null;
  }

  /**
   * @return the first (lowest, smallest) element currently in this set
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public E first() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    Node temp = root;
    while (temp.left != null) {
      temp = temp.left;
    }
    return temp.data;
  }

  /**
   * @return the last (highest, largest) element currently in this set
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public E last() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    Node temp = root;
    while (temp.right != null) {
      temp = temp.right;
    }
    return temp.data;
  }

  /**
   * Adds the specified element to this set if it is not already present and not set to null.
   *
   * @param o -- element to be added to this set
   * @return true if this set did not already contain the specified element
   */
  @Override
  public boolean add(E element) {

    E o = element;

    if (this.contains(o)) {
      return false;
    } else {
      root = insertElement(root, o);
      size++;
      return true;
    }
  }

  /**
   * A helper method to add a node to a Binary Search Tree
   *
   * @param parent - the root node of the Binary Search Tree
   * @param o
   * @return - a node whose data is the element to be inserted
   */
  private Node insertElement(Node parent, E o) {

    //base case - when you reach the specific location where you want to insert a new node
    if (parent == null) {
      parent = new Node(o, null, null);
    }

    //if the element to be inserted is less than the root node, traverse the left subtree until you reach the location to insert the node and insert the node
    if (o.compareTo(parent.data) < 0) {
      parent.left = insertElement(parent.left, o);
    }

    //if the element to be inserted is less than the root node, traverse the right subtree until you reach the location to insert the node and insert the node
    if (o.compareTo(parent.data) > 0) {
      parent.right = insertElement(parent.right, o);
    }
    return parent;
  }

  /**
   * Adds all of the elements in the specified collection to this set if they are not already
   * present and not set to null.
   *
   * @param c -- collection containing elements to be added to this set
   * @return true if this set changed as a result of the call
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean addAll(Collection<? extends E> elements) {

    //add all the elements in the specified collection to this set if they are not already present and not set to null.
    boolean addAll = false;

    for (Object c : elements) {
      if (add((E) c)) {
        addAll = true;
      }
    }
    return addAll;
  }

  /** Removes all of the elements from this set. The set will be empty after this call returns. */
  @Override
  public void clear() {
    size = 0;
    root = null;
  }

  /**
   * @param o -- element whose presence in this set is to be tested
   * @return true if this set contains the specified element
   */
  @Override
  public boolean contains(Object element) {

    //cast element to type E
    @SuppressWarnings("unchecked")
    E o = (E) element;
    return Search(root, o);
  }

  /**
   * A helper method to see if the Binary Search Tree contains the specific element in the set
   *
   * @param parent - the root node of the Binary Search Tree
   * @param element - the element you are looking for in the Binary Search Tree
   * @return - a boolean statement stating whether you have found the element
   */
  private boolean Search(Node parent, E element) {

    //base case - if the root is null or the element you are looking for is not in the set return false
    if (parent == null) {
      return false;
    }

    //if the element that we are looking for matches the data in one of the nodes return true
    if (element.compareTo(parent.data) == 0) {
      return true;
    }

    //if the element is less than the root node's data search all nodes on the left side for the element
    if (element.compareTo(parent.data) < 0) {
      return Search(parent.left, element);
    }

    //if the element is greater than the root node's data search all nodes on the right side for the element
    else {
      return Search(parent.right, element);
    }
  }

  /**
   * @param c -- collection to be checked for containment in this set
   * @return true if this set contains all of the elements of the specified collection
   */
  @Override
  public boolean containsAll(Collection<?> elements) {

    //set a boolean flag to keep track if this set contains all of the elements of the specified collection
    boolean containsAll = false;
    
    //if the size of the collection is zero then return true because if the BST is filled then the empty collection is a subset
    if (elements.size() == 0) {
      return true;
    }
    for (Object c : elements) {
      if (contains(c)) {
        containsAll = true;
      } else {
        return false;
      }
    }
    return containsAll;
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
  @Override
  public boolean remove(Object element) {

    //cast element to type e
    E o = (E) element;

    //check if the Binary Search Tree contains the element you want to remove. If it does not return false.
    if (!contains(o)) {
      return false;
    }
    deleteElement(o);
    size--;
    return true;
  }

  /**
   * A helper method to remove the element requested by the user from the Binary Search Tree
   *
   * @param o - the element we want to remove
   */
  private void deleteElement(E o) {
    //Create a temporary node to store the location of the root to find the parent of node we are interested in deleting
    Node parent = root;

    //Create a temporary node to store the location of the node we want to remove
    Node nodeToRemove = root;

    //enter this block of code to determine the parent of the node you want to remove
    // and to determine which node you want to remove
    while (nodeToRemove.data.compareTo(o) != 0) {
      parent = nodeToRemove;
      if (o.compareTo(nodeToRemove.data) > 0) {
        nodeToRemove = nodeToRemove.right;
      } else {
        nodeToRemove = nodeToRemove.left;
      }
    }

    //Case 1: Remove a leaf Node
    
    //case where the root node exists and has no children and the element we want to remove is the root of the tree
    if (o.compareTo(nodeToRemove.data) == 0 && o.compareTo(parent.data) == 0 && o.compareTo(root.data) == 0 && root.left == null && root.right == null)
    {
      parent = null;
      nodeToRemove = null;
      root = null;
    }

    //if the node we want to remove has no left child or right child set to null
    else if (nodeToRemove.left == null && nodeToRemove.right == null) {
      nodeToRemove = null;

      //Once the node we want to remove is set to null, set the node removed parent's pointer to point to null
      if (parent.left != null && parent.left.data.compareTo(o) == 0) {
        parent.left = null;
      }
      if (parent.right != null && parent.right.data.compareTo(o) == 0) {
        parent.right = null;
      }
    }

    //Case 2: A Parent with 1 Child
    else if ((nodeToRemove.left != null && nodeToRemove.right == null)
        || (nodeToRemove.right != null && nodeToRemove.left == null)) {

      //when the root has a single child
      if (nodeToRemove == root) {
        if (nodeToRemove.left != null) {
          root = root.left;
        }
        if (nodeToRemove.right != null) {
          root = root.right;
        }
      } else {
        // if the node we want to remove is a left node and has a child set the parent of this node to point to that nodes child and remove the node
        if (nodeToRemove.left != null) {
          if (parent.left != null && o.compareTo(parent.left.data) == 0) {
            parent.left = nodeToRemove.left;
          }
          if (parent.right != null && o.compareTo(parent.right.data) == 0) {
            parent.right = nodeToRemove.left;
          }
        }

        if (nodeToRemove.right != null) {
          if (parent.left != null && o.compareTo(parent.left.data) == 0) {
            parent.left = nodeToRemove.right;
          }
          if (parent.right != null && o.compareTo(parent.right.data) == 0) {
            parent.right = nodeToRemove.right;
          }
        }
      }
    }

    //Case 3: A Parent with 2 Children
    //    else if (nodeToRemove.left != null && nodeToRemove.right != null) {
    else {
      //create a temporary node that is set to the node we want to remove
      Node replacementItem = nodeToRemove;

      //set the temporary node we created above to it's right child to find the smallest element on the right subtree
      replacementItem = replacementItem.right;
      while (replacementItem.left != null) {
        replacementItem = replacementItem.left;
      }
      //store value of the node with the smallest element on the right subtree
      E temp = replacementItem.data;

      //call the delete method to remove the leaf node containing the the value stored in temp
      deleteElement(replacementItem.data);

      //set the value of the node we want to remove to the data we found above
      nodeToRemove.data = temp;
    }
  }

  /**
   * Removes from this set all of its elements that are contained in the specified collection.
   *
   * @param c -- collection containing elements to be removed from this set
   * @return true if this set changed as a result of the call
   */
  @Override
  public boolean removeAll(Collection<?> elements) {

    //set a boolean flag to keep track of removing all the elements that are in the specific collection
    boolean removeAll = false;
    for (Object c : elements) {
      if (remove(c)) {
        removeAll = true;
      }
    }
    return removeAll;
  }

  /** @return the number of elements in this set */
  @Override
  public int size() {
    return size;
  }

  /** @return an array containing all of the elements in this set, in sorted (ascending) order. */
  @Override
  public Object[] toArray() {

    ArrayList<Object> result = new ArrayList<>();

    return traverseToArray(root, result);
  }

  private Object[] traverseToArray(Node temp, ArrayList<Object> result) {
    if (temp != null) {
      traverseToArray(temp.left, result);
      result.add(temp.data);
      traverseToArray(temp.right, result);
    }
    return result.toArray();
  }

  /**
   * Generates a string containing all of the edges in a BST, in DOT format. Assumes root reference
   * of this BST is called root.
   *
   * @return DOT format string to enter at http://www.webgraphviz.com
   * @author Dr. Erin Parker
   */
  public String generateDot() throws Exception {
    if (root == null) throw new Exception("BST not large enough to visualize.");

    String result = "digraph BST {\n";
    result += "  node [shape=record]\n";

    result += root.generateDot();

    return result + "}";
  }
}
