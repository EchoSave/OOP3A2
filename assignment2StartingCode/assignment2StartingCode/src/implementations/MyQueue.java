package implementations;

import java.util.NoSuchElementException;

import exceptions.EmptyQueueException;
import utilities.*;

public class MyQueue implements QueueADT, Iterator{

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(Object toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object peek() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dequeueAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int search(Object toFind) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(QueueADT that) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] holder) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
