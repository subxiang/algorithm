package com.darkart.algo.ds;

public class SimpleStack implements Stack {
	private Object[] items;
	private int sp;
	
	public SimpleStack(int size) {
		items = new Object[size];
		sp = -1;
	}

	@Override
	public void push(Object obj) {
		items[++sp] = obj;
	}

	@Override
	public Object pop() {
		if (sp != -1) {
			return items[sp--];
		} else {
			throw new RuntimeException("underflow");
		}
	}

	@Override
	public boolean isEmpty() {
		return sp == -1;
	}

}
