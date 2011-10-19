package com.darkart.algo.tree;

public class RBNode<T extends Comparable<T>, N extends RBNode<T,N>> extends BinaryNode<T,N> {
	private boolean red;

	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}
}
