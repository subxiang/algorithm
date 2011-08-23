package com.darkart.algo.ds;

public class CircularDoubleLinkedList implements List {
	private DoubleLinkedNode nil = new DoubleLinkedNode(null);
	
	public CircularDoubleLinkedList() {
		nil.setPrev(nil);
		nil.setNext(nil);
	}

	@Override
	public DoubleLinkedNode search(Object obj) {
		for (DoubleLinkedNode n = nil.getNext(); n != nil; n = n.getNext()) {
			if (n.getData().equals(obj)) {
				return n;
			}
		}
		return null;
	}

	@Override
	public void insert(Object obj) {
		DoubleLinkedNode node = new DoubleLinkedNode(obj);
		
		node.setPrev(nil);
		node.setNext(nil.getNext());
		nil.setNext(node);
		node.getNext().setPrev(node);
	}

	@Override
	public void delete(Object obj) {
		DoubleLinkedNode node = search(obj);
		if (node != null) {
			node.getPrev().setNext(node.getNext());
			node.getNext().setPrev(node.getPrev());
		}
	}
}
