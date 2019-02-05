package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is an example of running an experiment on the contains method of a collection 
 * @author ryans
 */
public class Timing {

    private static final int ITER_COUNT = 2000;

    public static void main(String[] args) {
         // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);
        
        try(FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { //open up a file writer so we can write to file.

            for(int exp = 10; exp <= 25; exp++) { // This is used as the exponent to calculate the size of the set.
                int size = (int) Math.pow(2, exp); 
                
                // Do the experiment multiple times, and average out the results
                long totalTime = 0;
                
                StackLinkedList<Integer> stackList = new StackLinkedList<Integer>();
//                StackArray<Integer>arrayStack = new StackArray<Integer>();
//                
                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // SET UP!
                  for (int i = 0; i < size; i++)
                  {
                    stackList.push(i);
//                    arrayStack.push(i);
                  }
                    
                    // TIME IT!
                    long start = System.nanoTime();
//                    stackList.push(1);
//                    arrayStack.push(1);
//                    
                    stackList.pop();
//                    arrayStack.pop();
//                    
//                    stackList.peek();
//                    arrayStack.peek();
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }
                double averageTime = totalTime / (double)ITER_COUNT;
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

