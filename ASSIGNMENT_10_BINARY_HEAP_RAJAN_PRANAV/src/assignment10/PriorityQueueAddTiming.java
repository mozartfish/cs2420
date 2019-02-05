package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is an example of running an experiment on the contains method of a collection 
 * @author ryans and Pranav Rajan
 */
public class PriorityQueueAddTiming {

	private static final int ITER_COUNT = 200;

	public static void main(String[] args) {
		 // you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { //open up a file writer so we can write to file.
			for(int exp = 10; exp <= 25; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); 
				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				
				//create new priority queue
				PriorityQueue<Integer>intQueue = new PriorityQueue<Integer>();
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
				
				  //for deleteMin timing - add the elements here to account for the size changing
//					for(int i = 0; i < size; i++) {
//						intQueue.add(i);
//					}
					
					for (int i = 0; i < size; i++) {
					// TIME IT!
					  
					  //put add outside for the findMin to account for changing the size
//					    intQueue.add(i);
	                    long start = System.nanoTime();
	                    //put stuff here
//	                    intQueue.deleteMin();
//	                    intQueue.findMin();
//	                    intQueue.add(i);
	                    long stop = System.nanoTime();
	                    totalTime += stop - start;
					}
					intQueue.clear();
					
				}
				double averageTime = (totalTime / (double)ITER_COUNT) / (double) size;
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
