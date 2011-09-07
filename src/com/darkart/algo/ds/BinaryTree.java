package com.darkart.algo.ds;

public class BinaryTree<T extends Comparable<T>> {
	private BinaryNode<T> root;
	
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * inorder walk takes O(n)
	 * @param visitor
	 */
	public void inOrderWalk(Visitor visitor) {
		inOrderWalk(root, visitor);
	}

	private void inOrderWalk(BinaryNode<T> node, Visitor visitor) {
		if (node != null) {
			inOrderWalk(node.getLeftChild(), visitor);
			visitor.visit(node);
			inOrderWalk(node.getRightChild(), visitor);
		}
	}

	public BinaryNode<T> search(T obj) {
		BinaryNode<T> x = root;
		int c;
		while (x != null && (c = x.getData().compareTo(obj)) != 0) {
			if (c > 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}
		return x;
	}

	public BinaryNode<T> insert(T obj) {
		BinaryNode<T> p = null;
		BinaryNode<T> x = root;
		int c = 0;
		while (x != null) {
			p = x;
			c = obj.compareTo(p.getData());
			if (c < 0) {
				x = p.getLeftChild();
			} else {
				x = p.getRightChild();
			}
		}
		
		BinaryNode<T> n = new BinaryNode<T>(obj);
		n.setParent(p);
		
		if (p == null) {
			root = n;
		} else {
			if (c < 0) {
				p.setLeftChild(n);
			} else {
				p.setRightChild(n);
			}
		}
		
		return n;
	}
	
	public BinaryNode<T> delete(BinaryNode<T> n) {
		BinaryNode<T> y;
		if (n.getLeftChild() == null || n.getRightChild() == null) {
			y = n;
		} else {
			y = minimum(n.getRightChild());
		}
		
		//remove y
		BinaryNode<T> c = y.getLeftChild() != null ? y.getLeftChild() : y.getRightChild();
		BinaryNode<T> p = y.getParent();
		if (c != null) {
			c.setParent(p);
		}
		
		if (p == null) {
			root = c;
		} else {
			if (y == p.getLeftChild()) {
				p.setLeftChild(c);
			} else {
				p.setRightChild(c);
			}
		}
		
		if (y != n) {
			n.setData(y.getData());
		}
		
		return y;
	}

	public BinaryNode<T> minimum(BinaryNode<T> x) {
		while (x.getLeftChild() != null) {
			x = x.getLeftChild();
		}
		return x;
	}

	public BinaryNode<T> maximum(BinaryNode<T> x) {
		while (x.getRightChild() != null) {
			x = x.getRightChild();
		}
		return x;
	}

	public BinaryNode<T> successor(BinaryNode<T> n) {
		if (n.getRightChild() != null) {
			return minimum(n.getRightChild());
		} else {
			BinaryNode<T> p = n.getParent();
			while (p != null && p.getRightChild() == n) {
				n = p;
				p = p.getParent();
			}
			return p;
		}
	}

	public BinaryNode<T> predecessor(BinaryNode<T> n) {
		if (n.getLeftChild() != null) {
			return maximum(n.getLeftChild());
		} else {
			BinaryNode<T> p = n.getParent();
			while (p != null && p.getLeftChild() == n) {
				n = p;
				p = p.getParent();
			}
			return p;
		}
	}

	public int height() {
		return height(root);
	}

	private int height(BinaryNode<T> n) {
		if (n != null) {
			return 1 + Math.max(height(n.getLeftChild()), height(n.getRightChild()));
		} else {
			return 0;
		}
	}
}
