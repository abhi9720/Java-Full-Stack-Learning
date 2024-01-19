
import hashtable.HashTable;
import linkedList.SingleLinkedList;
import priorityQueue.PriorityQueue;
import queue.Queue;
import stack.Stack;;

public class Main {

	public static void main(String[] args) {

		System.out.println("---SINGLE LINKEDLIST example 1----------------");
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.insert(23);
		list.insert(1);
		list.insert(2);
		list.insert(-1);
		list.sort();
		list.print();

		System.out.println("\n---SINGLE LINKEDLIST example 2----------------");
		SingleLinkedList<String> lis = new SingleLinkedList<>();
		lis.insert("data3");
		lis.insert("data4");
		lis.insert("data1");
		lis.insert("data2");
		lis.sort();
		lis.print();

		System.out.println("\n----STACK----------------");
		Stack<Integer> stk = new Stack<>();
		stk.push(3);
		stk.push(1);
		stk.push(2);
		stk.print();
		System.out.println("*****After Sorting*****");
		stk.sort((a, b) -> a - b);
		stk.print();
		

		
		System.out.println("\n\n---QUEUE----------------");
		Queue<Integer> que = new Queue<>();
		que.enqueue(1);
		que.enqueue(12);
		que.enqueue(-1);
		que.print();
		System.out.println("*****After Sorting*****");
		que.sort((a, b) -> a - b);
		que.print();

		
		
		System.out.println("\n---PriorityQueue example 1----------------");
		PriorityQueue<student> classroom = new PriorityQueue<>((a, b) -> a.std - b.std);
		classroom.enqueue(new student("Manjeet", 22.5, 12));
		classroom.enqueue(new student("Abhishek", 22, 11));
		System.out.println(classroom.dequeue());
		System.out.println(classroom.dequeue());

		System.out.println("\n---PriorityQueue example 2----------------");
		PriorityQueue<Integer> pq = new PriorityQueue<>();// default max-priorityQueue
		pq.enqueue(1);
		pq.enqueue(2);
		pq.enqueue(3);
		pq.enqueue(4);
		pq.enqueue(5);
		pq.print();
		pq.reverse();
		pq.print();
		System.out.println(pq.center());

		System.out.println("\n---PriorityQueue example 3----------------");

		PriorityQueue<student> pq2 = new PriorityQueue<>((a, b) -> a.std - b.std);
		pq2.enqueue(new student("Abhishek", 12, 24));
		pq2.enqueue(new student("Naman", 11, 22));
		pq2.enqueue(new student("Arnav", 10, 27));
		pq2.print();
		System.out.println("\n***** Reversing *****");
		pq2.reverse();
		pq2.print();

		System.out.println("\n---- PriorityQueue Example 4 : After clonning PriorityQueue -------------------");
		PriorityQueue<Integer> qp2 = new PriorityQueue<>(pq, (a, b) -> b - a);
		qp2.print();
		

		System.out.println("\n---HashTable----------------");
		HashTable<String, Long> contactbook = new HashTable<>();
		contactbook.insert("Abhishek", 9720409597L);
		contactbook.insert("Naman", 8134825670L);
		System.out.println("size : " + contactbook.size());
		System.out.println("for key:Abhishek,  Values: "+contactbook.getValue("Abhishek"));
		for (var ele : contactbook) {
			System.out.println(ele);
		}
	}
}

class student {
	String name;
	double age;
	int std;

	public student(String name, double age, int std) {
		super();
		this.name = name;
		this.age = age;
		this.std = std;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public int getStd() {
		return std;
	}

	public void setStd(int std) {
		this.std = std;
	}

	@Override
	public String toString() {
		return "student [name=" + name + ", age=" + age + ", std=" + std + "]";
	}

}