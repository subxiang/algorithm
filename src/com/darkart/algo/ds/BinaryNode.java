package com.darkart.algo.ds;

public class BinaryNode<T extends Comparable<T>> implements Node {
	private BinaryNode<T> parent;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	private T data;
	
	public BinaryNode(T data) {
		setData(data);
	}
	
	@Override
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public BinaryNode<T> getParent() {
		return parent;
	}
	public void setParent(BinaryNode<T> parent) {
		this.parent = parent;
	}
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinaryNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryNode<T> rightChild) {
		this.rightChild = rightChild;
	}
}
