package com.darkart.algo.ds;

public class OpenAddressHashTable implements Table {
	private int size;
	private KeyedData[] items;
	private OpenAddressHasher hasher;

	public OpenAddressHashTable(int size) {
		this.size = size;
		this.items = new KeyedData[size];
		this.hasher = new LinearProbingHasher(new DivisionHasher(size), size);
	}
	
	@Override
	public void put(Object key, Object obj) {
		for (int i = 0; i < size; i++) {
			int index = hasher.hash(key, i);
			if (items[index] == null) {
				items[index] = new KeyedData(key, obj);
			}
		}
	}

	@Override
	public Object get(Object key) {
		for (int i = 0; i < size; i++) {
			int index = hasher.hash(key, i);
			if (items[index] == null) {
				return null;
			} else if (items[index].getKey().equals(key)) {
				return items[index].getData();
			}
		}
		return null;
	}

	@Override
	public void remove(Object key) {
		//just don't do it! otherwise guaranteed O(n) time for each search
		throw new RuntimeException("better don't do it!");
	}

}
