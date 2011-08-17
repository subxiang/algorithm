package com.darkart.algo.ds;

public class DoubleLinkedList implements List {
	private Node head;

	@Override
	public Node search(Object key) {
		Node node = head;
		while (node != null && node.getData() != key) {
			node = node.getNext();
		}
		return node;
	}

	@Override
	public void insert(Object obj) {
		Node node = new Node(obj);
		node.setNext(head);
		if (node.getNext() != null) {
			node.getNext().setPrev(node);
		}
		head = node;
	}

	@Override
	public void delete(Object obj) {
		Node node = (Node) search(obj);
		if (node != null) {
			if (node.getPrev() != null) {
				node.getPrev().setNext(node.getNext());
			} else {
				head = node.getNext();
			}
			if (node.getNext() != null) {
				node.getNext().setPrev(node.getPrev());
			}
		}
	}

}
