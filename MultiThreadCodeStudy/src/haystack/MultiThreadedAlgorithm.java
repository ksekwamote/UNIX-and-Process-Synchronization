package haystack;

import java.util.Random;

public class MultiThreadedAlgorithm {
	
	public static int HAYSTACK_SIZE = 100000000;
	public static double NEEDLE_VALUE = 2.0;
	
	public static int NUM_THREADS = 4;

	public static void main(String[] args) {
		Random rng = new Random();
		double[] haystack = new double[HAYSTACK_SIZE];
		// Initialise the values:
		for (int i = 0; i < haystack.length; i++) {
	    haystack[i] = rng.nextDouble();
    }
		// Insert the 'needle':
		int index = rng.nextInt(HAYSTACK_SIZE);
		haystack[index] = NEEDLE_VALUE;
		// Run the algorithm:
		new MultiThreadedAlgorithm(haystack);
	}
	
	public MultiThreadedAlgorithm(double[] haystack) {
		Thread t = null;
		for (int i = 0; i < NUM_THREADS; i++) {
		  int from = i * (HAYSTACK_SIZE / NUM_THREADS);
		  int to = from + (HAYSTACK_SIZE / NUM_THREADS);
	    t = new HaystackThread(haystack, from, to, i);
	    t.start();
    }
		// Wait for last thread:
		try {
	    t.join();
    } catch (InterruptedException e) {
	    e.printStackTrace();
    }
	}
	
	class HaystackThread extends Thread {
		
		HaystackThread(double[] haystack, int from, int to, int threadNum) {
			this.haystack = haystack;
			this.from = from;
			this.to = to;
			this.threadNum = threadNum;
		}

		public void run() {
			long start = System.currentTimeMillis();
			for (int i = from; i < to; i++) {
				if (haystack[i] == NEEDLE_VALUE) {
					long stop = System.currentTimeMillis();
	      	System.out.println(
	      			"Needle found by thread #" + threadNum + 
	      			" at index " + i + 
	      			" in " + (stop - start) + "ms"
	        );
	      	System.exit(0);
	      }
      }
		}

		private double[] haystack = null;
		private int from;
		private int to;
		private int threadNum; 
	}
}
