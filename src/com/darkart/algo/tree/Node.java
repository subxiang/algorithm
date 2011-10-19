package com.darkart.algo.tree;

public interface Node<T, N extends Node<T, N>> {
	N getParent();
	void setParent(N n);
	T getData();
	void setData(T data);
}
