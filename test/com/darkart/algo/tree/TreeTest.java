package com.darkart.algo.tree;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {
	private static final int INPUT_LENGTH = 1000000;
	private int[] input;
	private int[] ref;
	
	private long startTime;
	private long endTime;

	@Before
	public void setUp() throws Exception {
		Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
		
		input = new int[INPUT_LENGTH];
		for (int i = 0; i < input.length; i++) {
			input[i] = Math.abs(rnd.nextInt());
		}
//		Arrays.sort(input);
		ref = Arrays.copyOf(input, input.length);
		
		startTime = Calendar.getInstance().getTimeInMillis();
	}

	@After
	public void tearDown() throws Exception {
		endTime = Calendar.getInstance().getTimeInMillis();
		System.out.println("Total time: " + (endTime - startTime) + "ms.");
		
		Arrays.sort(ref);
		for (int i = 0; i < input.length; i++) {
			assertEquals(ref[i], input[i]);
		}
	}

	@Test
	public <N extends AVLNode<Integer, N>> void test() {
		final Tree<Integer, N> tree = createTree();
		for (int i = 0; i < input.length; i++) {
			tree.insert(input[i]);
		}
		
		System.out.println(tree.height(tree.getRoot()));
		
		tree.inOrderWalk(new Visitor<Integer, N>() {
			int i = 0;

			@Override
			public void visit(N n) {
				input[i++] = n.getData();
			}
			
		});
		
		N root = tree.getRoot();
		while (root != null) {
			tree.remove(root);
			
			root = tree.getRoot();
		}
	}

	protected BinaryTree createTree() {
		return new AATree();
	}

}
