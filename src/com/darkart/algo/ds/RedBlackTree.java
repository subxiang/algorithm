package com.darkart.algo.ds;

import com.darkart.algo.ds.RedBlackNode.Color;


public class RedBlackTree<T extends Comparable<T>> {
	private final RedBlackNode<T> nil;
	private RedBlackNode<T> root;
	
	public RedBlackTree() {
		nil = new RedBlackNode<T>(Color.BLACK);
		nil.setParent(nil);
		nil.setLeftChild(nil);
		nil.setRightChild(nil);
		
		root = nil;
	}
	
	public RedBlackNode<T> getRoot() {
		return root == nil ? null : root;
	}
	
	public RedBlackNode<T> insert(T obj) {
		RedBlackNode<T> p = nil;
		RedBlackNode<T> x = root;
		while (x != nil) {
			p = x;
			if (obj.compareTo(p.getData()) <= 0) {
				x = p.getLeftChild();
			} else {
				x = p.getRightChild();
			}
		}
		
		RedBlackNode<T> n = new RedBlackNode<T>();
		n.setData(obj);
		n.setParent(p);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		
		if (p == nil) {
			root = n;
		} else {
			if (obj.compareTo(p.getData()) <= 0) {
				p.setLeftChild(n);
			} else {
				p.setRightChild(n);
			}
		}
		
		fixRedBlackInsert(n);
		
		return n;
	}

	private void fixRedBlackInsert(RedBlackNode<T> n) {
		RedBlackNode<T> p = n.getParent();
		while (p.getColor() == Color.RED) {
			RedBlackNode<T> pp = p.getParent();
			if (p == pp.getLeftChild()) {
				if (n == p.getRightChild()) {
					p = leftRotate(p);
					n = p.getLeftChild();
				}
				
				RedBlackNode<T> u = pp.getRightChild();
				if (u.getColor() == Color.BLACK) {
					pp.setColor(Color.RED);
					p.setColor(Color.BLACK);
					rightRotate(pp);
				} else {
					pp.setColor(Color.RED);
					p.setColor(Color.BLACK);
					u.setColor(Color.BLACK);
					
					n = pp;
					p = n.getParent();
				}
			} else {
				if (n == p.getLeftChild()) {
					p = rightRotate(p);
					n = p.getRightChild();
				}
				
				RedBlackNode<T> u = pp.getLeftChild();
				if (u.getColor() == Color.BLACK) {
					pp.setColor(Color.RED);
					p.setColor(Color.BLACK);
					leftRotate(pp);
				} else {
					pp.setColor(Color.RED);
					p.setColor(Color.BLACK);
					u.setColor(Color.BLACK);
					
					n = pp;
					p = n.getParent();
				}
			}
		}
		if (p == nil) {
			n.setColor(Color.BLACK);
		}
	}
	
	public RedBlackNode<T> remove(RedBlackNode<T> n) {
		RedBlackNode<T> toRemove;
		if (n.getLeftChild() == nil || n.getRightChild() == nil) {
			toRemove = n;
		} else {
			toRemove = maximum(n.getLeftChild());
		}
		
		RedBlackNode<T> p = toRemove.getParent();
		RedBlackNode<T> c = toRemove.getLeftChild() != nil ? toRemove.getLeftChild() : toRemove.getRightChild();
		c.setParent(p);
		if (p == nil) {
			root = c;
		} else {
			if (toRemove == p.getLeftChild()) {
				p.setLeftChild(c);
			} else {
				p.setRightChild(c);
			}
		}
		
		if (toRemove != n) {
			n.setData(toRemove.getData());
		}
		
		if (toRemove.getColor() == Color.BLACK) {
			fixRedBlackDelete(c);
		}
		
		return toRemove;
	}

	private void fixRedBlackDelete(RedBlackNode<T> n) {
		while (n != root && n.getColor() == Color.BLACK) {
			RedBlackNode<T> p = n.getParent();
			if (n == p.getLeftChild()) {
				RedBlackNode<T> s = p.getRightChild();
				
				if (s.getColor() == Color.RED) { //s must have 2 black children
					p.setColor(Color.RED);
					s.setColor(Color.BLACK);
					leftRotate(p);
					s = p.getRightChild();
				}
				
				RedBlackNode<T> sl = s.getLeftChild();
				RedBlackNode<T> sr = s.getRightChild();
				
				//s is black now
				if (sl.getColor() == Color.BLACK && sr.getColor() == Color.BLACK) {
					s.setColor(Color.RED);
					n = p;	//only this case need recursion
				} else {
					if (sr.getColor() != Color.RED) {
						sl.setColor(Color.BLACK);
						s.setColor(Color.RED);
						s = rightRotate(s);
						sr = s.getRightChild();	// s is black and sr is RED now
					}
					s.setColor(p.getColor());
					p.setColor(Color.BLACK);
					sr.setColor(Color.BLACK);
					leftRotate(p);
					break;
				}
			} else {
				RedBlackNode<T> s = p.getLeftChild();
				
				if (s.getColor() == Color.RED) {
					p.setColor(Color.RED);
					s.setColor(Color.BLACK);
					rightRotate(p);
					s = p.getLeftChild();
				}
				
				RedBlackNode<T> sl = s.getLeftChild();
				RedBlackNode<T> sr = s.getRightChild();
				
				if (sl.getColor() == Color.BLACK && sr.getColor() == Color.BLACK) {
					s.setColor(Color.RED);
					n = p;
				} else {
					if (sl.getColor() != Color.RED) {
						sr.setColor(Color.BLACK);
						s.setColor(Color.RED);
						s = leftRotate(s);
						sl = s.getLeftChild();
					}
					s.setColor(p.getColor());
					p.setColor(Color.BLACK);
					sl.setColor(Color.BLACK);
					rightRotate(p);
					break;
				}
			}
		}
		n.setColor(Color.BLACK);	//n is either root or n.color == red
	}

	public T maximum() {
		return maximum(root).getData();
	}
	
	public RedBlackNode<T> maximum(RedBlackNode<T> n) {
		while (n.getRightChild() != nil) {
			n = n.getRightChild();
		}
		return n;
	}
	
	public T minimum() {
		return minimum(root).getData();
	}

	public RedBlackNode<T> minimum(RedBlackNode<T> n) {
		while (n.getLeftChild() != nil) {
			n = n.getLeftChild();
		}
		return n;
	}

	private RedBlackNode<T> rightRotate(RedBlackNode<T> n) {
		RedBlackNode<T> p = n.getParent();
		RedBlackNode<T> l = n.getLeftChild();
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

	private RedBlackNode<T> leftRotate(RedBlackNode<T> n) {
		RedBlackNode<T> p = n.getParent();
		RedBlackNode<T> r = n.getRightChild();
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

	private void inOrderWalk(RedBlackNode<T> node, Visitor visitor) {
		if (node != nil) {
			inOrderWalk(node.getLeftChild(), visitor);
			visitor.visit(node);
			inOrderWalk(node.getRightChild(), visitor);
		}
	}
	
	public int height() {
		return height(root);
	}

	private int height(RedBlackNode<T> n) {
		if (n != nil) {
			return 1 + Math.max(height(n.getLeftChild()), height(n.getRightChild()));
		} else {
			return 0;
		}
	}

	public int blackHeight(RedBlackNode<T> n) {
		if (n == nil) {
			return 0;
		} else if (n.getColor() == Color.BLACK) {
			return 1 + blackHeight(n.getLeftChild());
		} else {
			return blackHeight(n.getLeftChild());
		}
	}

	public int blackHeight() {
		return blackHeight(root);
	}
}
