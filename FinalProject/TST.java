import java.util.*;

public class TST<Value> {

	private int n;
	private tstSearchNode<String> root;

	private static class tstSearchNode<String> {
		private char character;
		private tstSearchNode<String> left;
		private tstSearchNode<String> mid;
		private tstSearchNode<String> right;
		private String value;

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

	public String get(String key) {
		if (key == null) {
			return null;
		}
		if (key.length() == 0) {
			return null;
		}
		tstSearchNode<String> x = get(root, key, 0);
		if (x == null) {
			return null;
		}
		
		return x.value;
	}

	private tstSearchNode<String> get(tstSearchNode<String> x, String key, int y) {
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

	public void put(String key, String val) {
		
		if (!contains(key)) {
			n++;
		} 
		root = put(root, key, val, 0);
	}

	private tstSearchNode<String> put(tstSearchNode<String> x, String key, String val, int y) {
		
		char c = key.charAt(y);
		if (x == null) {
			x = new tstSearchNode<String>();
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

	public ArrayList<String> keysWithPrefix(String prefix) {
		
		if (prefix == null) {
			return null;
		}
		ArrayList<String> list = new ArrayList<String>();
		tstSearchNode<String> x = get(root, prefix, 0);
		if (x == null) {
			return list;
		}
		if (x.value != null) {
			list.add(prefix);
		}
		collect(x.mid, new StringBuilder(prefix), list);
		return list;
	}

	private void collect(tstSearchNode<String> x, StringBuilder prefix, ArrayList<String> list) {
		
		if (x == null) {
			return;
		}
		
		collect(x.left, prefix, list);
		
		if (x.value != null) {
			list.add(prefix.toString() + x.character);	
		}
		collect(x.mid, prefix.append(x.character), list);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, list);
	}
}