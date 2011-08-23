package com.darkart.algo.ds;

public class HashTable implements Table {
	private List[] lists;
	private Hasher hasher;
	
	public HashTable(int size) {
		lists = new List[size];
		for (int i = 0; i < size; i++) {
			lists[i] = new CircularDoubleLinkedList();
		}
		hasher = new DivisionHasher(size);
	}
	
	public void put(Object key, Object obj) {
		int k = hasher.hash(key);
		lists[k].insert(obj);
	}
	
	public Object get(Object key) {
		int k = hasher.hash(key);
		return ((KeyedData)lists[k].search(new KeyedData(key, null)).getData()).getData();
	}
	
	public void remove(Object key) {
		int k = hasher.hash(key);
		lists[k].delete(new KeyedData(key, null));
	}
}
