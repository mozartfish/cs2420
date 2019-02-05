package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * This is an example of running an experiment on the contains method of a collection
 *
 * @author ryans and Pranav Rajan
 */
public class EfficiencyTest {

  private static final int ITER_COUNT = 100;

  public static void main(String[] args) {
    // you spin me round baby, right round
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000) ;

    try (FileWriter fw =
        new FileWriter(
            new File("contains_experiment.tsv"))) { //open up a file writer so we can write to file.
     
      for (int exp = 10;
          exp <= 25;
          exp++) { // This is used as the exponent to calculate the size of the set.
        int size = (int) Math.pow(2, exp);

        // Do the experiment multiple times, and average out the results
        long totalTime = 0;

        //The Good Hash Functor
        GoodHashFunctor goodHash = new GoodHashFunctor();

        //The Quadratic Probing Hash Table
        QuadProbeHashTable quadProbeHashTable = new QuadProbeHashTable(1001, goodHash);
        //
        //                //The Separate Chaining Hash Table
        //                ChainingHashTable chainHashTable = new ChainingHashTable(1001, goodHash);

        //An ArrayList that stores a bunch of randomly generated strings
        ArrayList<String> randomString = new ArrayList<String>();
        for (int iter = 0; iter < ITER_COUNT; iter++) {
          // SET UP!
          for (int i = 1; i <= size; i++) {
            String s = generateString(5);
            randomString.add(s);
          }
          quadProbeHashTable.addAll(randomString);

          //End Set Up

          // TIME IT!
          for (int i = 1; i <= size; i++) {
            long start = System.nanoTime();
            quadProbeHashTable.contains(randomString.get(i));
            long stop = System.nanoTime();
            totalTime += stop - start;
          }
          quadProbeHashTable.clear();
        }
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

  private static String generateString(int length) {
    final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    StringBuilder sb = new StringBuilder(length);

    for (int j = 0; j < length; j++) {
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    }
    return sb.toString();
  }
}
