package com.darkart.algo.ds;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;

public class RadixTree {
	private RadixNode root = new RadixNode();
	
	public void insert(String data) {
		RadixNode x = root;
		for (char key : data.toCharArray()) {
			if (x.getChild(key) == null) {
				x.setChild(key, new RadixNode());
			}
			x = x.getChild(key);
		}
		x.setData(data);
	}
	
	public void walk(Visitor visitor) {
		walk(root, visitor);
	}

	private void walk(RadixNode n, Visitor visitor) {
		visitor.visit(n);
		for (char key = 'A'; key <= 'z'; key++) {
			if (n.getChild(key) != null) {
				walk(n.getChild(key), visitor);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		RadixTree tree = new RadixTree();
		URL url = new URL("http://www.google.com.sg");
//		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("10.67.8.37", 8080));
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		
		int i;
		StringBuilder str = new StringBuilder();
		while ((i = in.read()) != -1) {
			char c = (char)i;
			if (c >= 'A' && c <= 'z') {
				str.append(c);
			} else {
				tree.insert(str.toString());
				str.setLength(0);
			}
		}
		in.close();
		
		tree.walk(new Visitor() {

			@Override
			public void visit(Node n) {
				if (n.getData() != null) {
					System.out.println(n.getData());
				}
			}
			
		});
	}
}
