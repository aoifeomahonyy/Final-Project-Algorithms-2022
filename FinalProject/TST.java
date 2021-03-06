import java.util.*;

public class TST<Value> {

	private int n;
	private Node<Value> root;

	private static class Node<Value> {
		private char character;
		private Node<Value> left;
		private Node<Value> mid; 
		private Node<Value> right;
		private Value val;

	}

	public TST() {
		root = null;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(String key) {
		if (key == null) {
			return false;
		}
		return get(key) != null;
	}

	public Value get(String key) {
		if (key == null) {
			return null;
		}
		if (key.length() == 0) {
			return null;
		}
		Node<Value> x = get(root, key, 0);
		if (x == null) {
			return null;
		}
		return x.val;
	}

	private Node<Value> get(Node<Value> x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (key.length() == 0) {
			return null;
		}
		char c = key.charAt(d);
		if (c < x.character) {
			return get(x.left, key, d);
		} else if (c > x.character) {
			return get(x.right, key, d);
		} else if (d < key.length() - 1) {
			return get(x.mid, key, d + 1);
		} else {
			return x;
		}
	}

	public void put(String key, Value val) {
		if (!contains(key)) {
			n++;
		} else if (val == null) {
			n--;
		}
		root = put(root, key, val, 0);
	}

	private Node<Value> put(Node<Value> x, String key, Value val, int d) {
		char c = key.charAt(d);
		if (x == null) {
			x = new Node<Value>();
			x.character = c;
		}
		if (c < x.character) {
			x.left = put(x.left, key, val, d);
		} else if (c > x.character) {
			x.right = put(x.right, key, val, d);
		} else if (d < key.length() - 1) {
			x.mid = put(x.mid, key, val, d + 1);
		} else {
			x.val = val;
		}
		return x;
	}

	public Iterable<String> keysWithPrefix(String prefix) {
		if (prefix == null) {
			return null;
		}
		Queue<String> queue = new LinkedList<String>();
		Node<Value> x = get(root, prefix, 0);
		if (x == null) {
			return queue;
		}
		if (x.val != null) {
			queue.add(prefix);
		}
		collect(x.mid, new StringBuilder(prefix), queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
		if (x == null) {
			return;
		}
		collect(x.left, prefix, queue);
		if (x.val != null) {
			queue.add(prefix.toString() + x.character);
		}
		collect(x.mid, prefix.append(x.character), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
	}
}