package com.darkart.algo.ds;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RBTreeTest {
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
	public void test() {
		final RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		for (int i = 0; i < input.length; i++) {
			tree.insert(input[i]);
		}
		
		tree.inOrderWalk(new Visitor() {
			int i = 0;

			@Override
			public void visit(Node n) {
				input[i++] = (Integer)n.getData();
				RedBlackNode<Integer> nn = (RedBlackNode<Integer>) n;
				assertEquals(tree.blackHeight(nn.getLeftChild()), tree.blackHeight(nn.getRightChild()));
			}
			
		});
		
		System.out.println(tree.blackHeight());
		System.out.println(tree.height());
		
		RedBlackNode<Integer> root = tree.getRoot();
		Integer i = Integer.MIN_VALUE;
		while (root != null) {
			assertEquals(tree.blackHeight(root.getLeftChild()), tree.blackHeight(root.getRightChild()));
			RedBlackNode<Integer> min = tree.minimum(root);
			System.out.println(min.getData());
			assertTrue(min.getData().compareTo(i) >= 0);
			i = min.getData();
			tree.remove(min);
			root = tree.getRoot();
		}
	}

}
