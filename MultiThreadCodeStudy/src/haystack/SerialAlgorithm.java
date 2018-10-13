package haystack;

import java.util.Random;

public class SerialAlgorithm {
	
	public static int HAYSTACK_SIZE = 100000000;
	public static double NEEDLE_VALUE = 2.0;

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
		new SerialAlgorithm(haystack);
	}
	
	public SerialAlgorithm(double[] haystack) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < haystack.length; i++) {
      if (haystack[i] == NEEDLE_VALUE) {
      	long stop = System.currentTimeMillis();
      	System.out.println(
      		"Needle found at index " + i + 
      		" in " + (stop - start) + "ms"
      	);
      	System.exit(0);
      }
    }
		// Should not happen:
		System.out.println("Failed to find needle!");
	}
}
