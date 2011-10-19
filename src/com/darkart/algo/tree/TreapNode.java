package com.darkart.algo.tree;

public class TreapNode<T extends Comparable<T>, N extends BinaryNode<T,N>> extends BinaryNode<T, N> {
	private int priority;
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
}
