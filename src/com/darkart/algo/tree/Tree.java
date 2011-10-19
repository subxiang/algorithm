package com.darkart.algo.tree;

public interface Tree<T, N extends Node<T, N>> {
	boolean isEmpty();
	N getRoot();
	N insert(T data);
	N search(T data);
	N remove(N n);
	int height(N n);
	int size(N n);
	void inOrderWalk(Visitor<T, N> visitor);
}
