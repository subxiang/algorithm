package com.darkart.algo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

public class SortingTest extends TestCase {
	private static final int INPUT_LENGTH = 10000000;
	private int[] input;
	private int[] ref;
	
	private long startTime;
	private long endTime;

	@Override
	protected void setUp() throws Exception {
		Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
		
		input = new int[INPUT_LENGTH];
		for (int i = 0; i < input.length; i++) {
//			input[i] = Math.abs(rnd.nextInt()) % 1000000;
			input[i] = Math.abs(rnd.nextInt());
//			input[i] = rnd.nextDouble();
		}
		
		ref = Arrays.copyOf(input, input.length);
		
		startTime = Calendar.getInstance().getTimeInMillis();
	}
	
	@Override
	protected void tearDown() throws Exception {
		endTime = Calendar.getInstance().getTimeInMillis();
		System.out.println("Total time: " + (endTime - startTime) + "ms.");
		
		Arrays.sort(ref);
		for (int i = 0; i < input.length; i++) {
			assertEquals(ref[i], input[i]);
		}
	}

	@Test
	public void testHeapsort() {
//		Arrays.sort(input);
//		Sorting.mergeSort(input);
//		Sorting.mergeSortQ(input);
//		Sorting.heapSort(input);
//		Sorting.quickSort(input);
//		Sorting.quickSortHoare(input);
//		Sorting.countingSort(input);
//		Sorting.radixSort(input);
//		Sorting.bucketSort(input);
	}

}
