package com.darkart.algo.tree;

public class RBTree<T extends Comparable<T>, N extends RBNode<T,N>> extends BinaryTree<T,N> {
	@Override
	protected N createNil() {
		N n = (N) new RBNode<T,N>();
		n.setRed(false);
		n.setParent(n);
		n.setLeftChild(n);
		n.setRightChild(n);
		
		return n;
	}
	
	@Override
	protected N createNode(T data) {
		N n = (N) new RBNode<T,N>();
		n.setData(data);
		n.setParent(nil);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		n.setRed(true);
		return n;
	}
	
	@Override
	protected void postAdd(N n) {
		N p = n.getParent();
		while (p.isRed()) {
			if (p == p.getParent().getLeftChild()) {
				if (n == p.getRightChild()) {
					p = rotateLeft(p);
					n = p.getLeftChild();
				}
				N u = p.getParent().getRightChild();
				if (u.isRed()) {
					u.setRed(false);
					p.setRed(false);
					p.getParent().setRed(true);
					n = p.getParent();
					p = n.getParent();
				} else {
					p.getParent().setRed(true);
					p.setRed(false);
					rotateRight(p.getParent());
				}
			} else {
				if (n == p.getLeftChild()) {
					p = rotateRight(p);
					n = p.getRightChild();
				}
				N u = (N) p.getParent().getLeftChild();
				if (u.isRed()) {
					u.setRed(false);
					p.setRed(false);
					p.getParent().setRed(true);
					n = p.getParent();
					p = n.getParent();
				} else {
					p.getParent().setRed(true);
					p.setRed(false);
					rotateLeft(p.getParent());
				}
			}
		}
		if (p == nil) {
			n.setRed(false);
		}
	}
	
	@Override
	protected void postDel(N original, N removed, N replaced) {
		if (!removed.isRed()) {
			while (replaced != root && !replaced.isRed()) {
				N p = replaced.getParent();
				if (replaced == p.getLeftChild()) {
					N s = p.getRightChild();
					if (s.isRed()) {
						s.setRed(false);
						p.setRed(true);
						rotateLeft(p);
						s = p.getRightChild();
					}
					if (!s.getLeftChild().isRed() && !s.getRightChild().isRed()) {
						s.setRed(true);
						replaced = p;
					} else {
						if (!s.getRightChild().isRed()) {
							s.setRed(true);
							s.getLeftChild().setRed(false);
							s = rotateRight(s);
						}
						s.setRed(p.isRed());
						p.setRed(false);
						s.getRightChild().setRed(false);
						rotateLeft(p);
						replaced = root;
					}
				} else {
					N s = p.getLeftChild();
					if (s.isRed()) {
						s.setRed(false);
						p.setRed(true);
						rotateRight(p);
						s = p.getLeftChild();
					}
					if (!s.getLeftChild().isRed() && !s.getRightChild().isRed()) {
						s.setRed(true);
						replaced = p;
					} else {
						if (!s.getLeftChild().isRed()) {
							s.setRed(true);
							s.getRightChild().setRed(false);
							s = rotateLeft(s);
						}
						s.setRed(p.isRed());
						p.setRed(false);
						s.getLeftChild().setRed(false);
						rotateRight(p);
						replaced = root;
					}
				}
			}
			replaced.setRed(false);
		}
	}
}
