package com.darkart.algo.tree;

public class BinaryTree<T extends Comparable<T>, N extends BinaryNode<T, N>> implements Tree<T, N> {
	protected final N nil = createNil();
	protected N root = nil;
	
	protected N createNil() {
		N n = (N) new BinaryNode<T,N>();
		n.setParent(n);
		n.setLeftChild(n);
		n.setRightChild(n);
		return n;
	}
	
	protected N createNode(T data) {
		N n = (N) new BinaryNode<T,N>();
		n.setData(data);
		n.setParent(nil);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		return n;
	}
	
	protected void postAdd(N n) {
	}
	
	protected void postDel(N original, N removed, N replaced) {
	}
	
	protected N rotateLeft(N n) {
		N c = n.getRightChild();
		c.setParent(n.getParent());
		if (n.getParent() == nil) {
			root = c;
		} else {
			if (n == n.getParent().getLeftChild()) {
				n.getParent().setLeftChild(c);
			} else {
				n.getParent().setRightChild(c);
			}
		}
		
		c.getLeftChild().setParent(n);
		n.setRightChild(c.getLeftChild());
		
		n.setParent(c);
		c.setLeftChild(n);
		return c;
	}
	
	protected N rotateRight(N n) {
		N c = n.getLeftChild();
		c.setParent(n.getParent());
		if (n.getParent() == nil) {
			root = c;
		} else {
			if (n == n.getParent().getLeftChild()) {
				n.getParent().setLeftChild(c);
			} else {
				n.getParent().setRightChild(c);
			}
		}
		
		c.getRightChild().setParent(n);
		n.setLeftChild(c.getRightChild());
		
		n.setParent(c);
		c.setRightChild(n);
		return c;
	}
	
	private N minimum(N n) {
		N x = root;
		while (x.getLeftChild() != nil) {
			x = x.getLeftChild();
		}
		return x;
	}

	private N maximum(N n) {
		N x = root;
		while (x.getRightChild() != nil) {
			x = x.getRightChild();
		}
		return x;
	}

	@Override
	public boolean isEmpty() {
		return root == nil;
	}

	@Override
	public N getRoot() {
		return root == nil ? null : root;
	}

	@Override
	public N insert(T data) {
		N p = nil;
		N x = root;
		while (x != nil) {
			p = x;
			if (data.compareTo(p.getData()) < 0) {
				x = p.getLeftChild();
			} else {
				x = p.getRightChild();
			}
		}
		
		N n = createNode(data);
		n.setParent(p);
		if (p == nil) {
			root = n;
		} else {
			if (data.compareTo(p.getData()) < 0) {
				p.setLeftChild(n);
			} else {
				p.setRightChild(n);
			}
		}
		
		postAdd(n);
		
		return n;
	}

	@Override
	public N search(T data) {
		N x = root;
		while (x != nil) {
			if (data.compareTo(x.getData()) < 0) {
				x = x.getLeftChild();
			} else if (data.compareTo(x.getData()) > 0) {
				x = x.getRightChild();
			} else {
				return (N) x;
			}
		}
		return null;
	}

	@Override
	public N remove(N n) {
		N x = (n.getLeftChild() == nil || n.getRightChild() == nil) ? n : maximum(n.getLeftChild());
		N c = x.getLeftChild() != nil ? x.getLeftChild() : x.getRightChild();
		N p = x.getParent();
		
		c.setParent(p);	// it's important to set c.parent even if c == nil, for RBtree etc recursion starts from c upwards
		
		if (p == nil) {
			root = c;
		} else {
			if (x == p.getLeftChild()) {
				p.setLeftChild(c);
			} else {
				p.setRightChild(c);
			}
		}
		
		if (x != n) {
			n.setData(x.getData());
		}
		
		postDel(n, x, c);
		
		return x;
	}

	@Override
	public int height(N n) {
		if (n == nil) {
			return 0;
		} else {
			return Math.max(height(n.getLeftChild()), height(n.getRightChild())) + 1;
		}
	}

	@Override
	public int size(N n) {
		if (n == nil) {
			return 0;
		} else {
			return size(n.getLeftChild()) + size(n.getRightChild()) + 1;
		}
	}

	@Override
	public void inOrderWalk(Visitor<T,N> visitor) {
		inOrderWalk(root, visitor);
	}

	private void inOrderWalk(N n, Visitor<T,N> visitor) {
		if (n != nil) {
			inOrderWalk(n.getLeftChild(), visitor);
			visitor.visit(n);
			inOrderWalk(n.getRightChild(), visitor);
		}
	}
}
