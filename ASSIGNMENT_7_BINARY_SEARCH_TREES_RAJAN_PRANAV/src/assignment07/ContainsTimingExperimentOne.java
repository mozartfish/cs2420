package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This file is used for timing the array based and linked list based stack.
 *
 * @author Ryan and Hank Gansert u1078722
 * @version 3/2/18
 */
public class ContainsTimingExperimentOne {

  private static final int ITER_COUNT = 200;

  public static void main(String[] args) {
    // you spin me round baby, right round
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000) ;

    try (FileWriter fw =
        new FileWriter(
            new File(
                "contains_experiment.tsv"))) { // open up a file writer so we can write to file.
      // Random random = new Random();
      for (int exp = 1000;
          exp <= 10000;
          exp += 100) { // This is used as the exponent to calculate the size of the set.
        int size = exp; // (int) Math.pow(2, exp);

        // Do the experiment multiple times, and average out the results
        long totalTime = 0;

        /* Initialize the Stuff here */
        ArrayList<Integer> setUpList = new ArrayList<Integer>();
        BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();

        for (int iter = 0; iter < ITER_COUNT; iter++) {
          // SET UP!
          for (int i = 1; i <= size; i++) {
            setUpList.add(i);
          }
          Collections.shuffle(setUpList);

          BST.addAll(setUpList);
          // End of Set up

          /* Stuff to use for timing */

          // TIME IT!
          for (int i = 1; i <= size; i++) {
            long start = System.nanoTime();
            BST.remove(i);
            long stop = System.nanoTime();
            totalTime += stop - start;
          }
          BST.clear();
        }
        // If we divide by size we get the average for each add
        double averageTime = (totalTime / size) / (double) ITER_COUNT;
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
