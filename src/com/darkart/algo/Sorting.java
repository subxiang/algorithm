package com.darkart.algo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


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
			output[i] = left[lIndex] <= right[rIndex] ? left[lIndex++] : right[rIndex++];
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
	
	public static void quickSort(int[] input) {
		quickSort(input, 0, input.length - 1);
	}

	private static void quickSort(int[] input, int start, int end) {
		if (end <= start) {
			return;
		} else {
			int i = partitionQuick(input, start, end);
			quickSort(input, start, i - 1);
			quickSort(input, i + 1, end);
		}
	}

	public static int partitionQuick(int[] input, int start, int end) {
		int x = input[end];
		int i = start - 1;
		for (int j = start; j <= end - 1; j++) {
			if (input[j] <= x) {
				i++;
				int tmp = input[j];
				input[j] = input[i];
				input[i] = tmp;
			}
		}
		
		i++;
		input[end] = input[i];
		input[i] = x;
		
		return i;
	}
	
	public static void quickSortHoare(int[] input) {
		quickSortHoare(input, 0, input.length - 1);
	}
	
	public static void quickSortHoare(int[] input, int start, int end) {
		if (end <= start) {
			return;
		} else {
			int i = partitionHoare(input, start, end);
			quickSortHoare(input, start, i);
			quickSortHoare(input, i + 1, end);
		}
	}
	
	public static int partitionHoare(int[] input, int start, int end) {
		int i = start - 1;
		int j = end + 1;
		int x = input[start];
		while (true) {
			j--;
			while (input[j] > x) j--;
			i++;
			while (input[i] < x) i++;
			if (i < j) {
				int tmp = input[j];
				input[j] = input[i];
				input[i] = tmp;
			} else {
				return j;
			}
		}
	}
	
	public static void quickSortRandomized(int[] input) {
		quickSortRandomized(input, 0, input.length - 1);
	}
	
	public static void quickSortRandomized(int[] input, int start, int end) {
		if (end <= start) {
			return;
		} else {
			int i = partitionRandomized(input, start, end);
			quickSortRandomized(input, start, i - 1);
			quickSortRandomized(input, i + 1, end);
		}
	}

	public static final Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
	private static int partitionRandomized(int[] input, int start, int end) {
		int i = start + rnd.nextInt(end - start + 1);
		int tmp = input[end];
		input[end] = input[i];
		input[i] = tmp;
		return partitionQuick(input, start, end);
	}
	
	public static void stoogeSort(int[] input) {
		stoogeSort(input, 0, input.length - 1);
	}

	private static void stoogeSort(int[] input, int start, int end) {
		if (input[start] > input[end]) {
			int tmp = input[start];
			input[start] = input[end];
			input[end] = tmp;
		}
		if (start + 1 >= end)
			return;
		int k = (end - start + 1) / 3;
		stoogeSort(input, start, end - k);
		stoogeSort(input, start + k, end);
		stoogeSort(input, start, end - k);
	}
	
	public static void mergeSortQ(int[] input) {
		int[] sorted = new int[input.length];
		int L = input.length;
		int l = 1;
		int c = 0;
		while (l <= L) {
			int segments = (L + (l - 1)) / l;
			int lastSegIndex = segments - 1;
			
			for (int i = 0; i <= lastSegIndex / 2; i++) {
				int lStart = 2 * i * l;
				int rStart = (2 * i + 1) * l;
				int lLen = Math.min(L - lStart, l);
				int rLen = Math.min(L - rStart, l);
				if (rLen < 0) {
					rLen = 0;
				}
				merge(input, lStart, lLen, rStart, rLen, sorted);
			}
			
			l = 2*l;
			
			int[] tmp = input;
			input = sorted;
			sorted = tmp;
			c++;
		}
		if (c % 2 == 1) {
			System.arraycopy(input, 0, sorted, 0, L);
		}
	}

	private static void merge(int[] input, int lStart, int lLen, int rStart, int rLen, int[] sorted) {
		int lIndex = 0;
		int rIndex = 0;
		int total = lLen + rLen;
		int l, r;
		for (int i = 0; i < total; i++) {
			if (lIndex < lLen) {
				l = input[lStart + lIndex];
			} else {
				l = Integer.MAX_VALUE;
			}
			
			if (rIndex < rLen) {
				r = input[rStart + rIndex];
			} else {
				r = Integer.MAX_VALUE;
			}
			
			if (l <= r) {
				sorted[lStart + i] = l;
				lIndex++;
			} else {
				sorted[lStart + i] = r;
				rIndex++;
			}
		}
	}

	public static void countingSort(int[] input) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (input[i] > max) {
				max = input[i];
			}
		}
		
		int[] counter = new int[max + 1];
		for (int i = 0; i < input.length; i++) {
			counter[input[i]]++;
		}
		for (int i = 1; i < counter.length; i++) {
			counter[i] += counter[i - 1];
		}
		
		int[] copy = Arrays.copyOf(input, input.length);
		for (int i = copy.length - 1; i >= 0; i--) {
			input[counter[copy[i]]-- - 1] = copy[i];
		}
	}
	
	public static void radixSort(int[] input) {
		int r = 11; //31 - Integer.numberOfLeadingZeros(input.length); //floor(log2(x))
		
		int mask = (1 << r) - 1;
		
		for (int i = 0; i < (32 + (r - 1)) / r; i++) {
			int[] copy = Arrays.copyOf(input, input.length);
			int[] counter = new int[mask + 1];
			for (int j = 0; j < copy.length; j++) {
				counter[copy[j] >> (r * i) & mask]++;
			}
			for (int j = 1; j < counter.length; j++) {
				counter[j] += counter[j - 1];
			}
			for (int j = copy.length - 1; j >= 0; j--) {
				input[counter[copy[j] >> (r * i) & mask]-- - 1] = copy[j];
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void bucketSort(double[] input) {
		List[] buckets = new List[input.length];
		for (int i = 0; i < input.length; i++) {
			int index = (int) Math.floor(input[i] * input.length);
			if (buckets[index] == null) {
				buckets[index] = new LinkedList<Double>();
			}
			buckets[index].add(input[i]);
		}
		
		int p = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				double[] tmp = new double[buckets[i].size()];
				for (int d = 0; d < tmp.length; d++) {
					tmp[d] = (Double)buckets[i].get(d);
				}
				Arrays.sort(tmp);
				System.arraycopy(tmp, 0, input, p, tmp.length);
				p += tmp.length;
			}
		}
	}
}
