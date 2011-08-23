package com.darkart.algo.ds;

public class QuadraticProbeHasher implements OpenAddressHasher {
	private static final int C1 = 1;
	private static final int C2 = 2;
	private final Hasher hasher;
	private final int size;
	
	public QuadraticProbeHasher(Hasher hasher, int size) {
		this.hasher = hasher;
		this.size = size;
	}

	@Override
	public int hash(Object key, int i) {
		return (hasher.hash(key) + i*C1 + i*i*C2) % size;
	}

}
