package com.darkart.algo.ds;

public class MultiTreeNode {
	private MultiTreeNode parent;
	private MultiTreeNode leftChild;
	private MultiTreeNode rightSibling;
	private Object data;
	
	public MultiTreeNode(Object data) {
		setData(data);
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public MultiTreeNode getParent() {
		return parent;
	}
	public void setParent(MultiTreeNode parent) {
		this.parent = parent;
	}

	public MultiTreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(MultiTreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public MultiTreeNode getRightSibling() {
		return rightSibling;
	}
	public void setRightSibling(MultiTreeNode rightSibling) {
		this.rightSibling = rightSibling;
	}
}
