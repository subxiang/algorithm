package com.darkart.algo.ds;

public class DoubleLinkedList implements List {
	private DoubleLinkedNode head;

	@Override
	public DoubleLinkedNode search(Object key) {
		DoubleLinkedNode node = head;
		while (node != null && node.getData() != key) {
			node = node.getNext();
		}
		return node;
	}

	@Override
	public void insert(Object obj) {
		DoubleLinkedNode node = new DoubleLinkedNode(obj);
		node.setNext(head);
		if (node.getNext() != null) {
			node.getNext().setPrev(node);
		}
		head = node;
	}

	@Override
	public void delete(Object obj) {
		DoubleLinkedNode node = search(obj);
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
