import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] array;
	private int size;
	
	public RandomizedQueue() {
		array = (Item[]) new Object[2];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	private void resize(int capacity) {
		assert capacity >= size;
		Item[] newArray = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	public void enqueue(Item item) {
		if (size == array.length) {
			resize(2*array.length);
		}
		array[size++] = item;
	}
	
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(size);
		Item returnItem = array[index];
		array[index] = null;
		if (size > 0 && size == array.length/4) {
			resize(array.length/2);
		}
		size--;
		return returnItem;
	}
	
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(size);
		return array[index];
	}
	
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item> {
		private int i;
		
		public ReverseArrayIterator() {
			i = size - 1;
		}
		
		public boolean hasNext() {
			return i >=0;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[i--];
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
