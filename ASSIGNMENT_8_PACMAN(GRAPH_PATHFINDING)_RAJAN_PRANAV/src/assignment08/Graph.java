package assignment08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This class represents a graph for the pacman game. It uses the node class to create nodes and stores an array of nodes
 * to represent a graph.
 * @author Pranav Rajan and Hank Gensert
 * @version March 15, 2018
 *
 */
public class Graph {

  /**Stores the nodes for the in the graph*/
  private ArrayList<Node<Character>> nodes;

  public Graph() {
    nodes = new ArrayList<Node<Character>>();
  }

  /**
   * Adds a node to the graph
   * @param node - the node the the user would like to add to the graph
   */
  public void add(Node<Character> node) {
    nodes.add(node);
  }

  /**
   * Helper method that returns the node containing the data the user wants
   * @param data - the specific character the user is looking for
   * @return - the node containing the data
   */
  public Node<Character> getNode(char data) {
    for (int i = 0; i < nodes.size(); i++) {
      if (nodes.get(i).getData() == data) {
        return nodes.get(i);
      }
    }
    throw new NoSuchElementException();
  }

  /** This is the driver method for the Breadth First Search method */
  public void BFS() {
    Node<Character> start = this.getNode('S');
    Node<Character> goal = this.getNode('G');
    BFS(start, goal);
  }

  /**
   * The method that does the Breadth First Search
   *
   * @param start - the starting node in the graph
   * @param goal - the node that we want to stop at
   */
  private void BFS(Node<Character> start, Node<Character> goal) {
    
    //Create a new queue backed by a linkedlist in order to find the path
    Queue<Node<Character>> bsfQueue = new LinkedList<Node<Character>>();
    
    //add the first node to the queue
    bsfQueue.add(start);
    
    //mark the first node as visited
    start.setHasBeenVisited();
    
    //while the queue is not empty remove the nodes in the queue
    while (!bsfQueue.isEmpty()) {
      Node<Character> curr = bsfQueue.remove();

      // If the current node that we are looking is the node that we want to end at
      // then return
      if (curr == goal) {
        return;
      }

      // While there is another node in the current node's list of adjacent nodes
      for (Node<Character> next : curr.getNeighborNodes()) {

        // if a node has not been visited then mark it as visited and add it to the
        // queue
        if (!next.getHasBeenVisited()) {
          next.setHasBeenVisited();
          next.setCameFrom(curr);
          bsfQueue.add(next);
        }
      }
    }
  }
}
