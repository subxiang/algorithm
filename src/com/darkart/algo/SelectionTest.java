package com.darkart.algo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectionTest {
	private static final Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
	private int size = 10000000;
	private int[] input = new int[size];
	private long start;
	private long end;
	
	@Before
	public void setUp() {
		for (int i = 0; i < input.length; i++) {
			input[i] = 1 + Math.abs(rnd.nextInt()) % 1000;
		}
		input[1] = 0;
		input[2] = 1001;
		start = Calendar.getInstance().getTimeInMillis();
	}
	
	@After
	public void tearDown() {
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("Time taken: " + (end - start) + "ms.");
	}

	@Test
	public void testMin() {
		assertEquals(0, Selection.min(input));
	}
	
	@Test
	public void testMax() {
		assertEquals(1001, Selection.max(input));
	}
	
	@Test
	public void testMinMax() {
		int[] mm = Selection.minMax(input);
		assertEquals(0, mm[0]);
		assertEquals(1001, mm[1]);
	}

	@Test
	public void testSelect() {
		assertEquals(0, Selection.selectQuick(input, 1));
		assertEquals(1001, Selection.selectQuick(input, size));
	}
}
