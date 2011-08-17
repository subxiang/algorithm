package com.darkart.algo.ds;

public class BinaryNode {
	private BinaryNode parent;
	private BinaryNode leftChild;
	private BinaryNode rightChild;
	private Object data;
	
	public BinaryNode(Object data) {
		setData(data);
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public BinaryNode getParent() {
		return parent;
	}
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	public BinaryNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinaryNode leftChild) {
		this.leftChild = leftChild;
	}
	public BinaryNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryNode rightChild) {
		this.rightChild = rightChild;
	}
}
