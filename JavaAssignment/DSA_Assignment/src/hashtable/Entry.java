package hashtable;

public class Entry<K,V> {
	 K key;
	 V value;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [key=" + key + ", value=" + value + "]";
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	
}