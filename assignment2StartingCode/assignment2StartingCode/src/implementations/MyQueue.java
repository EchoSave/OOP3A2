package implementations;
 
import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;
 
/**
* Queue implementation that uses MyDLL as its underlying data structure.
* The queue follows the First-In, First-Out (FIFO) rule.
*
* @param <E> the type of elements stored in the queue
*/
public class MyQueue<E> implements QueueADT<E>
{
	private MyDLL<E> queue;
 
	/**
	 * Creates an empty queue.
	 */
	public MyQueue()
	{
		queue = new MyDLL<E>();
	}
 
	/**
	 * Adds an element to the rear of the queue.
	 *
	 * @param toAdd the element to add
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public void enqueue(E toAdd) throws NullPointerException
	{
		if(toAdd == null)
		{
			throw new NullPointerException("Cannot add a null element.");
		}
 
		queue.add(toAdd);
	}
 
	/**
	 * Removes and returns the element at the front of the queue.
	 *
	 * @return the element removed from the front
	 * @throws EmptyQueueException if the queue is empty
	 */
	@Override
	public E dequeue() throws EmptyQueueException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("The queue is empty.");
		}
 
		return queue.remove(0);
	}
 
	/**
	 * Returns the element at the front without removing it.
	 *
	 * @return the element at the front of the queue
	 * @throws EmptyQueueException if the queue is empty
	 */
	@Override
	public E peek() throws EmptyQueueException
	{
		if(isEmpty())
		{
			throw new EmptyQueueException("The queue is empty.");
		}
 
		return queue.get(0);
	}
 
	/**
	 * Removes all elements from the queue.
	 */
	@Override
	public void dequeueAll()
	{
		queue.clear();
	}
 
	/**
	 * Checks whether the queue is empty.
	 *
	 * @return true if the queue is empty; otherwise false
	 */
	@Override
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}
 
	/**
	 * Checks whether the queue contains the specified element.
	 *
	 * @param toFind the element to search for
	 * @return true if the element is found; otherwise false
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if(toFind == null)
		{
			throw new NullPointerException("Cannot search for a null element.");
		}
 
		return queue.contains(toFind);
	}
 
	/**
	 * Returns the 1-based position of an element from the front of the queue.
	 *
	 * @param toFind the element to search for
	 * @return the 1-based position, or -1 if the element is not found
	 */
	@Override
	public int search(E toFind)
	{
		if(toFind == null)
		{
			throw new NullPointerException("Cannot search for a null element.");
		}
 
		Iterator<E> iterator = queue.iterator();
		int position = 1;
 
		while(iterator.hasNext())
		{
			E current = iterator.next();
 
			if(current.equals(toFind))
			{
				return position;
			}
 
			position++;
		}
 
		return -1;
	}
 
	/**
	 * Returns an iterator that moves through the queue from front to rear.
	 *
	 * @return an iterator for the queue
	 */
	@Override
	public Iterator<E> iterator()
	{
		return queue.iterator();
	}
 
	/**
	 * Compares this queue with another queue.
	 * Both queues must contain the same elements in the same order.
	 *
	 * @param that the queue to compare with
	 * @return true if both queues are equal; otherwise false
	 */
	@Override
	public boolean equals(QueueADT<E> that)
	{
		if(that == null || size() != that.size())
		{
			return false;
		}
 
		Iterator<E> thisIterator = iterator();
		Iterator<E> thatIterator = that.iterator();
 
		while(thisIterator.hasNext() && thatIterator.hasNext())
		{
			E thisElement = thisIterator.next();
			E thatElement = thatIterator.next();
 
			if(!thisElement.equals(thatElement))
			{
				return false;
			}
		}
 
		return true;
	}
 
	/**
	 * Returns all queue elements in an Object array.
	 *
	 * @return an array containing the queue elements
	 */
	@Override
	public Object[] toArray()
	{
		return queue.toArray();
	}
 
	/**
	 * Copies all queue elements into the provided array.
	 *
	 * @param holder the array used to hold the elements
	 * @return an array containing the queue elements
	 * @throws NullPointerException if the provided array is null
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException
	{
		if(holder == null)
		{
			throw new NullPointerException("The array cannot be null.");
		}
 
		return queue.toArray(holder);
	}
 
	/**
	 * Checks whether the queue is full.
	 * This queue uses a dynamic linked list, so it does not have a fixed capacity.
	 *
	 * @return false because this queue does not have a fixed maximum size
	 */
	@Override
	public boolean isFull()
	{
		return false;
	}
 
	/**
	 * Returns the number of elements in the queue.
	 *
	 * @return the current queue size
	 */
	@Override
	public int size()
	{
		return queue.size();
	}
}