package com.darkart.stats;

import java.util.Calendar;
import java.util.Random;

public class TreasureBox {
	public static final Random rndGen = new Random(Calendar.getInstance().getTimeInMillis());
	public static final Random rndPick = new Random(999);
	
	public static boolean pick() {
		boolean[] boxes = new boolean[3];
		
		//create a game
		int t = rndGen.nextInt(3);
		boxes[t] = true;
		
		//pick
		int pick = rndPick.nextInt(3);
		
		//disclose one fake
		int disclose = 0;
		for (int i = 0; i < 3; i++) {
			if (i != pick && !boxes[i]) {
				disclose = i;
				break;
			}
		}
		
		//re-select? 0.5
		int ns = rndPick.nextInt(3);
		while (ns == disclose)
			ns = rndPick.nextInt(3);
		
		//change pick? 0.66!!
		int change = 0;
		for (int i = 0; i < 3; i++) {
			if (i != pick && i != disclose) {
				change = i;
				break;
			}
		}
				
		return boxes[change];
	}
	
	public static void main(String[] args) {
		int total = 10000000;
		int success = 0;
		for (int i = 0; i < total; i++) {
			if (pick()) {
				success++;
			}
		}
		System.out.println("Success Rate: " + (double)success/total);
	}
}
