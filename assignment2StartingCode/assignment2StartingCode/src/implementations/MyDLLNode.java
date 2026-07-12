package implementations;
 
/**
 * Node class for the MyDLL doubly linked list.
 * Stores a single element along with references to the next
 * and previous nodes in the list.
 *
 * @param <E> The type of element this node holds.
 */
public class MyDLLNode<E> {
 
	private E element;
	private MyDLLNode<E> next;
	private MyDLLNode<E> prev;
 
	/**
	 * Constructs a new node holding the given element.
	 * next and prev are initialized to null.
	 *
	 * @param element the element to store in this node
	 */
	public MyDLLNode(E element) {
		this.element = element;
		this.next = null;
		this.prev = null;
	}
 
	public E getElement() {
		return element;
	}
 
	public void setElement(E element) {
		this.element = element;
	}
 
	public MyDLLNode<E> getNext() {
		return next;
	}
 
	public void setNext(MyDLLNode<E> next) {
		this.next = next;
	}
 
	public MyDLLNode<E> getPrev() {
		return prev;
	}
 
	public void setPrev(MyDLLNode<E> prev) {
		this.prev = prev;
	}
}