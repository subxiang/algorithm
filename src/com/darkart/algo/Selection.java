package com.darkart.algo;

public class Selection {
	public static int min(int[] input) {
		int min = input[0];
		for (int i = 1; i < input.length; i++) {
			if (input[i] < min) {
				min = input[i];
			}
		}

		return min;
	}
	
	public static int max(int[] input) {
		int max = input[0];
		for (int i = 1; i < input.length; i++) {
			if (input[i] > max) {
				max = input[i];
			}
		}
		return max;
	}
	
	public static int[] minMax(int[] input) {
		int min, max, localMin, localMax;
		
		boolean odd = input.length % 2 == 1;
		
		min = input[0];
		max = input[odd ? 0 : 1];
		
		if (min > max) {
			int tmp = max;
			max = min;
			min = tmp;
		}
			
		for (int i = odd ? 1 : 2; i < input.length; i += 2) {
			if (input[i] < input[i+1]) {
				localMin = input[i];
				localMax = input[i+1];
			} else {
				localMin = input[i+1];
				localMax = input[i];
			}
			if (localMin < min) min = localMin;
			if (localMax > max) max = localMax;
		}
		
		return new int[] {min, max};
	}
	
	public static int selectQuick(int[] input, int order) {
		return selectQuick(input, 0, input.length - 1, order);
	}

	private static int selectQuick(int[] input, int start, int end, int order) {
		int i = Sorting.partitionQuick(input, start, end);
		int k = i - start + 1;
		if (k == order) {
			return input[i];
		} else if (k > order) {
			return selectQuick(input, start, i - 1, order);
		} else {
			return selectQuick(input, i + 1, end, order - k);
		}
	}
}
