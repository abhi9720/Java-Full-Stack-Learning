package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIterator<T> implements Iterator<T> {

	private Node<T> current;

	public CustomIterator(Node<T> head) {
		current = head;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T data = current.data;
		current = current.next;
		return data;
	}
}