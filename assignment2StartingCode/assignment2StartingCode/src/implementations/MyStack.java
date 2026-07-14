package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.StackADT;

/**
 * An array-based implementation of a LIFO stack using MyArrayList.
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStack<E> implements StackADT<E> {

	private MyArrayList<E> list;

	/**
	 * Initializes an empty stack.
	 */
	public MyStack() {
		list = new MyArrayList<>();
	}

	// Add an element to the top of the stack
	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot push null values onto the stack");
		}
		list.add(toAdd);
	}

	// Get and remove the top element of the stack
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
	}

	// Get the top element of the stack without removing it
	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.get(list.size() - 1);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(list.size() - 1 - i);
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) {
			throw new NullPointerException("The provided array cannot be null");
		}

		if (holder.length < list.size()) {
			holder = (E[]) java.lang.reflect.Array.newInstance(
					holder.getClass().getComponentType(), list.size());
		}

		for (int i = 0; i < list.size(); i++) {
			holder[i] = list.get(list.size() - 1 - i);
		}

		if (holder.length > list.size()) {
			holder[list.size()] = null;
		}

		return holder;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Search target cannot be null");
		}
		return list.contains(toFind);
	}

	// Search for an element in the stack and return its 1-based position from the
	// top
	@Override
	public int search(E toFind) {
		if (toFind == null) {
			return -1;
		}

		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i).equals(toFind)) {
				return list.size() - i;
			}
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return new StackIterator();
	}

	// Check if this stack is equal to another stack
	@Override
	public boolean equals(StackADT<E> that) {
		if (that == null || this.size() != that.size()) {
			return false;
		}

		// Create the copy of the other stack to compare
		Object[] thisArr = this.toArray();
		Object[] thatArr = that.toArray();

		for (int i = 0; i < thisArr.length; i++) {
			if (!thisArr[i].equals(thatArr[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean stackOverflow() {
		return false;
	}

	/**
	 * Inner class implementing Iterator to traverse the stack in LIFO order (top to
	 * bottom).
	 */
	private class StackIterator implements Iterator<E> {
		// The current index for the iterator, starting from the top of the stack
		private int current = list.size() - 1;

		@Override
		public boolean hasNext() {
			return current >= 0;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements in the stack iterator");
			}
			return list.get(current--);
		}
	}
}