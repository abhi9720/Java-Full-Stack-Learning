package queue;

import java.util.*;
import util.CustomIterator;
import util.Node;

public class Queue<T> implements Iterable<T> {

	private Node<T> front; // Pointer to the front of the queue
	private Node<T> rear; // Pointer to the rear of the queue
	private int size; // The number of elements in the queue

	// Constructor to initialize the queue
	public Queue() {
		front = null;
		rear = null;
		size = 0;
	}

	// Add an element to the rear of the queue
	public void enqueue(T element) {
		Node<T> newNode = new Node<>(element);
		if (isEmpty()) {
			front = newNode;
		} else {
			rear.next = newNode;
		}
		rear = newNode;
		size++;
	}

	// Remove and return the element at the front of the queue
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		T element = front.data;
		front = front.next;
		size--;
		if (isEmpty()) {
			rear = null;
		}
		return element;
	}

	// Return the element at the front of the queue without removing it
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		return front.data;
	}

	// Check if the queue contains a given element
	public boolean contains(T element) {
		for (T item : this) {
			if (item.equals(element)) {
				return true;
			}
		}
		return false;
	}

	// Return the number of elements in the queue
	public int size() {
		return size;
	}

	// Return the element in the center of the queue
	public T center() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		int mid = size / 2;
		Node<T> curr = front;
		for (int i = 0; i < mid; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	// Sort the elements in the queue in ascending order
	public void sort(Comparator<? super T> comparator) {
		List<T> list = new ArrayList<T>();
		while (!isEmpty()) {
			list.add(dequeue());
		}
		Collections.sort(list, comparator);
		for (T item : list) {
			enqueue(item);
		}
	}

	// Reverse the order of the elements in the queue
	public void reverse() {
		Node<T> prev = null;
		Node<T> curr = front;
		Node<T> next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		rear = front;
		front = prev;
	}

	// Return an iterator over the elements in the queue
	public Iterator<T> iterator() {
		return new CustomIterator<>(front);
	}

	// Return boolean based on queue is empty or not
	public boolean isEmpty() {
		return size == 0;
	}

	// Print the queue
	public void print() {
		/* Here 'this' is pointing to Queue object,
		  as we have implemented iterable in Queue Class */	
		for (T ele : this) {
			System.out.print(ele+" ");
		}
		System.out.println();
	}

}