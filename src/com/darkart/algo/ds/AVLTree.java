package com.darkart.algo.ds;

public class AVLTree<T extends Comparable<T>> {
	private final AVLNode<T> nil;
	private AVLNode<T> root;
	
	public AVLTree() {
		nil = new AVLNode<T>();
		nil.setParent(nil);
		nil.setLeftChild(nil);
		nil.setRightChild(nil);
		nil.setHeight(0);
		root = nil;
	}
	
	public AVLNode<T> insert(T obj) {
		AVLNode<T> x = root;
		AVLNode<T> p = x.getParent();
		while (x != nil) {
			p = x;
			x = obj.compareTo(p.getData()) < 0 ? p.getLeftChild() : p.getRightChild();
		}
		
		AVLNode<T> n = new AVLNode<T>();
		n.setData(obj);
		n.setParent(p);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		n.setHeight(1);
		
		if (p == nil) {
			root = n;
		} else {
			if (obj.compareTo(p.getData()) < 0) {
				p.setLeftChild(n);
			} else {
				p.setRightChild(n);
			}
		}
		
		balanceAdd(n);
		
		return n;
	}
	
	public AVLNode<T> remove(AVLNode<T> n) {
		AVLNode<T> x = (n.getLeftChild() == nil || n.getRightChild() == nil) ? n : minimum(n.getRightChild());

		AVLNode<T> c = x.getLeftChild() != nil ? x.getLeftChild() : x.getRightChild();
		c.setParent(x.getParent());
		if (x == root) {
			root = c;
		} else {
			if (x == x.getParent().getLeftChild()) {
				x.getParent().setLeftChild(c);
			} else {
				x.getParent().setRightChild(c);
			}
		}
		
		if (x != n) {
			n.setData(x.getData());
		}
		
		balanceDel(c);
		
		return x;
	}

	private void balanceDel(AVLNode<T> n) {
		AVLNode<T> p = n.getParent();
		if (p != nil) {
			if (n == p.getLeftChild()) {
				AVLNode<T> s = p.getRightChild();
				if (n.getHeight() == s.getHeight() - 2) {
					if (s.getLeftChild().getHeight() > s.getRightChild().getHeight()) {
						s = rightRotate(s);
						updateHeight(s.getRightChild());
						updateHeight(s);
					}
					p = leftRotate(p);
					updateHeight(p.getLeftChild());
				}
				updateHeight(p);
				balanceDel(p);
			} else {
				AVLNode<T> s = p.getLeftChild();
				if (n.getHeight() == s.getHeight() - 2) {
					if (s.getRightChild().getHeight() > s.getLeftChild().getHeight()) {
						s = leftRotate(s);
						updateHeight(s.getLeftChild());
						updateHeight(s);
					}
					p = rightRotate(p);
					updateHeight(p.getRightChild());
				}
				updateHeight(p);
				balanceDel(p);
			}
		}
	}

	private void balanceAdd(AVLNode<T> n) {
		AVLNode<T> p = n.getParent();
		
		if (p != nil) {
			if (n == p.getLeftChild()) {
				AVLNode<T> s = p.getRightChild();
				if (n.getHeight() == s.getHeight() + 2) {
					if (n.getRightChild().getHeight() > n.getLeftChild().getHeight()) {
						n = leftRotate(n);
						updateHeight(n.getLeftChild());
						updateHeight(n);
					}
					p = rightRotate(p);
					updateHeight(p.getRightChild());
				}
				updateHeight(p);
				balanceAdd(p);
			} else {
				AVLNode<T> s = p.getLeftChild();
				if (n.getHeight() == s.getHeight() + 2) {
					if (n.getLeftChild().getHeight() > n.getRightChild().getHeight()) {
						n = rightRotate(n);
						updateHeight(n.getRightChild());
						updateHeight(n);
					}
					p = leftRotate(p);
					updateHeight(p.getLeftChild());
				}
				updateHeight(p);
				balanceAdd(p);
			}
		}
	}
	
	private void updateHeight(AVLNode<T> n) {
		n.setHeight(Math.max(n.getLeftChild().getHeight(), n.getRightChild().getHeight()) + 1);
	}

	public T maximum() {
		return maximum(root).getData();
	}
	
	public AVLNode<T> maximum(AVLNode<T> n) {
		while (n.getRightChild() != nil) {
			n = n.getRightChild();
		}
		return n;
	}
	
	public T minimum() {
		return minimum(root).getData();
	}

	public AVLNode<T> minimum(AVLNode<T> n) {
		while (n.getLeftChild() != nil) {
			n = n.getLeftChild();
		}
		return n;
	}

	private AVLNode<T> rightRotate(AVLNode<T> n) {
		AVLNode<T> p = n.getParent();
		AVLNode<T> l = n.getLeftChild();
		l.getRightChild().setParent(n);
		n.setLeftChild(l.getRightChild());
		
		l.setParent(p);
		l.setRightChild(n);
		n.setParent(l);
		
		if (p == nil) {
			root = l;
		} else {
			if (n == p.getLeftChild()) {
				p.setLeftChild(l);
			} else {
				p.setRightChild(l);
			}
		}
		return l;
	}

	private AVLNode<T> leftRotate(AVLNode<T> n) {
		AVLNode<T> p = n.getParent();
		AVLNode<T> r = n.getRightChild();
		if (r.getLeftChild() != nil) {
			r.getLeftChild().setParent(n);
		}
		n.setRightChild(r.getLeftChild());
		
		r.setLeftChild(n);
		n.setParent(r);
		r.setParent(p);
		
		if (p == nil) {
			root = r;
		} else {
			if (n == p.getLeftChild()) {
				p.setLeftChild(r);
			} else {
				p.setRightChild(r);
			}
		}
		
		return r;
	}

	public void inOrderWalk(Visitor visitor) {
		inOrderWalk(root, visitor);
	}

	private void inOrderWalk(AVLNode<T> node, Visitor visitor) {
		if (node != nil) {
			inOrderWalk(node.getLeftChild(), visitor);
			visitor.visit(node);
			inOrderWalk(node.getRightChild(), visitor);
		}
	}

	public AVLNode<T> getRoot() {
		return root == nil ? null : root;
	}
}
