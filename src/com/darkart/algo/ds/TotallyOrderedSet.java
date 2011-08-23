package com.darkart.algo.ds;

public interface TotallyOrderedSet<T extends Comparable<?>> extends DynamicSet {
	T minimum();
	T maximum();
	T successor();
	T predecessor();
}
