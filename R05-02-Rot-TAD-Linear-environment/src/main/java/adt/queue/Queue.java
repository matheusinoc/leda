package adt.queue;

import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

/**
 * The interface of a generic queue. The queue is able to store any kind of
 * data.
 *
 */
public interface Queue<T> {

	/**
	 * Inserts a new element in the queue or returns an exception if the queue
	 * is full. Null elements are not allowed (the queue remains unchanged).
	 * 
	 * @param element
	 * @throws QueueOverflowException
	 */
	public void enqueue(T element) throws QueueOverflowException, StackOverflowException;

	/**
	 * If the queue has elements, it removes the oldest of the queue and returns
	 * it; otherwise, it throws an exception.
	 * 
	 * @return
	 * @throws QueueUnderflowException
	 */
	public T dequeue() throws QueueUnderflowException, StackUnderflowException, StackOverflowException;

	/**
	 * Returns (without removing) the oldest element of the queue; or 
	 * null if the queue is empty.
	 * 
	 * @return
	 */
	public T head() throws StackUnderflowException, StackOverflowException;

	/**
	 * Returns true if the queue is empty; or false, otherwise.
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * Returns true if the queue is full; or false, otherwise.
	 * 
	 * @return
	 */
	public boolean isFull();

}
