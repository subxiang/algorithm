package com.darkart.algo.ds;

public interface Table {
	public void put(Object key, Object obj);
	public Object get(Object key);
	public void remove(Object key);
}
