package com.darkart.algo.tree;

public class AATree<T extends Comparable<T>, N extends AVLNode<T, N>> extends BinaryTree<T,N> {
	@Override
	protected N createNil() {
		N n = (N) new AVLNode<T,N>();
		n.setHeight(0);
		n.setParent(n);
		n.setLeftChild(n);
		n.setRightChild(n);
		return n;
	}
	
	@Override
	protected N createNode(T data) {
		N n = (N) new AVLNode<T,N>();
		n.setData(data);
		n.setHeight(1);
		n.setParent(nil);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		return n;
	}
	
	@Override
	protected void postAdd(N n) {
		while (n.getHeight() == n.getParent().getHeight()) {
			if (n == n.getParent().getLeftChild()) {
				n = n.getParent();
				rotateRight(n);
			} else {
				if (n.getHeight() == n.getRightChild().getHeight()) {
					n.setHeight(n.getHeight() + 1);
					rotateLeft(n.getParent());
				} else {
					n = n.getParent();
				}
			}
		}
	}
	
	@Override
	protected void postDel(N original, N removed, N r) {
		while (r.getHeight() == r.getParent().getHeight() - 2) {
			r = r.getParent();
			r.setHeight(r.getHeight() - 1);
			if (r.getRightChild().getHeight() > r.getHeight()) {
				r.getRightChild().setHeight(r.getHeight());
			}
			r = skew(r);
			skew(r.getRightChild());
			skew(r.getRightChild().getRightChild());
			r = split(r);
			split(r.getRightChild());
		}
	}
	
	private N skew(N n) {
		if (n.getHeight() == n.getLeftChild().getHeight()) {
			n = rotateRight(n);
		}
		return n;
	}
	
	private N split(N n) {
		if (n.getHeight() == n.getRightChild().getRightChild().getHeight()) {
			n = rotateLeft(n);
			n.setHeight(n.getHeight() + 1);
		}
		return n;
	}
}
