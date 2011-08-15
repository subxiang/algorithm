package com.darkart.algo.ds;

public interface TotallyOrderedSet extends DynamicSet {
	Object minimum();
	Object maximum();
	Object successor();
	Object predecessor();
}
