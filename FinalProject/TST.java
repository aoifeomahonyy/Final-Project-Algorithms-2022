import java.util.*;

public class TST<Value> {

	private int n;
	private tstSearchNode<Value> root;

	private static class tstSearchNode<Value> {
		private char character;
		private tstSearchNode<Value> left;
		private tstSearchNode<Value> mid;
		private tstSearchNode<Value> right;
		private Value value;

	}

	public TST() {
		root = null;
	}

	public int size() {
		return n;
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
		tstSearchNode<Value> x = get(root, key, 0);
		if (x == null) {
			return null;
		}
		
		return x.value;
	}

	private tstSearchNode<Value> get(tstSearchNode<Value> x, String key, int y) {
		if (x == null) {
			return null;
		}
		if (key.length() == 0) {
			return null;
		}
		char c = key.charAt(y);
		
		if (c < x.character) {
			return get(x.left, key, y);
			
		} else if (c > x.character) {
			return get(x.right, key, y);
			
		} else if (y < key.length() - 1) {
			return get(x.mid, key, y + 1);
			
		} else {
			return x;
		}
	}

	public void put(String key, Value val) {
		
		if (!contains(key)) {
			n++;
		} 
		root = put(root, key, val, 0);
	}

	private tstSearchNode<Value> put(tstSearchNode<Value> x, String key, Value val, int y) {
		
		char c = key.charAt(y);
		if (x == null) {
			x = new tstSearchNode<Value>();
			x.character = c;
		}
		
		if (c < x.character) {
			x.left = put(x.left, key, val, y);
			
		} else if (c > x.character) {
			x.right = put(x.right, key, val, y);
			
		} else if (y < key.length() - 1) {
			x.mid = put(x.mid, key, val, y + 1);
			
		} else {
			x.value = val;
			
		}
		return x;
	}

	public Iterable<String> keysWithPrefix(String prefix) {
		
		if (prefix == null) {
			return null;
		}
		Queue<String> queue = new LinkedList<String>();
		tstSearchNode<Value> x = get(root, prefix, 0);
		if (x == null) {
			return queue;
		}
		if (x.value != null) {
			queue.add(prefix);
		}
		collect(x.mid, new StringBuilder(prefix), queue);
		return queue;
	}

	private void collect(tstSearchNode<Value> x, StringBuilder prefix, Queue<String> queue) {
		
		if (x == null) {
			return;
		}
		
		collect(x.left, prefix, queue);
		
		if (x.value != null) {
			queue.add(prefix.toString() + x.character);	
		}
		collect(x.mid, prefix.append(x.character), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
	}
}