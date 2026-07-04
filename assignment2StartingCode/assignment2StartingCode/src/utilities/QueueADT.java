package utilities;

import exceptions.EmptyQueueException;
 
/**
* Author: Irteza Hassan, Joshua Wijaya
*
* Description: A generic Queue Abstract Data Type (ADT) for Assignment 2 in 
* CPRG304 class at SAIT. This interface defines the basic contract 
* for queue operations.
*
* @param <E> type of elements stored in the queue
*/
public interface QueueADT<E>
{
	/**
	 * Adds an element to the rear of the queue.
	 *
	 * Pre-condition:
	 * - The element cannot be null.
	 *
	 * Post-condition:
	 * - The element is added to the rear of the queue.
	 *
	 * @param element the element to add
	 * @throws NullPointerException if the element is null
	 */

	public void enqueue(E element) throws NullPointerException;
 
	/**
	 * Removes and returns the element at the front of the queue.
	 *
	 * Pre-condition:
	 * - The queue must not be empty.
	 *
	 * Post-condition:
	 * - The front element is removed from the queue.
	 *
	 * @return the removed front element
	 * @throws EmptyQueueException if the queue is empty
	 */

	public E dequeue() throws EmptyQueueException;
 
	/**
	 * Returns the element at the front of the queue without removing it.
	 *
	 * Pre-condition:
	 * - The queue must not be empty.
	 *
	 * Post-condition:
	 * - The queue remains unchanged.
	 *
	 * @return the front element
	 * @throws EmptyQueueException if the queue is empty
	 */

	public E peek() throws EmptyQueueException;
 
	/**
	 * Removes all elements from the queue.
	 *
	 * Pre-condition:
	 * - The queue may be empty.
	 *
	 * Post-condition:
	 * - The queue becomes empty.
	 */

	public void dequeueAll();

    /**
     * Returns number of element of current queue
     * 
     * Pre-condition:
     * - None
     * 
     * Post-condition:
     * - The Queue remains unchanged
     * 
     * @return the number of elements in the queue
     */
    public int size();

    /**
	 * Checks whether the queue is empty.
	 *
	 * Pre-condition:
	 * - None.
	 *
	 * Post-condition:
	 * - The queue remains unchanged.
	 *
	 * @return true if the queue contains no elements, false otherwise
	 */
    public boolean isEmpty();

    /**
	 * Compares this queue to the specified object for equality. Two queues
	 * are equal if they are both QueueADT objects containing the same
	 * elements in the same order.
	 *
	 * Pre-condition:
	 * - None.
	 *
	 * Post-condition:
	 * - The queue remains unchanged.
	 *
	 * @param obj the object to compare against
	 * @return true if the queues are equal, false otherwise
	 */
    public boolean equals(Object obj);

    /**
	 * Returns an iterator over the elements in this queue, in order from
	 * front to rear.
	 *
	 * Pre-condition:
	 * - None.
	 *
	 * Post-condition:
	 * - The queue remains unchanged.
	 *
	 * @return an iterator over the elements in the queue
	 */
    public Iterator<E> iterator();
}
 