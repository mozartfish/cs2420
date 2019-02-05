package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



/**
 * This program compares times for the insertion sort of a basic array and Java's sort for an
 * ArrayList.
 * 
 * Which is more important, the big-O difference in the sorting algorithms or the difference in time
 * required to access elements of a basic array vs. calling the methods of ArrayList?
 * 
 * @author Erin Parker
 * @version February 8, 2018
 */
public class SortTimer {

  // All of these values are purposely small to keep the lecture demo quick.
  // Values for your own timing experiments should be larger.
  private final static int ITER = 100;
  private final static int NSTART = 10000;
  private final static int NSTOP = 100000;
  private final static int NINCR = 10000;

  public static void main(String[] args) {
    // double[] basicArrayTimes = timeBasicArray();
    double[] arrayListTimes = timeArrayList();

    for (int i = 0, N = NSTART; i < arrayListTimes.length; i++, N += NINCR)
      System.out.println(N + "\t" + arrayListTimes[i]);
  }

  // private static double[] timeBasicArray() {
  // Random rng = new Random(12345);
  //
  // double[] times = new double[(NSTOP - NSTART) / NINCR + 1];
  // int count = 0;
  //
  // for(int N = NSTART; N <= NSTOP; N += NINCR) {
  //
  // // Build an array of random integers
  // // (Notice how it will be randomly ordered and may contain duplicates)
  // Integer[] arr = new Integer[N];
  // for(int i = 0; i < N; i++)
  // arr[i] = rng.nextInt(N);
  //
  // // Let things stabilize
  // long startTime = System.nanoTime();
  // while(System.nanoTime() - startTime < 1000000000)
  // ;
  //
  // // Time the sort
  // startTime = System.nanoTime();
  // for(int i = 0; i < ITER; i++) {
  // // Make sure you aren't sorting an already sorted array!
  // Integer[] temp = Arrays.copyOf(arr, arr.length);
  // // AnagramUtil.insertionSort(temp, (i1, i2) -> i1 - i2);
  // }
  //
  // long midTime = System.nanoTime();
  //
  // // Time the "overhead"
  // for(int i = 0; i < ITER; i++) {
  // Integer[] temp = Arrays.copyOf(arr, arr.length); // unused variable noted
  // }
  //
  // long endTime = System.nanoTime();
  //
  // double averageTime = ((midTime - startTime) - (endTime - midTime)) / (double) ITER;
  // times[count++] = averageTime;
  // }
  // return times;
  // }

  private static double[] timeArrayList() {
    Random rng = new Random(12345);

    double[] times = new double[(NSTOP - NSTART) / NINCR + 1];
    int count = 0;

    try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) {
      for (int N = NSTART; N <= NSTOP; N += NINCR) {

        // Build an ArrayList of random integers
        // By using a random number generated seeded the same as for the basic array
        // experiment, we get the same random values to the sorted for the ArrayList
        ArrayList<Integer> list = new ArrayList<Integer>();
        list = SortUtil.generateDescending(N);
        for (int i = N; i > 0; i--)
          list.add(rng.nextInt(N));

        // Let things stabilize
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000);

        // Time the sort
        startTime = System.nanoTime();
        for (int i = 0; i < ITER; i++) {
          // Make sure you aren't sorting an already sorted array!
          @SuppressWarnings("unchecked")
          ArrayList<Integer> temp = (ArrayList<Integer>) list.clone(); // unchecked cast noted
          SortUtil.mergesort(temp, (i1, i2) -> i1 - i2);
        }

        long midTime = System.nanoTime();

        // Time the "overhead"
        for (int i = 0; i < ITER; i++) {
          @SuppressWarnings("unchecked")
          ArrayList<Integer> temp = (ArrayList<Integer>) list.clone(); // unchecked cast noted
        }

        long endTime = System.nanoTime();

        double averageTime = ((midTime - startTime) - (endTime - midTime)) / (double) ITER;
        fw.write(N + "\t" + averageTime / 1E9 + "\n");
        times[count++] = averageTime;
      }

    } catch (IOException e) {
    }

    Charter charter = new Charter();
    charter.createChart(new File("contains_experiment.tsv"), new File("chart.png"));

    return times;
  }
}
