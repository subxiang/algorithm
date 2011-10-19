package com.darkart.algo.tree;

import java.util.Calendar;
import java.util.Random;

public class Treap<T extends Comparable<T>, N extends TreapNode<T,N>> extends BinaryTree<T, N> {
	private static final Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
	
	@Override
	protected N createNil() {
		N n = (N) new TreapNode<T,N>();
		n.setParent(n);
		n.setLeftChild(n);
		n.setRightChild(n);
		n.setPriority(Integer.MAX_VALUE);
		return n;
	}
	
	protected N createNode(T data) {
		N n = (N) new TreapNode<T,N>();
		n.setLeftChild(nil);
		n.setRightChild(nil);
		n.setData(data);
		n.setPriority(rnd.nextInt());
		return n;
	}
	
	@Override
	protected void postAdd(N n) {
		while (n.getParent() != nil && n.getPriority() < n.getParent().getPriority()) {
			if (n == n.getParent().getLeftChild()) {
				rotateRight(n.getParent());
			} else {
				rotateLeft(n.getParent());
			}
		}
	}
	
	@Override
	protected void postDel(N original, N removed, N replaced) {
		original.setPriority(removed.getPriority());
		N c = original.getLeftChild().getPriority() < original.getRightChild().getPriority() ? original.getLeftChild() : original.getRightChild();
		while (original.getPriority() > c.getPriority()) {
			if (c == original.getLeftChild()) {
				rotateRight(original);
			} else {
				rotateLeft(original);
			}
			c = original.getLeftChild().getPriority() < original.getRightChild().getPriority() ? original.getLeftChild() : original.getRightChild();
		}
	}
}
