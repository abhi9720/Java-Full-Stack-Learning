package stack;

import java.util.*;

import util.CustomIterator;
import util.Node;

public class Stack<T extends Comparable<T>> implements Iterable<T> {
	private Node<T> top;
	private int size;

	public Stack() {
		this.top = null;
		this.size = 0;
	}

	public void push(T element) {
		Node<T> newNode = new Node<T>(element);
		if (top == null) {
			top = newNode;
		} else {
			newNode.next = top;
			top = newNode;
		}
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		T poppedData = top.data;
		top = top.next;
		size--;
		return poppedData;
	}

	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return top.data;
	}

	public boolean contains(T element) {
		Node<T> itr = top;
		while (itr != null) {
			if (itr.data.equals(element))
				return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public T center() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		int centerIndex = (size - 1) / 2;
		Node<T> current = top;
		for (int i = 0; i < centerIndex; i++) {
			current = current.next;
		}
		return current.data;
	}

	public void sort(Comparator<? super T> comparator) {
		List<T> list = new ArrayList<>();
		while (!isEmpty()) {
			list.add(pop());
		}
		Collections.sort(list, comparator);
		for (T data : list) {
			push(data);
		}
	}

	public void reverse() {
		List<T> list = new ArrayList<>();
		while (!isEmpty()) {
			list.add(pop());
		}
		for (T data : list) {
			push(data);
		}
	}

	public Iterator<T> iterator() {
		return new CustomIterator<>(top);
	}

	public void print() {
		/* Here 'this' is pointing to Stack object,
		  as we have implemented iterable in Stack Class */	
		for (T data : this) {
			System.out.print(data+" ");
		}
		System.out.println();
	}
}
