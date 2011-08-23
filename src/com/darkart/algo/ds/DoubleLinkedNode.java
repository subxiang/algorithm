package com.darkart.algo.ds;

public class DoubleLinkedNode implements Node {
	private DoubleLinkedNode prev;
	private DoubleLinkedNode next;
	private Object data;
	
	public DoubleLinkedNode(Object obj) {
		setData(obj);
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public DoubleLinkedNode getPrev() {
		return prev;
	}

	public void setPrev(DoubleLinkedNode prev) {
		this.prev = prev;
	}

	public DoubleLinkedNode getNext() {
		return next;
	}

	public void setNext(DoubleLinkedNode next) {
		this.next = next;
	}
}
