import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int size;
	
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}
	
	public Deque() {
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node oldFirst = first;
		Node temp = new Node();
		temp.item = item;
		temp.next = oldFirst;
		first = temp;
		if (oldFirst == null) {
			last = temp;
		} else {
			oldFirst.previous = temp;
		}
		size++;
		
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node oldLast = last;
		Node temp = new Node();
		temp.item = item;
		temp.previous = oldLast;
		last = temp;
		if (oldLast == null) {
			first = temp;
		} else {
			oldLast.next = temp;
		}
		size++;
	}
	
	public Item removeFirst() {
		if (first == null) {
			throw new java.util.NoSuchElementException();
		} else if (size() == 1) {
			Node toReturn = first;
			first = null;
			last = null;
			size--;
			return toReturn.item;
		} else {
			Node toReturn = first;
			first = toReturn.next;
			first.previous = null;
			size--;
			return toReturn.item;		
		}
	}
	
	public Item removeLast() {
		if (last == null) {
			throw new java.util.NoSuchElementException();
		} else if (size() == 1) {
			Node toReturn = last;
			first = null;
			last = null;
			size--;
			return toReturn.item;
		} else {
			Node toReturn = last;
			last = toReturn.previous;
			last.next = null;
			size--;
			return toReturn.item;
		}
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext())  {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	public static void main(String[] args) {
		Deque<Integer> Mark = new Deque<Integer>();
		for (int i = 0; i < 10; i++) {
			Mark.addFirst(i);
		}
		for (int j = 0; j < 10; j++) {
			System.out.println(Mark.removeLast());
		}
		for (int m = 0; m < 10; m++) {
			Mark.addLast(m);
		}
		Iterator itr = Mark.iterator();
		while (itr.hasNext()) {
			Object element = itr.next();
			System.out.println(element);
		}
	}

}
