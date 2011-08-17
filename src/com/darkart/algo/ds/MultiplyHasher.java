package com.darkart.algo.ds;

public class MultiplyHasher implements Hasher {
	private static final double A = 0.618;	// 0 < A < 1
	private int size;
	
	public MultiplyHasher(int size) {
		this.size = size;
	}

	@Override
	public int hash(Object key) {
		double r = (Integer)key * A;
		r = r - (int)r;
		return (int)(r * size);
	}

}
