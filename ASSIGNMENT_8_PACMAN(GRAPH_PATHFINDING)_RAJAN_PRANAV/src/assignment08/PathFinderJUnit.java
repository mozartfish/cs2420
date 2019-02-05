package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class PathFinderJUnit {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void demoMazeTest() throws IOException {
    PathFinder.solveMaze("demoMaze.txt", "demoMazeOutput.txt");
    int solutionXResult = getNumberofXCharacters("demoMazeSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("demoMazeSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("demoMazeSol.txt");
    int outputXResult = getNumberofXCharacters("demoMazeOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("demoMazeOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("demoMazeOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void bigMazeTest() throws IOException {
    PathFinder.solveMaze("bigMaze.txt", "bigMazeOutput.txt");
    int solutionXResult = getNumberofXCharacters("bigMazeSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("bigMazeSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("bigMazeSol.txt");
    int outputXResult = getNumberofXCharacters("bigMazeOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("bigMazeOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("bigMazeOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void classicMazeTest() throws IOException {
    PathFinder.solveMaze("classic.txt", "classicOutput.txt");
    int solutionXResult = getNumberofXCharacters("classicSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("classicSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("classicSol.txt");
    int outputXResult = getNumberofXCharacters("classicOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("classicOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("classicOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void mediumMazeTest() throws IOException {
    PathFinder.solveMaze("mediumMaze.txt", "mediumMazeOutput.txt");
    int solutionXResult = getNumberofXCharacters("mediumMazeSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("mediumMazeSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("mediumMazeSol.txt");
    int outputXResult = getNumberofXCharacters("mediumMazeOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("mediumMazeOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("mediumMazeOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void straightMazeTest() throws IOException {
    PathFinder.solveMaze("straight.txt", "straightOutput.txt");
    int solutionXResult = getNumberofXCharacters("straightSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("straightSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("straightSol.txt");
    int outputXResult = getNumberofXCharacters("straightOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("straightOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("straightOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void tinyMazeTest() throws IOException {
    PathFinder.solveMaze("tinyMaze.txt", "tinyMazeOutput.txt");
    int solutionXResult = getNumberofXCharacters("tinyMazeSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("tinyMazeSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("tinyMazeSol.txt");
    int outputXResult = getNumberofXCharacters("tinyMazeOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("tinyMazeOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("tinyMazeOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void tinyOpenMazeTest() throws IOException {
    PathFinder.solveMaze("tinyOpen.txt", "tinyOpenOutput.txt");
    int solutionXResult = getNumberofXCharacters("tinyOpenSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("tinyOpenSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("tinyOpenSol.txt");
    int outputXResult = getNumberofXCharacters("tinyOpenOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("tinyOpenOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("tinyOpenOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void turnMazeTest() throws IOException {
    PathFinder.solveMaze("turn.txt", "turnOutput.txt");
    int solutionXResult = getNumberofXCharacters("turnSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("turnSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("turnSol.txt");
    int outputXResult = getNumberofXCharacters("turnOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("turnOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("turnOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }
  /* This test needs to be fixed */
  @Test
  public void usolvableMazeTest() throws IOException {
    PathFinder.solveMaze("unsolvable.txt", "unsolvableOutput.txt");
    int solutionXResult = getNumberofXCharacters("unsolvableSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("unsolvableSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("unsolvableSol.txt");
    int outputXResult = getNumberofXCharacters("unsolvableOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("unsolvableOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("unsolvableOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  @Test
  public void randomMazeTest() throws IOException {
    PathFinder.solveMaze("randomMaze.txt", "randomMazeOutput.txt");
    int solutionXResult = getNumberofXCharacters("randomMazeSol.txt");
    int solutionSpaceResult = getNumberofSpaceCharacters("randomMazeSol.txt");
    int solutionPeriodResult = getNumberofPeriodCharacters("randomMazeSol.txt");
    int outputXResult = getNumberofXCharacters("randomMazeOutput.txt");
    int outputSpaceResult = getNumberofSpaceCharacters("randomMazeOutput.txt");
    int outputPeriodResult = getNumberofPeriodCharacters("randomMazeOutput.txt");
    assertEquals(solutionXResult, outputXResult);
    assertEquals(solutionSpaceResult, outputSpaceResult);
    assertEquals(solutionPeriodResult, outputPeriodResult);
  }

  /* Tests for helper functions */
  @Test
  public void testForGetNumberofXCharacters() throws FileNotFoundException {
    int solutionXResult = getNumberofXCharacters("tinyMazeSol.txt");
    assertEquals(38, solutionXResult);
  }

  @Test
  public void testForGetNumberofSpaceCharacters() throws FileNotFoundException {
    int solutionSpaceResult = getNumberofSpaceCharacters("tinyMazeSol.txt");
    assertEquals(16, solutionSpaceResult);
  }

  @Test
  public void testForGetNumberofPeriodCharacters() throws FileNotFoundException {
    int solutionPeriodResult = getNumberofPeriodCharacters("tinyMazeSol.txt");
    assertEquals(8, solutionPeriodResult);
  }

  /* Helper functions */
  public int getNumberofXCharacters(String inputFile) throws FileNotFoundException {
    try (Scanner fileIn = new Scanner(new File(inputFile))) {
      int numberOfXCharacters = 0;
      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();
        char[] charactersInLine = line.toCharArray();

        //create the nodes for each line in the graph
        for (int i = 0; i < charactersInLine.length; i++) {
          if (charactersInLine[i] == 'X') {
            numberOfXCharacters++;
          }
        }
      }
      return numberOfXCharacters;
    }
  }

  public int getNumberofSpaceCharacters(String inputFile) throws FileNotFoundException {
    try (Scanner fileIn = new Scanner(new File(inputFile))) {
      int numberOfSpaceCharacters = 0;
      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();
        char[] charactersInLine = line.toCharArray();

        //create the nodes for each line in the graph
        for (int i = 0; i < charactersInLine.length; i++) {
          if (charactersInLine[i] == ' ') {
            numberOfSpaceCharacters++;
          }
        }
      }
      return numberOfSpaceCharacters;
    }
  }

  public int getNumberofPeriodCharacters(String inputFile) throws FileNotFoundException {
    try (Scanner fileIn = new Scanner(new File(inputFile))) {
      int numberOfPeriodCharacters = 0;
      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();
        char[] charactersInLine = line.toCharArray();

        //create the nodes for each line in the graph
        for (int i = 0; i < charactersInLine.length; i++) {
          if (charactersInLine[i] == '.') {
            numberOfPeriodCharacters++;
          }
        }
      }
      return numberOfPeriodCharacters;
    }
  }
}
