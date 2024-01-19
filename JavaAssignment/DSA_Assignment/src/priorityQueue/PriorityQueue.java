package priorityQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<T> implements Iterable<T> {

	private static class DefaultComparator<T> implements Comparator<T> {
		@SuppressWarnings("unchecked")
		@Override
		public int compare(T o1, T o2) {
			if (o1 instanceof Comparable && o2 instanceof Comparable) {
				return ((Comparable<T>) o1).compareTo(o2);
			} else {
				throw new UnsupportedOperationException("Cannot compare elements of type " + o1.getClass().getName());
			}
		}
	}

	private ArrayList<T> data;
	private Comparator<T> comparator;

	public PriorityQueue() {
		this.data = new ArrayList<T>();
		this.comparator = new DefaultComparator<T>();
	}

	public PriorityQueue(Comparator<T> comparator) {
		this.data = new ArrayList<T>();
		this.comparator = comparator;
	}
	
	
	
	public PriorityQueue(PriorityQueue<T> orginal , Comparator<T> comparator) {
		buildCopy(orginal.data, comparator);
	}
	
	private void  buildCopy(ArrayList<T> data , Comparator<T> comparator) {
		this.data =  new ArrayList<>();
		this.comparator =  comparator;
		for(T ele : data) {
			this.enqueue(ele);
		}
		
	}

	public void enqueue(T item) {
		data.add(item);
		bubbleUp(data.size() - 1);
	}

	private void bubbleUp(int index) {
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			T parentItem = data.get(parentIndex);
			T currentItem = data.get(index);
			int compareResult = compareData(currentItem, parentItem);
			if (compareResult > 0) {
				swap(index, parentIndex);
				index = parentIndex;
			} else {
				break;
			}
		}
	}

	public T dequeue() {
		if (data.isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = data.get(0);
		int lastIndex = data.size() - 1;
		data.set(0, data.get(lastIndex));
		data.remove(lastIndex);
		bubbleDown(0);
		return item;
	}

	private void bubbleDown(int index) {
		int size = data.size();
		while (index < size) {
			int leftChildIndex = 2 * index + 1;
			int rightChildIndex = 2 * index + 2;
			int maxIndex = index;
			if (leftChildIndex < size) {
				T leftChild = data.get(leftChildIndex);
				T currentItem = data.get(index);
				int compareResult = compareData(leftChild, currentItem);
				if (compareResult > 0) {
					maxIndex = leftChildIndex;
				}
			}
			if (rightChildIndex < size) {
				T rightChild = data.get(rightChildIndex);
				T maxItem = data.get(maxIndex);
				int compareResult = compareData(rightChild, maxItem);
				if (compareResult > 0) {
					maxIndex = rightChildIndex;
				}
			}
			if (maxIndex != index) {
				swap(index, maxIndex);
				index = maxIndex;
			} else {
				break;
			}
		}
	}

	public T peek() {
		if (data.isEmpty()) {
			throw new NoSuchElementException();
		}
		return data.get(0);
	}

	public boolean contains(T val) {
		for (T ele : this.data) {
			if (ele.equals(val))
				return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public int size() {
		return this.data.size();
	}

	public T center() {
		int mid = (this.size() + 1) / 2 - 1;
		System.out.println("midindex : " + mid);
		for (int i = 0; i < this.size(); i++) {
			if (i == mid)
				return data.get(mid);
		}
		return null;
	}

	public void reverse() {
		int lastIndex = data.size() - 1;
		for (int i = 0; i < data.size() / 2; i++) {
			T temp = data.get(i);
			data.set(i, data.get(lastIndex - i));
			data.set(lastIndex - i, temp);
		}
		bubbleDown(0);
	}

	@Override
	public Iterator<T> iterator() {
		return data.iterator();
	}

	public void print() {
		for (T val : this.data) {
			System.out.print(val + " ");
		}
		System.out.println();
	}

	private int compareData(T ele1, T ele2) {
		return comparator.compare(ele1, ele2);
	}

	private void swap(int idx1, int idx2) {
		T temp = data.get(idx1);
		data.set(idx1, data.get(idx2));
		data.set(idx2, temp);
	}

}
