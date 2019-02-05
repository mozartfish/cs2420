package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class is the main class that uses the graph and node classes to find the shortest path
 * @author Pranav Rajan and Hank Gensert
 * @version March 15, 2018
 *
 */
public class PathFinder {

  public static void solveMaze(String file, String outputFile) throws IOException {

    //variable that keeps track of the number of rows for the pacman maze
    int rowSize = 0;
    
    //variable that keeps track of the number of columns for the pacman maze
    int columnSize = 0;
    
    //variable that stores the nodes created from the file as a 2-d array
    Node<Character>[][] arrayOfNodes = null;

    try (Scanner scn = new Scanner(new File(file))) {
      
      //row number refers to each line in the file
      int lineNumber = 1;
      
      while (scn.hasNextLine()) {

        //Read the first line to determine the dimensions of the maze
        if (lineNumber == 1) {

          //set the number of rows and columns for the array of nodes to the values on the first line of the text file
          rowSize = scn.nextInt();
          columnSize = scn.nextInt();

          //create a new 2-d array of nodes to store all the nodes in the text file
          arrayOfNodes = new Node[rowSize][columnSize];

          //call nextLine() to finish reading the first line where the dimensions were found
          //after reading the integers there is nothing left in the line so calling
          //nextLine returns everything else in the first line before moving the scanner to the second
          //line in the file
          scn.nextLine();

        } else {
          String currentLine = scn.nextLine();
          char[] charactersInLine = currentLine.toCharArray();

          //create the nodes for each line in the graph
          for (int columnNumber = 0; columnNumber < charactersInLine.length; columnNumber++) {
            Node<Character> temp =
                new Node<Character>(charactersInLine[columnNumber], lineNumber - 2, columnNumber);

            //since we started the line number at 1, we have to decrement by one to create the nodes correctly
            arrayOfNodes[lineNumber - 2][columnNumber] = temp;
          }
        }
        lineNumber++;
      }
      
      //Create a new graph object
      Graph graphOfCharNodes = new Graph();

      //Fill the graph with nodes based on the array of nodes created above
      for (int row = 0; row < rowSize; row++) {
        for (int column = 0; column < columnSize; column++) {
          graphOfCharNodes.add(arrayOfNodes[row][column]);

          //check to see if we are at a wall
          if (arrayOfNodes[row][column].getData() != 'X') {

            //look at the node above in the same column
            //if the node above has data that is not an X
            //add that node to the current node's list of neighbor nodes
            if (arrayOfNodes[row - 1][column].getData() != 'X') {
              arrayOfNodes[row][column].addNeighbor(arrayOfNodes[row - 1][column]);
            }

            //look at the node below in the same column
            //if the node below has data that is not an X
            //add that node to the current node's list of neighbor nodes
            if (arrayOfNodes[row + 1][column].getData() != 'X') {
              arrayOfNodes[row][column].addNeighbor(arrayOfNodes[row + 1][column]);
            }

            //look at the node to the left of the current node
            //if the node to the left has data that is not an X
            //add that node to the current node's list of neighbor nodes
            if (arrayOfNodes[row][column - 1].getData() != 'X') {
              arrayOfNodes[row][column].addNeighbor(arrayOfNodes[row][column - 1]);
            }

            //look at the node to the right of the current node
            //if the node to the right has data that is not an X
            //add that node to the current node's list of neighbor nodes
            if (arrayOfNodes[row][column + 1].getData() != 'X') {
              arrayOfNodes[row][column].addNeighbor(arrayOfNodes[row][column + 1]);
            }
          }
        }
      }

      //After the graph has been set up call the breadth first search algorithm
      graphOfCharNodes.BFS();

      //Construct the path from the goal node to the start node
      Node<Character> path = graphOfCharNodes.getNode('G');
      path = path.getCameFrom();
      if (path != null) {
        while (path.getData() != 'S') {
          path.setData('.');
          path = path.getCameFrom();
        }
      }

      //Draw the path
      String result = rowSize + " " + columnSize + "\n";
      for (int row = 0; row < rowSize; row++) {
        for (int column = 0; column < columnSize; column++) {
          result += arrayOfNodes[row][column].getData();
        }
        result += "\n";
      }

      //Write the path to a file
      try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
        output.print(result);
      }
    }
  }
}
