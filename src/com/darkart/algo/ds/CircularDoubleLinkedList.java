package com.darkart.algo.ds;

public class CircularDoubleLinkedList implements List {
	private Node nil = new Node(null);
	
	public CircularDoubleLinkedList() {
		nil.setPrev(nil);
		nil.setNext(nil);
	}

	@Override
	public Node search(Object obj) {
		for (Node n = nil.getNext(); n != nil; n = n.getNext()) {
			if (n.getData().equals(obj)) {
				return n;
			}
		}
		return null;
	}

	@Override
	public void insert(Object obj) {
		Node node = new Node(obj);
		
		node.setPrev(nil);
		node.setNext(nil.getNext());
		nil.setNext(node);
		node.getNext().setPrev(node);
	}

	@Override
	public void delete(Object obj) {
		Node node = search(obj);
		if (node != null) {
			node.getPrev().setNext(node.getNext());
			node.getNext().setPrev(node.getPrev());
		}
	}
}
