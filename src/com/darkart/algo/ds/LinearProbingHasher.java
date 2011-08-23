package com.darkart.algo.ds;

public class LinearProbingHasher implements OpenAddressHasher {
	private final Hasher hasher;
	private final int size;

	public LinearProbingHasher(Hasher hasher, int size) {
		this.hasher = hasher;
		this.size = size;
	}

	@Override
	public int hash(Object key, int i) {
		return (hasher.hash(key) + i) % size;
	}

}
