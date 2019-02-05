package assignment03;

public class TimingAdd {

    public static void main(String[] args) {
      long startTime, midpointTime, stopTime;

      // First, spin computing stuff until one second has gone by.
      // This allows this thread to stabilize.

      startTime = System.nanoTime();
      while (System.nanoTime() - startTime < 1000000000) { // empty block
      }

      // Now, run the test.

      long timesToLoop = 1000000000;
      BinarySearchSet<Integer>s = new BinarySearchSet<Integer>();
      

      startTime = System.nanoTime();

      for (long i = 0; i < timesToLoop; i++)
        for (int j = 0; j <2000000; j++ )
        {
          s.add(j);
        }

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
          + " nanoseconds to compute the number of items "
          );
    }
  }


