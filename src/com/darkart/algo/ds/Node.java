package com.darkart.algo.ds;

public class Node {
	private Node prev;
	private Node next;
	private Object data;
	
	public Node(Object obj) {
		setData(obj);
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
