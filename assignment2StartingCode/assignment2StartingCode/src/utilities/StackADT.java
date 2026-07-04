package utilities;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A generic Last-In-First-Out (LIFO) stack abstract data type.
 * 
 * <p>This interface defines the core operations required for a stack,
 * including insertion, removal, inspection, size queries, conversion
 * to arrays, equality comparison, and iteration support.</p>
 *
 * @param <E> the type of elements stored in the stack
 */
public interface StackADT<E> {

    /**
     * Removes all elements from the stack.
     * 
     * <p>Postcondition: the stack becomes empty.</p>
     */
    void clear();

    /**
     * Pushes an element onto the top of the stack.
     *
     * <p>Precondition: the element to add is valid.</p>
     * <p>Postcondition: the element is inserted at the top of the stack.</p>
     *
     * @param toAdd the element to push onto the stack
     * @throws IllegalArgumentException if {@code toAdd} is invalid
     */
    void push(E toAdd) throws IllegalArgumentException;

    /**
     * Removes the element at the top of the stack.
     *
     * <p>Precondition: the stack is not empty.</p>
     * <p>Postcondition: the top element is removed.</p>
     *
     * @throws EmptyStackException if the stack is empty
     */
    void pop() throws EmptyStackException;

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * <p>Precondition: the stack is not empty.</p>
     * <p>Postcondition: the top element is returned unchanged.</p>
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    E peek() throws EmptyStackException;

    /**
     * Returns the number of elements currently stored in the stack.
     *
     * <p>Postcondition: the size of the stack is returned.</p>
     *
     * @return the number of elements in the stack
     */
    int size();

    /**
     * Determines whether the stack contains no elements.
     *
     * <p>Postcondition: returns {@code true} if the stack is empty,
     * {@code false} otherwise.</p>
     *
     * @return {@code true} if the stack is empty; {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns an array containing all elements in the stack.
     *
     * <p>The returned array contains elements in top-to-bottom order,
     * where index {@code 0} corresponds to the top of the stack.</p>
     *
     * @return an array containing all stack elements
     */
    Object[] toArray();

    /**
     * Stores the stack's elements into the provided array.
     *
     * <p>If the provided array is large enough, elements are stored in it.
     * Otherwise, a new array of the same runtime type is created.</p>
     *
     * <p>Elements are stored in top-to-bottom order, where index {@code 0}
     * corresponds to the top of the stack.</p>
     *
     * @param toHold the array into which elements are to be stored
     * @return the array containing the stack's elements
     * @throws NullPointerException if {@code toHold} is {@code null}
     */
    E[] toArray(E[] toHold) throws NullPointerException;

    /**
     * Compares this stack to another object for equality.
     *
     * <p>Two stacks are considered equal if they contain the same number
     * of elements and each corresponding element is equal according to
     * {@code equals()}.</p>
     *
     * @param o the object to compare with
     * @return {@code true} if the stacks are equal; {@code false} otherwise
     */
    boolean equals(Object o);

    /**
     * Returns an iterator over the elements in the stack.
     *
     * <p>The iterator traverses the stack from top to bottom.</p>
     *
     * @return an iterator over the stack's elements
     */
    Iterator<E> iterator();
    
    /**
     * Checks whether the stack contains the specified element.
     *
     * <p>The comparison is performed using {@code equals()}.</p>
     *
     * @param element the element to search for
     * @return {@code true} if the element exists in the stack;
     *         {@code false} otherwise
     */
    boolean contains(E element);
}
