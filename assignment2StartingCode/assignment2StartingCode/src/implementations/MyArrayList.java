package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E>, Iterator<E> {

    private Object[] data;
    private int size;
    private int iterIndex;

    public MyArrayList() {
        data = new Object[10];   // default capacity
        size = 0;
        iterIndex = 0;
    }

    // -------------------------
    // Iterator methods
    // -------------------------

    @Override
    public boolean hasNext() {
        return iterIndex < size;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return (E) data[iterIndex++];
    }

    @Override
    public Iterator<E> iterator() {
        iterIndex = 0;
        return this;
    }

    // -------------------------
    // Core List methods
    // -------------------------

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    // -------------------------
    // Add methods
    // -------------------------

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null");
        }

        ensureCapacity();
        data[size] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E toAdd)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toAdd == null) {
            throw new NullPointerException("Cannot add null");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        ensureCapacity();

        // shift right
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = toAdd;
        size++;
        return true;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newArr = new Object[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newArr[i] = data[i];
            }
            data = newArr;
        }
    }

    // -------------------------
    // get / set
    // -------------------------

    @SuppressWarnings("unchecked")
	@Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return (E) data[index];
    }

    @Override
    public E set(int index, E toChange)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toChange == null) {
            throw new NullPointerException("Cannot set null");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        @SuppressWarnings("unchecked")
		E oldValue = (E) data[index];
        data[index] = toChange;
        return oldValue;
    }

    // -------------------------
    // remove methods
    // -------------------------

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        E removed = (E) data[index];

        // shift left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null");
        }

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toRemove)) {
                return remove(i);
            }
        }

        return null; // not found
    }

    // -------------------------
    // contains
    // -------------------------

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null");
        }

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind)) {
                return true;
            }
        }
        return false;
    }

    // -------------------------
    // addAll
    // -------------------------

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add from a null list");
        }

        boolean addedSomething = false;

        for (int i = 0; i < toAdd.size(); i++) {
            E element = toAdd.get(i);
            this.add(element);
            addedSomething = true;
        }

        return addedSomething;
    }

    // -------------------------
    // toArray methods
    // -------------------------

    @Override
    public Object[] toArray() {
        Object[] newArr = new Object[size];
        for (int i = 0; i < size; i++) {
            newArr[i] = data[i];
        }
        return newArr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("Array cannot be null");
        }

        if (toHold.length < size) {
            // Create a new array of the same runtime type
            return (E[]) java.util.Arrays.copyOf(data, size, toHold.getClass());
        }

        // Copy into provided array
        System.arraycopy(data, 0, toHold, 0, size);

        // Null-terminate if array is larger
        if (toHold.length > size) {
            toHold[size] = null;
        }

        return toHold;
    }
}
