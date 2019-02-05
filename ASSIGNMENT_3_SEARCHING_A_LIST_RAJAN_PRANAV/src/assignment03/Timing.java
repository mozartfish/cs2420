package assignment03;



public class Timing {

  public static void main(String[] args) {

    int initValue = 10000;
    int maxValue = 1000000000;
    int step = 10000;
    for (int n = initValue; n <= maxValue; n += step) {
      long startTime, midpointTime, stopTime;

      // First, spin computing stuff until one second has gone by.
      // This allows this thread to stabilize.

      startTime = System.nanoTime();
      while (System.nanoTime() - startTime < 1000000000) { // empty block
      }

      // Now, run the test.

      long timesToLoop = 1000000000;

      BinarySearchSet<Integer>s = new BinarySearchSet<Integer>();
      
      for (int i = 0; i < n; ++i) {
        s.add(i);
      }

      startTime = System.nanoTime();

      for (long i = 0; i < timesToLoop; i++) 
        s.contains(n / 2);

      midpointTime = System.nanoTime();

      // Run an empty loop to capture the cost of running the loop.

      for (long i = 0; i < timesToLoop; i++) { // empty block
      }

      stopTime = System.nanoTime();

      // Compute the time, subtract the cost of running the loop
      // from the cost of running the loop and computing square roots.
      // Average it over the number of runs.

      double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
          / timesToLoop;

      System.out.println("It takes exactly " + averageTime
          + " nanoseconds to compute the number of elements in a list of size " + n);
    }
  }
}
