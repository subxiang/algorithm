package com.darkart.algo.ds;

public class DivisionHasher implements Hasher {
	private int divider;

	public DivisionHasher(int divider) {
		this.divider = divider;
	}

	@Override
	public int hash(Object key) {
		return (Integer)key % divider;
	}

}
