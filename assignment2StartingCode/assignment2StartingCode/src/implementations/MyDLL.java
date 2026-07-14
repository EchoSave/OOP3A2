package implementations;
 
import java.util.NoSuchElementException;

import utilities.*;
 
/**
 * Doubly linked list implementation of ListADT&lt;E&gt;.
 *
 * @param <E> The type of elements this list holds.
 */
public class MyDLL<E> implements ListADT<E> {
 
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
 
	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
	}
 
	/**
	 * Returns the number of elements currently stored in the list.
	 *
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
 
	/**
	 * Removes all elements from the list, resetting it to an empty state.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
 
	/**
	 * Inserts the given element at the specified index, shifting any
	 * existing elements at and after that index one position to the right.
	 *
	 * @param index the position to insert the element at
	 * @param toAdd the element to insert
	 * @return true if the element was successfully added
	 * @throws NullPointerException if toAdd is null
	 * @throws IndexOutOfBoundsException if index is negative or greater than size
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// Cannot add a null element
		if (toAdd == null) {
			throw new NullPointerException("Cannot add a null element");
		}
		// Index has to be a valid index (cannot be out of bounds)
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index doesn't exist. Index: " + index + ", Size: " + size);
		}
 
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
 
		// empty list
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else if (index == 0) { // adding first element
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		} else if (index == size) { // adding last element
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		} else { // adding any element in between first and last element
			MyDLLNode<E> current = getNode(index);
			MyDLLNode<E> before = current.getPrev();
			newNode.setNext(current);
			newNode.setPrev(before);
			before.setNext(newNode);
			current.setPrev(newNode);
		}
 
		size++;
		return true;
	}
 
	/**
	 * Appends the given element to the end of the list.
	 *
	 * @param toAdd the element to add
	 * @return true if the element was successfully added
	 * @throws NullPointerException if toAdd is null
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		return add(size, toAdd); // uses method above, but for last element
	}
 
	/**
	 * Appends all elements from the given list to the end of this list,
	 * in the order returned by the given list's toArray() method.
	 *
	 * @param toAdd the list of elements to add
	 * @return true if the elements were successfully added
	 * @throws NullPointerException if toAdd is null
	 */
	@SuppressWarnings("unchecked") // i think this isn't java.util
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		// cannot add null element
		if (toAdd == null) {
			throw new NullPointerException("Cannot add a null list");
		}
		// changes ListADT to an array
		Object[] items = toAdd.toArray();
		for (Object item : items) { // loops and adds the element
			E element = (E) item;
			add(element);
		}
		return true;
	}
 
	/**
	 * Returns the element at the specified index.
	 *
	 * @param index the position of the element to retrieve
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if index is negative or >= size
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// Index has to be a valid index ( Cannot be out of bounds )
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		return getNode(index).getElement();
	}
 
	/**
	 * Removes and returns the element at the specified index.
	 *
	 * @param index the position of the element to remove
	 * @return the element that was removed
	 * @throws IndexOutOfBoundsException if index is negative or >= size
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		MyDLLNode<E> target = getNode(index);
		return unlink(target);
	}
 
	/**
	 * Removes the first occurrence of the given element from the list, if present.
	 *
	 * @param toRemove the element to remove
	 * @return the removed element, or null if it was not found in the list
	 * @throws NullPointerException if toRemove is null
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) {
			throw new NullPointerException("Cannot remove a null element");
		}
		MyDLLNode<E> current = head;
		while (current != null) { // loops through each element in the list
			if (current.getElement().equals(toRemove)) {
				return unlink(current);
			}
			current = current.getNext(); // goes to the next element
		}
		return null;
	}
 
	/**
	 * Replaces the element at the specified index with the given element.
	 *
	 * @param index the position of the element to replace
	 * @param toChange the new element to store at that position
	 * @return the element previously stored at that position
	 * @throws NullPointerException if toChange is null
	 * @throws IndexOutOfBoundsException if index is negative or >= size
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
			throw new NullPointerException("Cannot set a null element");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		MyDLLNode<E> node = getNode(index); // gets the node of the index
		E old = node.getElement(); // sets it to a variable for return statement
		node.setElement(toChange); // replaces it
		return old;
	}
 
	/**
	 * Checks whether the list contains no elements.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
 
	/**
	 * Checks whether the list contains the given element.
	 *
	 * @param toFind the element to search for
	 * @return true if the element is found, false otherwise
	 * @throws NullPointerException if toFind is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for a null element");
		}
		MyDLLNode<E> current = head;
		while (current != null) {
			if (current.getElement().equals(toFind)) {
				return true;
			}
			current = current.getNext(); // goes to next element
		}
		return false;
	}
 
	/**
	 * Returns an array containing all elements in the list in order from
	 * head to tail. If the given array is large enough, it is populated
	 * and returned (with a trailing null if it has extra capacity);
	 * otherwise a new array of the same runtime type is allocated.
	 *
	 * @param toHold the array to populate, if it is large enough
	 * @return an array containing all elements of the list in order
	 * @throws NullPointerException if toHold is null
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException("Array cannot be null");
		}
		if (toHold.length < size) {
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		MyDLLNode<E> current = head;
		int i = 0;
		while (current != null) {
			toHold[i++] = current.getElement();
			current = current.getNext();
		}
		if (toHold.length > size) {
			toHold[size] = null;
		}
		return toHold;
	}
 
	/**
	 * Returns an array containing all elements in the list in order from
	 * head to tail.
	 *
	 * @return an Object array containing all elements of the list in order
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		MyDLLNode<E> current = head;
		int i = 0;
		while (current != null) {
			result[i++] = current.getElement();
			current = current.getNext();
		}
		return result;
	}
 
	/**
	 * Returns an iterator over the elements in this list, backed by a
	 * snapshot taken at the time this method is called.
	 *
	 * @return an Iterator over the elements of the list
	 */
	@Override
	public Iterator<E> iterator() {
		return new DLLIterator();
	}
 
	/**
	 * Gets node of element at set index
	 */
	private MyDLLNode<E> getNode(int index) {
		MyDLLNode<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}
 
	/**
	 * Removes the given node from the list by relinking its
	 * neighbours, and returns the element that was stored in it.
	 */
	private E unlink(MyDLLNode<E> node) {
		MyDLLNode<E> before = node.getPrev();
		MyDLLNode<E> after = node.getNext();
 
		if (before == null) { // if removed node was the head
			head = after;
		} else { // else set node before to whats after the removed node
			before.setNext(after);
		}
 
		if (after == null) { // if removed node was the tail
			tail = before;
		} else { // else setPrev of the node after to whatever was before
			after.setPrev(before);
		}
 
		node.setNext(null);
		node.setPrev(null);
		size--;
		return node.getElement();
	}
 
	/** 
	 * Iterator that walks over a snapshot (deep copy) of the list's
	 * elements at the time iterator() was called, per the Iterator
	 * interface contract - it does NOT reflect later modifications
	 * to the live list.
	 */
	private class DLLIterator implements Iterator<E> {
 
		private final Object[] snapshot;
		private int cursorIndex;
 
		public DLLIterator() {
			this.snapshot = toArray();
			this.cursorIndex = 0;
		}
 
		@Override
		public boolean hasNext() {
			return cursorIndex < snapshot.length;
		}
 
		@SuppressWarnings("unchecked")
		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements in the list");
			}
			E element = (E) snapshot[cursorIndex];
			cursorIndex++;
			return element;
		}
	}
}