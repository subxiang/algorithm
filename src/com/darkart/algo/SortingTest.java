package com.darkart.algo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import com.darkart.algo.ds.BinaryTree;
import com.darkart.algo.ds.Node;
import com.darkart.algo.ds.RedBlackNode;
import com.darkart.algo.ds.RedBlackTree;
import com.darkart.algo.ds.Visitor;

public class SortingTest extends TestCase {
	private static final int INPUT_LENGTH = 1000000;
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
//		Arrays.sort(input);
//		for (int i = 0; i < input.length / 2; i++) {
//			int temp = input[i];
//			input[i] = input[input.length - 1 - i];
//			input[input.length -  1 -i] = temp;
//		}
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
		Sorting.radixSort(input);
//		Sorting.quickSortHoare(input);
//		Sorting.quickSort(input);
//		Arrays.sort(input);
//		Sorting.mergeSortQ(input);
//		Sorting.mergeSort(input);
//		Sorting.heapSort(input);
//		Sorting.insertionSort(input);
//		Sorting.bubbleSort(input);
		
//		Sorting.countingSort(input);
//		Sorting.bucketSort(input);
	}
}
