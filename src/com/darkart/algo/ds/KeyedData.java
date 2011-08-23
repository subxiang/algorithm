package com.darkart.algo.ds;

class KeyedData {
	private Object key;
	private Object data;
	
	public KeyedData(Object key, Object data) {
		this.key = key;
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public Object getKey() {
		return key;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof KeyedData) && (((KeyedData)obj).key.equals(key));
	}
}