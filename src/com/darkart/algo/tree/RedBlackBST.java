package com.darkart.algo.tree;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	public boolean contains(Key key) {
		return get(root, key) != null;
	}
	
	private boolean contains(Node x, Key key) {
		return get(x, key) != null;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else return x.val;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, RED, 1);
		
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = put(x.left, key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		
		if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
		if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
		if (isRed(x.left) && isRed(x.right)) flipColors(x);
		x.N = 1 + size(x.left) + size(x.right);
		
		return x;
	}

	public void delete(Key key) {
		if (!contains(key)) {
            System.err.println("symbol table does not contain " + key);
            return;
        }
		
		if (!isRed(root.left)) root.color = RED;
		
		root = delete(root, key);
		
		if (root != null) root.color = BLACK;
	}
	
	private Node delete(Node h, Key key) {
		if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
	}

	public void deleteMin() {
		if (root == null) throw new RuntimeException("no more item to delete!");
		
		if (!isRed(root.left)) root.color = RED;
		
		root = deleteMin(root);
		
		if (root != null) root.color = BLACK;
	}
	
	private Node deleteMin(Node x) {	//key point is to ensure current node is not a 2-node -> remove a red node is straight forward
		if (x.left == null) return null;	// x.right must be null, otherwise no black balance
		
		if (!isRed(x.left) && !isRed(x.left.left)) {	//left child is a 2-node. 2 cases: sibling is a 2-node; sibling is a 3-node
			x = moveRedLeft(x);
		}
		
		x.left = deleteMin(x.left);
		
		return balance(x);
	}

	public void deleteMax() {
		if (root == null) throw new RuntimeException("no more item to delete!");
		
		if (!isRed(root.left)) root.color = RED;
		
		root = deleteMax(root);
		
		if (root != null) root.color = BLACK;
	}
	
	private Node deleteMax(Node x) {
		if (isRed(x.left)) x = rotateRight(x);
		
		if (x.right == null) return null;
		
		if (!isRed(x.right) && !isRed(x.right.left)) {
			x = moveRedRight(x);
		}
		
		x.right = deleteMax(x.right);
		
		return balance(x);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		return x == null ? 0 : x.N;
	}
	
	private boolean isRed(Node x) {
		return x == null ? BLACK : (x.color == RED);
	}
	
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color= !h.right.color;
	}
	
	private Node moveRedLeft(Node x) {
		flipColors(x);
		if (isRed(x.right.left)) {
			x.right = rotateRight(x.right);
			x = rotateLeft(x);
		}
		return x;
	}
	
	private Node moveRedRight(Node x) {
		flipColors(x);
		if (isRed(x.left.left)) {
			x = rotateRight(x);
		}
		return x;
	}
	
	private Node balance(Node h) {
		if (isRed(h.right))	h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right)) flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	/**
	 * Utilities
	 */
	public int height() {
		return height(root);
	}
	
	private int height(Node x) {
		return x == null ? 0 : (1 + Math.max(height(x.left), height(x.right)));
	}

	public Key min() {
		return root == null ? null : min(root).key;
	}
	
	private Node min(Node x) {
		return x.left == null ? x : min(x.left);
	}

	public Key max() {
		return root == null ? null : max(root).key;
	}
	
	private Node max(Node x) {
		return x.right == null ? x : max(x.right);
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		return x == null ? null : x.key;
	}
	
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		return t == null ? x : t;
	}

	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		return x == null ? null : x.key;
	}
	
	private Node ceiling(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0) return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		return t == null ? x : t;
	}

	public Key select(int index) {
		if (index < 0 || index >= size()) return null;
		return select(root, index).key;
	}
	
	private Node select(Node x, int index) {
		int l = size(x.left);
		if (index == l) return x;
		else if (index < l) return select(x.left, index);
		else return select(x.right, index - l - 1);
	}

	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node x, Key key) {
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return size(x.left);
		else if (cmp < 0) return rank(x.left, key);
		else return 1 + size(x.left) + rank(x.right, key);
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	private Iterable<Key> keys(Key min, Key max) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, min, max);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key min, Key max) {
		if (x != null) {
			int cmpMin = min.compareTo(x.key);
			int cmpMax = max.compareTo(x.key);
			if (cmpMin < 0) keys(x.left, queue, min, max);
			if (cmpMin <= 0 && cmpMax >= 0) queue.enqueue(x.key);
			if (cmpMax > 0) keys(x.right, queue, min, max);
		}
	}
	
	public int size(Key min, Key max) {
		if (min.compareTo(max) > 0) return 0;
		if (contains(max)) return rank(max) - rank(min) + 1;
		else return rank(max) - rank(min);
	}
	
	private class Node {
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;
		
		Node(Key key, Value val, boolean color, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
}
