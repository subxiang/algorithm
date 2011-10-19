package com.darkart.algo.tree;

public class AVLNode<T extends Comparable<T>, N extends AVLNode<T,N>> extends BinaryNode<T,N> {
	private int height;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
