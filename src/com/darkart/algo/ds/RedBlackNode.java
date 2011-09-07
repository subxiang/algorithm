package com.darkart.algo.ds;

public class RedBlackNode <T extends Comparable<T>> implements Node {
	enum Color {
		RED,
		BLACK
	}

	private RedBlackNode<T> parent;
	private RedBlackNode<T> leftChild;
	private RedBlackNode<T> rightChild;
	private Color color;
	private T data;
	
	public RedBlackNode() {
		this(Color.RED);
	}

	public RedBlackNode(Color color) {
		setColor(color);
	}

	public RedBlackNode<T> getParent() {
		return parent;
	}

	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
	}

	public RedBlackNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(RedBlackNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public RedBlackNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(RedBlackNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}
}
