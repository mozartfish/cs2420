package assignment08;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that defines a node object for the use in a graph data structure
 * @author Pranav Rajan and Hank Gensert
 * @version March 15, 2018
 *
 * @param <Type> - The Type of that that the user wants stored in the node
 */
public class Node<Type> {
  /** A variable that stores the data in the node */
  private Type data;

  /** A node that keeps track of the previous node that came before the current node */
  private Node<Type> cameFrom;

  /** A boolean that states whether a node has been visited */
  private boolean hasBeenVisited;

  /** A list that contains all the nodes that are the neighbor nodes of the current nodes */
  private List<Node<Type>> neighborNodes;

  public Node(Type data, int row, int column) {
    this.data = data;
    this.cameFrom = null;
    this.hasBeenVisited = false;
    this.neighborNodes = new ArrayList<Node<Type>>();
  }

  /**
   * A getter method that returns the data stored in a node in the graph
   *
   * @return - the data stored in the node
   */
  public Type getData() {
    return this.data;
  }

  /**
   * A setter method that sets the value of the cameFrom to the node that was previously visited
   *
   * @param previousNode - the node that was previously visited before the current node
   */
  public void setCameFrom(Node<Type> previousNode) {
    this.cameFrom = previousNode;
  }

  /**
   * A getter method that returns the node that was previously visited before the current node
   *
   * @return - the node that was previously visited before the current node
   */
  public Node<Type> getCameFrom() {
    return this.cameFrom;
  }

  /** A setter method that states that a node has been visited in the graph */
  public void setHasBeenVisited() {
    this.hasBeenVisited = true;
  }

  /**
   * A getter method that returns whether or not a node has been visited
   *
   * @return - whether or not a node has been visited in the graph
   */
  public boolean getHasBeenVisited() {
    return this.hasBeenVisited;
  }

  /**
   * A getter method that returns a list of all of the neighbor nodes of the current node
   *
   * @return - list of all the neighbor nodes for the current node
   */
  public List<Node<Type>> getNeighborNodes() {
    return this.neighborNodes;
  }

  /**
   * This method adds Nodes to the Neighbor collection
   *
   * @param Node<Type> - Node to be added to the collection of neighbors
   */
  public void addNeighbor(Node<Type> addNeighborNode) {
    neighborNodes.add(addNeighborNode);
  }

  /**
   * This method sets the data for the given Node
   *
   * @param Type - variable to be set for node's data
   */
  public void setData(Type input) {
    this.data = input;
  }
}
