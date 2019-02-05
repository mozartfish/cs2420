package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;


public class TimingGetLargestAnagramGroup {

  private static long timesToLoop;
  private static int size = 100;

  public static void main(String[] args) {



    try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) {
      timesToLoop = 30;
      for (int z = 1; z < 10; z++) {
        size = size + 50;
        timesToLoop = timesToLoop - 2;



        long startTime, midpointTime, stopTime;

        startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000);



        startTime = System.nanoTime();

        for (int i = 0; i < timesToLoop; i++) {
          AnagramUtil.getLargestAnagramGroup(generateStringArray(size));
        }

        midpointTime = System.nanoTime();

        // Run an empty loop to capture the cost of running the loop and the generateStringArray
        // method.

        for (long i = 0; i < timesToLoop; i++) {
          generateStringArray(size);
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

  private static String[] generateStringArray(int length) {
    final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();

    String[] wordList = new String[length];
    for (int i = 0; i < length; i++) {
      int wordLength = (int) (Math.random() * 15 + 5);
      StringBuilder sb = new StringBuilder(wordLength);
      for (int j = 0; j < wordLength; j++) {

        sb.append(AB.charAt(rnd.nextInt(AB.length())));
      }
      wordList[i] = sb.toString();

    }
    return wordList;
  }
}
