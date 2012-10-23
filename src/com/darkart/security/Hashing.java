package com.darkart.security;

import java.io.IOException;
import java.io.InputStream;

public class Hashing {
	private static final byte[][] MD5_BUFFER_INIT = {
		{(byte)0x01, (byte)0x23, (byte)0x45, (byte)0x67},
		{(byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef},
		{(byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98},
		{(byte)0x76, (byte)0x54, (byte)0x32, (byte)0x10}
	};
	
	private static final int[][] MD5_SHIFT = {
		{7,	12,	17,	22},
		{5,	9,	14,	20},
		{4,	11,	16,	23},
		{6,	10,	15,	21}
	};
	
	//random number table. := floor(4294967296 * abs(sin(i)))
	private static final byte[] MD4_SIN_TABLE = {
		//for readability, use formula in the process instead of table lookup.
	};

	
	public static byte[] md5(InputStream data) throws IOException {
		int state_a = MD5_BUFFER_INIT[0][0] | MD5_BUFFER_INIT[0][1] << 8 | MD5_BUFFER_INIT[0][2] << 16 | MD5_BUFFER_INIT[0][3] << 24;
		int state_b = MD5_BUFFER_INIT[1][0] | MD5_BUFFER_INIT[1][1] << 8 | MD5_BUFFER_INIT[1][2] << 16 | MD5_BUFFER_INIT[1][3] << 24;
		int state_c = MD5_BUFFER_INIT[2][0] | MD5_BUFFER_INIT[2][1] << 8 | MD5_BUFFER_INIT[2][2] << 16 | MD5_BUFFER_INIT[2][3] << 24;
		int state_d = MD5_BUFFER_INIT[3][0] | MD5_BUFFER_INIT[3][1] << 8 | MD5_BUFFER_INIT[3][2] << 16 | MD5_BUFFER_INIT[3][3] << 24;

		byte[] block = new byte[64];
		boolean padded = false;
		long totalbits = 0;
		
		while (true) {
			int state_aa = state_a, state_bb = state_b, state_cc = state_c, state_dd = state_d;
			
			int readCount = data.read(block);
			
			if (readCount == -1 && padded) {
				break;
			}
			
			if (readCount > 0) {
				totalbits += readCount * 8;
			}
			
			if (readCount < block.length) {
				if (readCount == -1) {
					readCount = 0;
				}
				
				//padding
				
				padded = true;
			}
			
			int[] x = decode(block);	//transform 64byte-block to 16 words
			
			//process
		}
		
		return new byte[0];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
