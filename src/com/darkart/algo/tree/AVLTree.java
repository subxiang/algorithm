package com.darkart.algo.tree;

public class AVLTree<T extends Comparable<T>, N extends AVLNode<T,N>> extends BinaryTree<T,N> {
	@Override
	protected N createNode(T data) {
		N n = (N) new AVLNode<T,N>();
		n.setData(data);
		n.setParent(nil);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		n.setHeight(1);
		return n;
	}
	
	@Override
	protected N createNil() {
		N n = (N) new AVLNode<T,N>();
		n.setHeight(0);
		return n;
	}
	
	@Override
	protected void postAdd(N n) {
		while (n != root) {
			N p = n.getParent();
			if (n == p.getLeftChild()) {
				N s = p.getRightChild();
				if (n.getHeight() == s.getHeight()) {
					//nothing to do
					break;
				} else if (n.getHeight() == s.getHeight() + 1) {
					p.setHeight(p.getHeight() + 1);
					n = p;
				} else if (n.getHeight() == s.getHeight() + 2) {
					if (n.getRightChild().getHeight() == n.getLeftChild().getHeight() + 1) {
						int tmp = n.getHeight();
						n.setHeight(tmp - 1);
						n.getRightChild().setHeight(tmp);
						n = rotateLeft(n);	//when h(r) = h(l) + 1, left rotate has the effect of swapping height
					}
					if (n.getLeftChild().getHeight() == n.getRightChild().getHeight()) {
						rotateRight(p);
						n.setHeight(n.getHeight() + 1);
					} else {
						rotateRight(p);
						p.setHeight(p.getHeight() - 1);
						break;
					}
				}
			} else {
				N s = p.getLeftChild();
				if (n.getHeight() == s.getHeight()) {
					break;
				} else if (n.getHeight() == s.getHeight() + 1) {
					p.setHeight(p.getHeight() + 1);
					n = p;
				} else if (n.getHeight() == s.getHeight() + 2) {
					if (n.getLeftChild().getHeight() == n.getRightChild().getHeight() + 1) {
						int tmp = n.getHeight();
						n.setHeight(tmp - 1);
						n.getLeftChild().setHeight(tmp);
						n = rotateRight(n);
					}
					if (n.getLeftChild().getHeight() == n.getRightChild().getHeight()) {
						rotateLeft(p);
						n.setHeight(n.getHeight() + 1);
					} else {
						rotateLeft(p);
						p.setHeight(p.getHeight() - 1);
						break;
					}
				}
			}
		}
	}
	
	@Override
	protected void postDel(N original, N removed, N replaced) {
		balanceDel(replaced);
	}
	
	private void balanceDel(N n) {
		while (n != root) {
			N p = n.getParent();
			if (n == p.getLeftChild()) {
				N s = p.getRightChild();
				if (n.getHeight() == s.getHeight()) {
					p.setHeight(p.getHeight() - 1);
					n = p;
				} else if (n.getHeight() == s.getHeight() - 1) {
					//nothing to do
					break;
				} else if (n.getHeight() == s.getHeight() - 2) {
					if (s.getLeftChild().getHeight() == s.getRightChild().getHeight() + 1) {
						int tmp = s.getHeight();
						s.setHeight(tmp - 1);
						s.getLeftChild().setHeight(tmp);
						s = rotateRight(s);
					}
					if (s.getLeftChild().getHeight() == s.getRightChild().getHeight()) { //s.l == s.r, r.h does not change
						p.setHeight(p.getHeight() - 1);
						s.setHeight(s.getHeight() + 1);
						rotateLeft(p);
						break;
					} else { // s.l < s.r, r.h decrease 1
						p.setHeight(p.getHeight() - 2);
						n = rotateLeft(p);
					}
				}
			} else {
				N s = p.getLeftChild();
				if (n.getHeight() == s.getHeight()) {
					p.setHeight(p.getHeight() - 1);
					n = p;
				} else if (n.getHeight() == s.getHeight() - 1) {
					break;
				} else if (n.getHeight() == s.getHeight() - 2) {
					if (s.getRightChild().getHeight() == s.getLeftChild().getHeight() + 1) {
						int tmp = s.getHeight();
						s.setHeight(tmp - 1);
						s.getRightChild().setHeight(tmp);
						s = rotateLeft(s);
					}
					if (s.getLeftChild().getHeight() == s.getRightChild().getHeight()) {
						p.setHeight(p.getHeight() - 1);
						s.setHeight(s.getHeight() + 1);
						rotateRight(p);
						break;
					} else {
						p.setHeight(p.getHeight() - 2);
						n = rotateRight(p);
					}
				}
			}
		}
	}

	private void updateHeight(N n) {
		n.setHeight(Math.max(n.getLeftChild().getHeight(), n.getRightChild().getHeight()) + 1);
	}
}
