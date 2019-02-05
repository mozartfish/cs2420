package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;


public class TimingAreAnagrams {

  // how many times you call the method
  private static long timesToLoop;
  
  // N[number of characters]
  private static int size = 1000;

  public static void main(String[] args) {



    try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) {
      timesToLoop = 42;
      
      //the number of trials
      for (int z = 1; z < 15; z++) {
        size = size + 500;
        
        timesToLoop = timesToLoop - 2;



        long startTime, midpointTime, stopTime;

        startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000);



        startTime = System.nanoTime();

        for (int i = 0; i < timesToLoop; i++) {
          AnagramUtil.areAnagrams(generateString(size), generateString(size));
        }

        midpointTime = System.nanoTime();

        // Run an empty loop to capture the cost of running the loop and the generateString
        // method.

        for (long i = 0; i < timesToLoop; i++) {
          generateString(size);
          generateString(size);
        }

        stopTime = System.nanoTime();

        double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
        fw.write(size + "\t" + averageTime / 1E9 + "\n");

        System.out.println(size + "    " + averageTime / 1E9);
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
