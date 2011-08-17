package com.darkart.algo.ds;


public class SimpleQueue implements Queue {
	private Object[] items;
	private int head;
	private int tail;
	
	public SimpleQueue(int size) {
		items = new Object[size];
		head = tail = 0;
	}

	/**
	 * slightly modified version which can make sure of the full n elements.
	 */
	@Override
	public void enqueue(Object obj) {
		if (tail - head == items.length) {
			throw new RuntimeException("overflow");
		} else {
			items[tail++ % items.length] = obj;
		}
	}

	@Override
	public Object dequeue() {
		if (head == tail) {
			throw new RuntimeException("underflow");
		} else {
			return items[head++ % items.length];
		}
	}

}
