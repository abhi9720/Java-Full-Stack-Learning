package linkedList;

import java.util.Iterator;
import util.CustomIterator;
import util.Node;

public class SingleLinkedList<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public SingleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return this.size;
	}

	public void insert(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	public void insertAtPosition(T data, int position) {
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<>(data);
		if (position == 0) {
			newNode.next = head;
			head = newNode;
			if (tail == null) {
				tail = newNode;
			}
		} else if (position == size) {
			tail.next = newNode;
			tail = newNode;
		} else {
			Node<T> current = head;
			for (int i = 0; i < position - 1; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
		size++;
	}

	public void delete(T data) {
		if (head == null) {
			throw new NullPointerException();
		}
//		Node<T> deletedNode;
		if (head.data.equals(data)) {
//			deletedNode = head;
			head = head.next;
			if (head == null) {
				tail = null;
			}
			size--;
			return;
		}
		Node<T> current = head;
		while (current.next != null && !current.next.data.equals(data)) {
			current = current.next;
		}
		if (current.next != null) {
			current.next = current.next.next;
			if (current.next == null) {
				tail = current;
			}
			size--;
		}
	}

	public Node<T> deleteAtPosition(int position) {
		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> deletedNode;
		if (position == 0) {
			deletedNode = head;
			head = head.next;
			if (head == null) {
				tail = null;
			}
		} else {
			Node<T> current = head;
			for (int i = 0; i < position - 1; i++) {
				current = current.next;
			}
			deletedNode = current.next;
			current.next = current.next.next; // deleted node here
			if (current.next == null) {
				tail = current;
			}
		}
		size--;
		return deletedNode;
	}

	public Node<T> center() {
		if (head == null) {
			return null;
		}
		Node<T> slow = head;
		Node<T> fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public void sort() {
		if (head == null) {
			return;
		}
		head = mergeSort(head);
		tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
	}

	public Node<T> getMiddle(Node<T> head) {
		if (head == null) {
			return null;
		}
		Node<T> slow = head;
		Node<T> fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public Node<T> mergeSort(Node<T> head) {

		if (head == null || head.next == null) {
			return head;
		}
		Node<T> middle = getMiddle(head);
		Node<T> secondHalf = middle.next;
		middle.next = null;
		Node<T> firstHalf = mergeSort(head);
		secondHalf = mergeSort(secondHalf);
		return mergeSortedList(firstHalf, secondHalf);

	}

	private Node<T> mergeSortedList(Node<T> head1, Node<T> head2) {
		Node<T> dummy = new Node<>(null);
		Node<T> current = dummy;
		while (head1 != null && head2 != null) {
			if (((Comparable<T>) head1.data).compareTo(head2.data) <= 0) {
				current.next = head1;
				head1 = head1.next;
			} else {
				current.next = head2;
				head2 = head2.next;
			}
			current = current.next;
		}
		if (head1 != null) {
			current.next = head1;
		} else {
			current.next = head2;
		}
		Node<T> newHead = dummy.next;
		dummy.next = null;
		return newHead;
	}

	public void reverse() {
		if (head == null || head.next == null) {
			return;
		}
		Node<T> previous = null;
		Node<T> current = head;
		while (current != null) {
			Node<T> next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		head = previous;
	}

	@Override
	public Iterator<T> iterator() {
		return new CustomIterator<>(this.head);
	}

	public void print() { 
		/* Here 'this' is pointing to linkedlist class object,
		  as we have implemented iterable in linkedlist class */	
		for (T ele : this) {
			System.out.print(ele+" ");
		}
		System.out.println();
	}
}
