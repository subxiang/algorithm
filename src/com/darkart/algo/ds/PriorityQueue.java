package com.darkart.algo.ds;

public interface PriorityQueue<T extends Comparable<T>> {
	void insert(T e);
	T peek();
	T pop();
	//void updateKey(T e, int key???);
}
