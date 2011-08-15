package com.darkart.algo.ds;

public interface Heap {
	void buildHeap();
	void heapify(int index);
	void update(int index, int newValue);
}
