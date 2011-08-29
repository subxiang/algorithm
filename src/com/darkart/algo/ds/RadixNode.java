package com.darkart.algo.ds;

public class RadixNode implements Node {
	private String data;
	private RadixNode parent;
	private RadixNode[] children;
	
	public RadixNode() {
		children = new RadixNode['z' - 'A' + 1];
	}
	
	@Override
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public RadixNode getParent() {
		return parent;
	}
	
	public void setParent(RadixNode parent) {
		this.parent = parent;
	}

	public RadixNode getChild(char key) {
		return children[key - 'A'];
	}
	
	public void setChild(char key, RadixNode c) {
		children[key - 'A'] = c;
	}
	
	public static void main(String[] args) {
		System.out.println((int)'A');
	}
}
