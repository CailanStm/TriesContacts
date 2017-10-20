import java.util.HashMap;

public class Trie {

	private class Node {
		public char character;
		public HashMap<Character, Node> children;
		public int numPrefixes;
		
		public Node(char character) {
			this.character = character;
			this.children = new HashMap<Character, Node>();
			this.numPrefixes = 0;
		}
	}
	
	private Node root;
	
	public Trie() {
		root = new Node(' ');
	}
	
	public void addString(String s) {
		addStringInternal(s, root);
	}
	
	private void addStringInternal(String s, Node currNode) {
		currNode.numPrefixes++;
		
		if (s.length() == 0) {
			return;
		}
		
		char next = s. charAt(0);
		
		if (currNode.children.get(next) == null) {
			Node newNode = new Node(next);
			currNode.children.put(next, newNode);
			addStringInternal(s.substring(1), newNode);
		} else {
			Node foundNode = currNode.children.get(next);
			addStringInternal(s.substring(1), foundNode);
		}
	}
	
	public int findContacts(String prefix) {
		return findContactsInternal(prefix, root);
	}
	
	private int findContactsInternal(String prefix, Node currNode) {
		if (prefix.length() > 0) {
			char nextChar = prefix.charAt(0);
			Node childNode = currNode.children.get(nextChar);
			if (childNode == null) {
				return 0;
			} else {
				return findContactsInternal(prefix.substring(1), childNode);
			}
		} else {
			return currNode.numPrefixes;
		}
	}
}