package com.darkart.algo.tree;

public interface Visitor<T, N extends Node<T,N>> {
	public void visit(N n);
}
