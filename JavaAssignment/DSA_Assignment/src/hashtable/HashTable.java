package hashtable;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> implements Iterable<Entry<K, V>> {
	private LinkedList<Entry<K, V>>[] table;
	private int size;

	public HashTable() {
		table = new LinkedList[31];
		size = 0;
	}

	public HashTable(int capacity) {
		table = new LinkedList[capacity];
		size = 0;
	}

	private int hash(K key) {
		return Math.abs(key.hashCode() % table.length);
	}

	public void insert(K key, V value) {
		int index = hash(key);
		if (table[index] == null) {
			table[index] = new LinkedList<>();
		}
		for (Entry<K, V> node : table[index]) {
			if (node.key.equals(key)) {
				node.value = value;
				return;
			}
		}
		table[index].add(new Entry<K, V>(key, value));
		size++;
	}

	public void delete(K key) {
		int index = hash(key);
		if (table[index] == null) {
			return;
		}
		for (Entry<K, V> node : table[index]) {
			if (node.key.equals(key)) {
				table[index].remove(node);
				size--;
				return;
			}
		}
	}

	public boolean contains(K key) {
		int index = hash(key);
		if (table[index] == null) {
			return false;
		}
		for (Entry<K, V> node : table[index]) {
			if (node.key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public V getValue(K key) {
		int index = hash(key);
		if (table[index] == null) {
			return null;
		}
		for (Entry<K, V> node : table[index]) {
			if (node.key.equals(key)) {
				return node.value;
			}
		}
		return null;
	}

	public int size() {
		return size;
	}

	public Iterator<Entry<K, V>> iterator() {
		 LinkedList<Entry<K, V>> nodes = new LinkedList<>();
	        for (LinkedList<Entry<K, V>> list : table) {
	            if (list != null) {
	                nodes.addAll(list);
	            }
	        }
	        return nodes.iterator();
	}

	
	public void traverse() {
		for (LinkedList<Entry<K, V>> list : table) {
			if (list != null) {
				for (Entry<K, V> node : list) {
					System.out.println(node.key + ": " + node.value);
				}
			}
		}
	}
}
