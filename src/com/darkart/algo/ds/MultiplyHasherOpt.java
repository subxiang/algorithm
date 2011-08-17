package com.darkart.algo.ds;

public class MultiplyHasherOpt implements Hasher {
	private static final long s = 2654435769L;  //(sqrt(5) - 1) * 2^31
	private int p;

	public MultiplyHasherOpt(int size) {
		//assume size = 2^p
		p = 31 - Integer.numberOfLeadingZeros(size); //floor of log(2, size)
	}
	
	@Override
	public int hash(Object key) {
		return ((int)(((Integer)key) * s)) >>> (32 - p);
	}
	
	public static void main(String[] args) {
		MultiplyHasherOpt hasher = new MultiplyHasherOpt(16384);
		
		System.out.println(hasher.hash(123456));
	}
}
