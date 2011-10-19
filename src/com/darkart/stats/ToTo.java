package com.darkart.stats;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ToTo {
	private static final Random rnd1 = new Random(Calendar.getInstance().getTimeInMillis());
	private static final Random rnd2 = new Random(Calendar.getInstance().getTimeInMillis()  * rnd1.hashCode());
	private static final int[] pool = new int[45];
	
	public static final int RUN = 10000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 45; i++) {
			pool[i] = i + 1;
		}
		
		int[] result = draw(7, pool, rnd1);
		
		int win = 0;
		for (int i = 0; i < RUN; i++) {
			for (int j = 0; j < 7; j++) {
				if (match(draw(6, pool, rnd2), result, 4)) {
					win++;
					break;
				}
			}
//			int[] pick = draw(8, pool, rnd2);
//			if (match(pick, result, 4)) {
//				win++;
//			}
		}
		System.out.println((double)100 * win/RUN);
	}

	private static boolean match(int[] pick, int[] result, int m) {
		int matches = 0;
		for (int pi = 0, ri = 0; pi < pick.length && ri < result.length; ) {
			if (pick[pi] < result[ri]) {
				pi++;
			} else if (pick[pi] > result[ri]) {
				ri++;
			} else {
				pi++;
				ri++;
				matches++;
			}
		}
		
		return matches >= m;
	}

	private static int[] draw(int n, int[] pool, Random rnd) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < pool.length; i++) {
			list.add(i);
		}
		
		int[] result = new int[n];
		for (int i = 0;i < n; i++) {
			result[i] = list.remove(rnd.nextInt(list.size()));
		}
		
		return result;
	}

}
