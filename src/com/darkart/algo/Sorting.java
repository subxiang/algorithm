package com.darkart.algo;


public class Sorting {
	
	/**
	 * bubble sort (or selection sort). always O(n^2)
	 * @param input
	 */
	public static void bubbleSort(int[] input) {
		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				if (input[j] < input[i]) {
					int tmp = input[i];
					input[i] = input[j];
					input[j] = tmp;
				}
			}
		}
	}
	
	/**
	 * insertion sort. worst case O(n^2). best case O(n);
	 * @param input
	 */
	public static void insertionSort(int[] input) {
		for (int i = 1; i < input.length; i++) {
			for (int j = i; j >= 1 && input[j] < input[j - 1]; j--) {
				int tmp = input[j];
				input[j] = input[j - 1];
				input[j - 1] = tmp;
			}
		}
	}
	
	/**
	 * merge sort. O(nlgn)
	 * @param input
	 */
	public static void mergeSort(int[] input) {
		int[] output = mergeSort(input, 0, input.length);
		System.arraycopy(output, 0, input, 0, output.length);
	}
	
	private static int[] mergeSort(int[] input, int i, int len) {
		if (len >= 2) {
			int d = len / 2;
			int[] left = mergeSort(input, i, d);
			int[] right = mergeSort(input, i + d, len - d);
			return merge(left, right);
		} else {
			return new int[] {input[i]};
		}
	}

	private static int[] merge(int[] left, int[] right) {
		int[] output = new int[left.length + right.length];
		int lIndex = 0, rIndex = 0, i = 0;
		for (; i < output.length && lIndex < left.length && rIndex < right.length; i++) {
			output[i] = left[lIndex] < right[rIndex] ? left[lIndex++] : right[rIndex++];
		}
		
		int length = left.length - lIndex;
		System.arraycopy(left, lIndex, output, i, length);
		i += length;
		length = right.length - rIndex;
		System.arraycopy(right, rIndex, output, i, length);
		
		return output;
	}

	/**
	 * heap sort. O(nlgn)
	 * this does not match the design of heap, which requires index starts at 1
	 * but we can imaging the root (index 0) has itself as the left child.
	 * @param input
	 */
	public static void heapSort(int[] input) {
		for (int i = (input.length - 1) / 2; i >= 0; i--) {
			maxHeapify(input, i, input.length);
		}
		
		for (int i = input.length - 1; i >= 1; i--) {
			int tmp = input[i];
			input[i] = input[0];
			input[0] = tmp;
			maxHeapify(input, 0, i);
		}
	}

	private static void maxHeapify(int[] input, int i, int length) {
		int lIndex = i * 2;
		int rIndex = lIndex + 1;
		
		int largest = i;
		if (lIndex < length && input[lIndex] > input[i]) {
			largest = lIndex;
		}
		if (rIndex < length && input[rIndex] > input[largest]) {
			largest = rIndex;
		}
		if (largest != i) {
			int tmp = input[i];
			input[i] = input[largest];
			input[largest] = tmp;
			maxHeapify(input, largest, length);
		}
	}
	
	/**
	 * a correct implementation of heap sort requires index at 1.
	 * @param input
	 */
	public static void heapSortCorr(int[] input) {
		int length = input.length;
		int[] sorted = new int[length + 1];
		System.arraycopy(input, 0, sorted, 1, length);
		
		for (int i = length / 2; i >= 1; i--) {
			maxHeapifyCorr(sorted, i, length);
		}
		
		for (int i = length; i >= 2; i--) {
			int tmp = sorted[i];
			sorted[i] = sorted[1];
			sorted[1] = tmp;
			maxHeapifyCorr(sorted, 1, i - 1);
		}
		
		System.arraycopy(sorted, 1, input, 0, length);
	}
	
	private static void maxHeapifyCorr(int[] input, int i, int length) {
		int lIndex = i * 2;
		int rIndex = lIndex + 1;
		
		int largest = i;
		if (lIndex <= length && input[lIndex] > input[i]) {
			largest = lIndex;
		}
		if (rIndex <= length && input[rIndex] > input[largest]) {
			largest = rIndex;
		}
		if (largest != i) {
			int tmp = input[i];
			input[i] = input[largest];
			input[largest] = tmp;
			maxHeapifyCorr(input, largest, length);
		}
	}
}
