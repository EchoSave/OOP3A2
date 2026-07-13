package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> {

    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[10];
        size = 0;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = new Object[10];
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null values");

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Stay within 0 and " + size);

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null values");

        ensureCapacity();
        data[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null values");

        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Stay within 0 and " + size);

        return (E) data[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Stay within 0 and " + size);

        E removed = (E) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public E remove(E toRemove) {
        if (toRemove == null)
            throw new NullPointerException("Cannot add null values");

        int index = indexOf(toRemove);
        if (index == -1)
            return null;

        return remove(index);
    }

    @Override
    public E set(int index, E toChange) {
        if (toChange == null)
            throw new NullPointerException("Cannot add null values");

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Stay within 0 and " + size);

        E prev = get(index);
        data[index] = toChange;
        return prev;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) {
        if (toFind == null)
            throw new NullPointerException("Cannot add null values");

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind))
                return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("Array cannot be null");
        }

        if (toHold.length < size) {
            return (E[]) java.util.Arrays.copyOf(data, size, toHold.getClass());
        }

        System.arraycopy(data, 0, toHold, 0, size);
        if (toHold.length > size) {
            toHold[size] = null;
        }

        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = data[i];
        }
        return arr;
    }

    private int indexOf(E toFind) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind))
                return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("No more elements");
            return (E) data[cursor++];
        }
    }
}
