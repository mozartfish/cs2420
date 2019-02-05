package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This file is used for timing the array based and linked list based stack.
 *
 * @author Ryan and Hank Gansert u1078722
 * @version 3/2/18
 */
public class TimingExperiment {

  private static final int ITER_COUNT = 1000;

  public static void main(String[] args) {
    // you spin me round baby, right round
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000) ;

    try (FileWriter fw =
        new FileWriter(
            new File(
                "contains_experiment.tsv"))) { // open up a file writer so we can write to file.
      // Random random = new Random();
      for (int exp = 0;
          exp <= 1;
          exp++) { // This is used as the exponent to calculate the size of the set.
        int size = 1; // (int) Math.pow(2, exp);

        // Do the experiment multiple times, and average out the results
        long totalTime = 0;

        /* Initialize the Stuff here */

        for (int iter = 0; iter < ITER_COUNT; iter++) {
          // SET UP!

          // End of Set up

          /* Stuff to use for timing */
          //PathFinder.solveMaze("classic.txt", "classicOutput.txt");
          //PathFinder.solveMaze("randomMaze.txt", "randomMazeOutput.txt");
          //PathFinder.solveMaze("longStraight.txt", "longStraightOutput.txt");
          //PathFinder.solveMaze("straight.txt", "straightOutput.txt");
          //PathFinder.solveMaze("mediumMaze.txt", "mediumMazeOutput.txt");

          // TIME IT!
          long start = System.nanoTime();
          //PathFinder.solveMaze("randomMaze.txt", "randomMazeOutput.txt");
          long stop = System.nanoTime();
          totalTime += stop - start;
        }
        // BST.removeAll(setUpList);

        // If we divide by size we get the average for each add
        double averageTime = totalTime / (double) ITER_COUNT;
        System.out.println(size + "\t" + averageTime); // print to console
        fw.write(size + "\t" + averageTime + "\n"); // write to file.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    Charter charter = new Charter();
    charter.createChart(new File("contains_experiment.tsv"), new File("chart.png"));
  }
}
