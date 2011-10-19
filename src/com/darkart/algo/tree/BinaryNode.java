package com.darkart.algo.tree;

public class BinaryNode<T extends Comparable<T>, N extends BinaryNode<T, N>> implements Node<T, N> {
	private T data;
	private N parent;
	private N leftChild;
	private N rightChild;

	@Override
	public N getParent() {
		return parent;
	}

	@Override
	public void setParent(N n) {
		this.parent = n;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public void setData(T data) {
		this.data = data;
	}

	public N getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(N leftChild) {
		this.leftChild = leftChild;
	}

	public N getRightChild() {
		return rightChild;
	}

	public void setRightChild(N rightChild) {
		this.rightChild = rightChild;
	}
}
