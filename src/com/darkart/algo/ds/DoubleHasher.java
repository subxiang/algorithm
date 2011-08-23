package com.darkart.algo.ds;

public class DoubleHasher implements OpenAddressHasher {
	private final Hasher h1;
	private final Hasher h2;
	private final int size;

	public DoubleHasher(Hasher h1, Hasher h2, int size) {
		this.h1 = h1;
		this.h2 = h2;
		this.size = size;
	}

	@Override
	public int hash(Object key, int i) {
		return (h1.hash(key) + i * h2.hash(key)) % size;
	}

}
