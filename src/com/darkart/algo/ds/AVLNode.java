package com.darkart.algo.ds;

public class AVLNode<T extends Comparable<T>> implements Node {
	private AVLNode<T> parent;
	private AVLNode<T> leftChild;
	private AVLNode<T> rightChild;
	private T data;
	private int height;

	public AVLNode<T> getParent() {
		return parent;
	}

	public void setParent(AVLNode<T> parent) {
		this.parent = parent;
	}

	public AVLNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(AVLNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public AVLNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(AVLNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}
}
